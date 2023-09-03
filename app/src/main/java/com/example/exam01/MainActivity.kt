package com.example.exam01

import androidx.activity.viewModels
import com.example.exam01.base.BaseActivity
import com.example.exam01.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainFViewState>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()
    override fun initUi() {
        with(binding) {
            binding.viewModel = this@MainActivity.viewModel
        }

    }
    override fun onChangedViewState(viewState: MainFViewState) {
    }


}