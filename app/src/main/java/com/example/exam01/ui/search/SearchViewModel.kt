package com.example.exam01.ui.search

import androidx.lifecycle.viewModelScope
import com.example.exam01.base.BaseViewModel
import com.example.exam01.data.repo.BookmarkRepository
import com.example.exam01.exception.EmptyBodyException
import com.example.exam01.exception.NetworkFailureException
import com.example.exam01.exception.SearchErrorException
import com.example.exam01.usecase.SearchUiState
import com.example.exam01.usecase.SearchUseCase
import com.example.exam01.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    bookmarkRepository: BookmarkRepository
) : BaseViewModel() {

    private var offsetCount = DEFAULT_START_COUNT
    private var isStartSearch = AtomicBoolean(false)

    private val bookMarkList = bookmarkRepository.bookmarkList

    val isScrollBottomPosition: Function1<Boolean, Unit> = { isBottom ->
        if (isBottom && !isStartSearch.get()) {
            isStartSearch.set(true)
            search()
        }
    }

    init {
        search()
    }

    private fun search(
        isRefresh: Boolean = false,
        start: Int = offsetCount,
    ) {
        onChangedViewState(SearchViewState.ShowLoading(true))
        searchUseCase(start).onEach { result ->
            when (result) {
                is Result.Success -> {
                    when (result.data) {
                        SearchUiState.End -> {
                            onChangedViewState(SearchViewState.ShowToast("마지막 데이터 입니다."))
                        }

                        is SearchUiState.GetData -> {
                            offsetCount += result.data.data.count

                            val bookmarkIdList = bookMarkList.first().map { it.id }

                            val toConvertBookmarkList = result.data.data.results.map {
                                it.copy(isBookmark = bookmarkIdList.contains(it.id))
                            }

                            if (isRefresh)
                                onChangedViewState(SearchViewState.Refresh(toConvertBookmarkList))
                            else
                                onChangedViewState(SearchViewState.GetData(toConvertBookmarkList))
                        }

                    }
                }

                is Result.Error -> {
                    when (result.exception) {
                        is NetworkFailureException -> {
                            onChangedViewState(SearchViewState.ShowToast("네트워크 에러!"))
                        }

                        is EmptyBodyException -> {
                            onChangedViewState(SearchViewState.ShowToast("네트워크 에러!!"))
                        }

                        is SearchErrorException -> {
                            onChangedViewState(SearchViewState.ShowToast("네트워크 에러!!!"))
                        }
                    }
                }
            }
            isStartSearch.set(false)
            onChangedViewState(SearchViewState.ShowLoading(false))
        }.launchIn(viewModelScope)

    }

    fun refresh() {
        offsetCount = DEFAULT_START_COUNT
        search(isRefresh = true)
    }

    companion object {
        private const val DEFAULT_START_COUNT = 0
    }
}