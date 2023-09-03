package com.example.exam01

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import com.example.exam01.base.BaseFragment
import com.example.exam01.databinding.FragmentMainBinding
import com.example.exam01.ext.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainFViewState>(R.layout.fragment_main) {
    private val bookAdapter = KakaoBookAdapter()
    override val viewModel: MainFViewModel by viewModels()
    override fun initUi() {
        with(binding) {
            binding.viewModel = this@MainFragment.viewModel
            rvBooks.adapter = bookAdapter

            binding.etSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    viewModel?.textChanged()
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        }

    }
    override fun onChangedViewState(viewState: MainFViewState) {
        when(viewState) {
            is MainFViewState.ShowToast -> {
                showToast(message = viewState.message)

            }
            is MainFViewState.ShowLoading -> {
                binding.progressBar.visibility = viewState.flag
            }
            is MainFViewState.GetData -> {
                binding.tvNoData.visibility = View.INVISIBLE
                bookAdapter.addAll(viewState.list)

            }
            is MainFViewState.Clear -> {
                bookAdapter.clear()
                binding.tvNoData.visibility = View.VISIBLE
            }
        }
    }
}