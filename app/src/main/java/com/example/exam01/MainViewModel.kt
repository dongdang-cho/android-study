package com.example.exam01

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.exam01.base.BaseViewModel
import com.example.exam01.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.exam01.util.Result
import java.util.concurrent.atomic.AtomicBoolean

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
): BaseViewModel() {

    private val _mainViewStateLiveData = MutableLiveData<MainViewState>()
    val mainViewStateLiveData: LiveData<MainViewState> = _mainViewStateLiveData

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
        onChangedViewState(MainViewState.Clear(""))
        isEnd = false
        search(DEFALUT_START_PAGE, DEFAULT_LIMIT,inputSearch.value.toString() )
    }

    private fun search(start: Int, limit : Int= DEFAULT_LIMIT, query: String) {
        if (isEnd) {
            onChangedViewState(MainViewState.ShowToast("마지막 데이터 입니다."))
            return
        }
        viewModelScope.launch (Dispatchers.IO) {
            onChangedViewState(MainViewState.ShowLoading(View.VISIBLE))
            when(val result = searchUseCase(query = query, start=start, size=limit)) {
                is Result.Error -> {
                    onChangedViewState(MainViewState.ShowToast(result.exception?.message?: "Error"))
                    onChangedViewState(MainViewState.Clear(""))
                    isEnd = false
                    page = DEFALUT_START_PAGE
                }
                is Result.Success -> {
                    isEnd = result.data.meta.is_end
                    if (!isEnd) page += 1
                    onChangedViewState(MainViewState.GetData(result.data.documents))
                }
                is Result.NoData -> {
                    isEnd = false
                    page = DEFALUT_START_PAGE
                    onChangedViewState(MainViewState.Clear(""))
                }
            }
            onChangedViewState(MainViewState.ShowLoading(View.INVISIBLE))
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
