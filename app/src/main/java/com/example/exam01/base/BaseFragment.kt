package com.example.exam01.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment <V : ViewDataBinding, VS: ViewState>(@LayoutRes private val layoutId: Int)
    : Fragment() {

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
        viewModel.viewStateLiveData.observe(viewLifecycleOwner) { viewState ->
            (viewState as? VS)?.let {
                onChangedViewState(viewState)
            }
        }
    }
    abstract fun initUi()
    abstract fun onChangedViewState(viewState: VS)
}