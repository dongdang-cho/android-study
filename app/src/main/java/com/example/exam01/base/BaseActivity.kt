package com.example.exam01.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

abstract class BaseActivity<V : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    AppCompatActivity() {

    abstract val viewModel: BaseViewModel
    protected lateinit var binding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, layoutId)
        setContentView(binding.root)
        initUi()
        initViewModel()
    }
    private fun initViewModel() {
        lifecycleScope.launch {
            viewModel.uiState.collect { viewState ->
                onChangedViewState(viewState)
            }
        }
    }

    abstract fun initUi()
    abstract fun onChangedViewState(viewState: ViewState)


}