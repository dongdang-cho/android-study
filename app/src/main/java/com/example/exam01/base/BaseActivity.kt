package com.example.exam01.base

import android.os.Bundle
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<V : ViewDataBinding, VS: ViewState>(@LayoutRes private val layoutId: Int) :
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
        viewModel.viewStateLiveData.observe(this) { viewState ->
            (viewState as? VS)?.let {
                onChangedViewState(viewState)
            }
        }
    }

    abstract fun initUi()
    abstract fun onChangedViewState(viewState: VS)
}