package com.example.exam01.ui.search

import com.example.exam01.base.ViewState
import com.example.exam01.network.response.Result

sealed class SearchViewState : ViewState{
    data class ShowToast(val message: String) : SearchViewState()
    data class GetData(val list: List<Result>) : SearchViewState()
    data class ShowLoading(val flag: Boolean) : SearchViewState()
    data class Refresh(val list: List<Result>) : SearchViewState()

    object Clear : SearchViewState()

}