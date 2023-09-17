package com.example.exam01.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<ViewState>(ViewState.Idle)
    val uiState: StateFlow<ViewState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = ViewState.Idle
    )

    protected fun onChangedViewState(viewState: ViewState) {
        viewModelScope.launch {
            _uiState.value = viewState
        }
    }
}