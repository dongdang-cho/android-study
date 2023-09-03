package com.example.exam01

import com.example.exam01.base.ViewState
import com.example.exam01.network.response.Document

sealed class MainFViewState : ViewState{
    data class ShowToast(val message: String) : MainFViewState()
    data class GetData(val list: List<Document>) : MainFViewState()
    data class ShowLoading(val flag: Int) : MainFViewState()
    data class Clear(val message: String) : MainFViewState()
}