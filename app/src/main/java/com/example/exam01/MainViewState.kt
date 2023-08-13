package com.example.exam01

sealed class MainViewState {
    object ShowLoading : MainViewState()
    object HideLoading : MainViewState()
    data class GetData(val string: String) : MainViewState()
}