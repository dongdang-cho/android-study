package com.example.exam01.ui.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.example.exam01.base.BaseViewModel
import com.example.exam01.network.response.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : BaseViewModel() {

    private val getBookInfo = savedStateHandle.get<Result>("item")

    init {
        Log.d("결과",getBookInfo.toString())
    }

}