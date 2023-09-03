package com.example.exam01

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.exam01.base.BaseViewModel
import com.example.exam01.domain.usecase.SearchUseCase
import com.example.exam01.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class MainFViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : BaseViewModel() {
    private val _mainViewStateLiveData = MutableLiveData<MainFViewState>()
    val mainViewStateLiveData: LiveData<MainFViewState> = _mainViewStateLiveData

    var inputSearch = MutableLiveData<String>("")
    private var isStartSearch = AtomicBoolean(false)
    private var isEnd = false
    private var page = DEFALUT_START_PAGE
    val isScrollBottomPosition: Function1<Boolean, Unit> = { isBottom ->
        if (isBottom && !isStartSearch.get()) {
            search(page,DEFAULT_LIMIT,inputSearch.value.toString())
        }
    }

    fun textChanged() {
        onChangedViewState(MainFViewState.Clear(""))
        isEnd = false
        search(DEFALUT_START_PAGE, DEFAULT_LIMIT,inputSearch.value.toString() )
    }

    private fun search(start: Int, limit : Int= DEFAULT_LIMIT, query: String) {
        if (isEnd) {
            onChangedViewState(MainFViewState.ShowToast("마지막 데이터 입니다."))
            return
        }
        viewModelScope.launch (Dispatchers.IO) {
            onChangedViewState(MainFViewState.ShowLoading(View.VISIBLE))
            when(val result = searchUseCase(query = query, start=start, size=limit)) {
                is Result.Error -> {
                    onChangedViewState(MainFViewState.ShowToast(result.exception?.message?: "Error"))
                    onChangedViewState(MainFViewState.Clear(""))
                    isEnd = false
                    page = DEFALUT_START_PAGE
                }
                is Result.Success -> {
                    isEnd = result.data.meta.is_end
                    if (!isEnd) page += 1
                    onChangedViewState(MainFViewState.GetData(result.data.documents))
                }
                is Result.NoData -> {
                    isEnd = false
                    page = DEFALUT_START_PAGE
                    onChangedViewState(MainFViewState.Clear(""))
                }
            }
            onChangedViewState(MainFViewState.ShowLoading(View.INVISIBLE))
        }

    }

//    fun refresh(start: Int, limit: Int) {
    /* viewModelScope.launch(Dispatchers.IO) {
         val response = marvelRepository.searchCharacters(start, limit)
         if (response.isSuccessful) {
             response.body()?.let {
                 onChangedViewState(MainViewState.Refresh(it.data.results))
             } ?: kotlin.run {
                 onChangedViewState(MainViewState.ShowToast("검색을 실패하였습니다."))
             }
         } else {
             onChangedViewState(MainViewState.ShowToast("검색을 실패하였습니다."))
         }
     }*/
//    }


    companion object {
        private const val DEFAULT_LIMIT = 50
        private const val DEFALUT_START_PAGE = 1
    }
}