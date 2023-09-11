package com.example.exam01.ui.search

import androidx.lifecycle.viewModelScope
import com.example.exam01.base.BaseViewModel
import com.example.exam01.exception.EmptyBodyException
import com.example.exam01.exception.NetworkFailureException
import com.example.exam01.exception.SearchErrorException
import com.example.exam01.usecase.SearchUiState
import com.example.exam01.usecase.SearchUseCase
import com.example.exam01.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : BaseViewModel() {

    private var offsetCount = DEFAULT_START_COUNT
    private var isStartSearch = AtomicBoolean(false)

    init {
        search(offsetCount)
    }

    val isScrollBottomPosition: Function1<Boolean, Unit> = { isBottom ->
        if (isBottom && !isStartSearch.get()) {
            search(offsetCount)
        }
    }


    private fun search(
        start: Int = DEFAULT_START_COUNT,
        limit: Int = DEFAULT_LIMIT
    ) {
        searchUseCase(start, limit).onEach { result ->
            when (result) {
                is Result.Success -> {
                    when (result.data) {
                        SearchUiState.End -> {
                            onChangedViewState(SearchViewState.ShowToast("마지막 데이터 입니다."))
                        }

                        is SearchUiState.GetData -> {
                            offsetCount += result.data.data.count
                            onChangedViewState(SearchViewState.GetData(result.data.data.results))
                        }
                    }
                }

                is Result.Error -> {
                    when (result.exception) {
                        is NetworkFailureException -> {

                        }

                        is EmptyBodyException -> {

                        }

                        is SearchErrorException -> {

                        }
                    }
                }
            }
        }.launchIn(viewModelScope)

    }

    companion object {
        private const val DEFAULT_START_COUNT = 0
        private const val DEFAULT_LIMIT = 20
    }
}