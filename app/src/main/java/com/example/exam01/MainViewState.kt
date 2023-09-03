package com.example.exam01

import com.example.exam01.base.ViewState
import com.example.exam01.network.response.Document

sealed class MainViewState : ViewState {
    data class ShowToast(val message: String) : MainViewState()
    data class GetData(val list: List<Document>) : MainViewState()
    data class ShowLoading(val flag: Int) : MainViewState()
    data class Clear(val message: String) : MainViewState()

}