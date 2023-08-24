package com.example.exam01

import com.example.exam01.base.ViewState
import com.example.exam01.network.response.Result

sealed class MainViewState : ViewState {
    data class ShowToast(val message: String) : MainViewState()
    data class GetData(val list: List<Result>) : MainViewState()
    data class Refresh(val list: List<Result>) : MainViewState()
    data class ShowLoading(val flag: Int) : MainViewState()

    data class LastData(val message: String) : MainViewState()

}