package com.example.exam01

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _mainViewStateLiveData = MutableLiveData<MainViewState>()
    val mainViewStateLiveData: LiveData<MainViewState> = _mainViewStateLiveData
    var aNum : String = "0"
    var bNum : String = "0"

    fun plus() {
        viewModelScope.launch {
            _mainViewStateLiveData.value = MainViewState.ShowLoading
            delay(500)
            _mainViewStateLiveData.value = MainViewState.HideLoading
            _mainViewStateLiveData.value = MainViewState.GetData((Integer.parseInt(aNum)+Integer.parseInt(bNum)).toString())
        }
    }

    fun minus() {
        viewModelScope.launch {
            _mainViewStateLiveData.value = MainViewState.ShowLoading
            delay(500)
            _mainViewStateLiveData.value = MainViewState.HideLoading
            _mainViewStateLiveData.value = MainViewState.GetData((Integer.parseInt(aNum)-Integer.parseInt(bNum)).toString())
        }
    }

    fun multiply() {
        viewModelScope.launch {
            _mainViewStateLiveData.value = MainViewState.ShowLoading
            delay(500)
            _mainViewStateLiveData.value = MainViewState.HideLoading
            _mainViewStateLiveData.value = MainViewState.GetData((Integer.parseInt(aNum)*Integer.parseInt(bNum)).toString())

        }
    }
    fun divide() {
        viewModelScope.launch {
            _mainViewStateLiveData.value = MainViewState.ShowLoading
            delay(500)
            _mainViewStateLiveData.value = MainViewState.HideLoading
            _mainViewStateLiveData.value = MainViewState.GetData((Integer.parseInt(aNum)/Integer.parseInt(bNum)).toString())

        }
    }



}