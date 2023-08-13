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


    fun calc(c : Char) {

        val aInt = aNum.toInt()
        val bInt = bNum.toInt()
        viewModelScope.launch {
            _mainViewStateLiveData.value = MainViewState.ShowLoading
            delay(500)
            _mainViewStateLiveData.value = MainViewState.HideLoading
            _mainViewStateLiveData.value = MainViewState.GetData((when(c) {
                '+' -> plus(aInt, bInt)
                '-' -> minus(aInt, bInt)
                '*' -> multiply(aInt, bInt)
                '/' -> divide(aInt, bInt)
                else -> "잘못된 연산입니다."
            }).toString())
        }
    }

    private val plus =  {x: Int, y: Int -> x+y}
    private val minus =  {x: Int, y: Int -> x-y}
    private val multiply =  {x: Int, y: Int -> x*y}
    private val divide =  {x: Int, y: Int -> x/y}







}