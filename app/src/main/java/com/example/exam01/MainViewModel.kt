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
    var inputALiveData = MutableLiveData("")
    var inputBLiveData = MutableLiveData("")

    fun calc(c: Operation) {

        val aInt = inputALiveData.value?.toInt() ?: 0
        val bInt = inputBLiveData.value?.toInt() ?: 0

        viewModelScope.launch {
            _mainViewStateLiveData.value = MainViewState.ShowLoading
            delay(500)
            _mainViewStateLiveData.value = MainViewState.HideLoading
            _mainViewStateLiveData.value = MainViewState.GetData(
                (when (c) {
                    Operation.Plus -> plus(aInt, bInt)
                    Operation.Min -> minus(aInt, bInt)
                    Operation.Mul -> multiply(aInt, bInt)
                    Operation.Div -> divide(aInt, bInt)
                }).toString()
            )
        }
    }

    private val plus = { x: Int, y: Int -> x + y }
    private val minus = { x: Int, y: Int -> x - y }
    private val multiply = { x: Int, y: Int -> x * y }
    private val divide = { x: Int, y: Int -> x / y }

}

enum class Operation {
    Plus, Min, Mul, Div
}