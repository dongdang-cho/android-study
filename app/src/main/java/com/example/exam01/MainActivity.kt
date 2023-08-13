package com.example.exam01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.exam01.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel> ()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        binding.viewModel = mainViewModel

        mainViewModel.mainViewStateLiveData.observe(this) {
            onChangedViewState(it)
        }
    }

    private fun onChangedViewState(viewState: MainViewState) {
        when(viewState) {
            is MainViewState.GetData -> binding.tvResult.text = viewState.string
            MainViewState.HideLoading -> binding.tvResult.isVisible = true
            MainViewState.ShowLoading -> binding.tvResult.isVisible = false
        }
    }
}