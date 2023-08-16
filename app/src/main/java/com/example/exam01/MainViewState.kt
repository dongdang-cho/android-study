package com.example.exam01

import com.example.exam01.base.ViewState

sealed class MainViewState : ViewState {
    object ShowLoading : MainViewState()
    object HideLoading : MainViewState()
    data class GetData(val string: String) : MainViewState()
}