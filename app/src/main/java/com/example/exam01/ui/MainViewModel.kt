package com.example.exam01.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.exam01.base.BaseViewModel
import com.example.exam01.data.repo.BookmarkRepository
import com.example.exam01.data.repo.MarvelRepository
import com.example.exam01.network.response.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) : BaseViewModel() {

    fun addBookmark(item: com.example.exam01.network.response.Result) = viewModelScope.launch(Dispatchers.IO) {
        bookmarkRepository.addBookmark(item.toBookmarkEntity())
        onChangedViewState(MainViewState.AddBookmark(item))
    }

    fun deleteBookmark(item: Result) = viewModelScope.launch(Dispatchers.IO) {
        bookmarkRepository.deleteBookmark(item.toBookmarkEntity())
        onChangedViewState(MainViewState.DeleteBookmark(item))
    }




}
