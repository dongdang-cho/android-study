package com.example.exam01.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val _viewStateLiveData = MutableLiveData<ViewState>()
    val viewStateLiveData: LiveData<ViewState> = _viewStateLiveData

    protected fun onChangedViewState(viewState: ViewState) {
        viewModelScope.launch {
            _viewStateLiveData.value = viewState
            _viewStateLiveData.value = null
        }
    }
}