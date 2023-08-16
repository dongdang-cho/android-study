package com.example.exam01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.exam01.base.BaseActivity
import com.example.exam01.base.BaseViewModel
import com.example.exam01.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewState>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()
    override fun initUi() {
        binding.viewModel = viewModel
    }


    override fun onChangedViewState(viewState: MainViewState) {
        when(viewState) {
            is MainViewState.GetData -> binding.tvResult.text = viewState.string
            MainViewState.HideLoading -> binding.tvResult.isVisible = true
            MainViewState.ShowLoading -> binding.tvResult.isVisible = false
        }
    }
}