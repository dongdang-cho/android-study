package com.example.exam01.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withCreated
import kotlinx.coroutines.launch


abstract class BaseFragment<V : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : Fragment() {

    abstract val viewModel: BaseViewModel
    protected lateinit var binding: V


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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