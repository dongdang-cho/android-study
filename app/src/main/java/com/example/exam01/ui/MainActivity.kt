package com.example.exam01.ui

import androidx.activity.viewModels
import com.example.exam01.R
import com.example.exam01.base.BaseActivity
import com.example.exam01.base.ViewState
import com.example.exam01.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModels()
    override fun initUi() {
            binding.viewModel = this@MainActivity.viewModel
    }
    override fun onChangedViewState(viewState: ViewState) {
        when(viewState) {
            is MainViewState.AddBookmark -> {
                Snackbar.make(binding.root, "즐겨찾기 추가", Snackbar.LENGTH_SHORT).show()
            }

            is MainViewState.DeleteBookmark -> {
                Snackbar.make(binding.root, "즐겨찾기 삭제", Snackbar.LENGTH_SHORT).show()
            }
        }
    }


}