package com.example.exam01

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exam01.base.BaseViewModel
import com.example.exam01.data.repo.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val marvelRepository: MarvelRepository): BaseViewModel() {

    private val _mainViewStateLiveData = MutableLiveData<MainViewState>()
    val mainViewStateLiveData: LiveData<MainViewState> = _mainViewStateLiveData
    var inputText = MutableLiveData("")

    fun search(start: Int, limit : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            onChangedViewState(MainViewState.ShowLoading(View.VISIBLE))
            val response = marvelRepository.searchCharacters(start, limit)
            if (response.isSuccessful) {
                response.body()?.let {
                    if(it.data.results.isEmpty()) onChangedViewState(MainViewState.LastData("마지막 캐릭터 정보입니다."))
                    else onChangedViewState(MainViewState.GetData(it.data.results))
                } ?: kotlin.run {
                    onChangedViewState(MainViewState.ShowToast("검색을 실패하였습니다."))
                }
            } else {
                onChangedViewState(MainViewState.ShowToast("검색을 실패하였습니다."))
            }

            onChangedViewState(MainViewState.ShowLoading(View.INVISIBLE))
        }

    }

    fun refresh(start: Int, limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = marvelRepository.searchCharacters(start, limit)
            if (response.isSuccessful) {
                response.body()?.let {
                    onChangedViewState(MainViewState.Refresh(it.data.results))
                } ?: kotlin.run {
                    onChangedViewState(MainViewState.ShowToast("검색을 실패하였습니다."))
                }
            } else {
                onChangedViewState(MainViewState.ShowToast("검색을 실패하였습니다."))
            }
        }
    }



}
