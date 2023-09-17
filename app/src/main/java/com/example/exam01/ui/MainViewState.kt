package com.example.exam01.ui

import com.example.exam01.base.ViewState
import com.example.exam01.network.response.Result

sealed class MainViewState : ViewState{

    data class AddBookmark(val item: Result) : MainViewState()

    data class DeleteBookmark(val item: Result) : MainViewState()
}