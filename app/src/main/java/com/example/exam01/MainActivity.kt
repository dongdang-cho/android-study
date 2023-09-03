package com.example.exam01

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import com.example.exam01.base.BaseActivity
import com.example.exam01.databinding.ActivityMainBinding
import com.example.exam01.ext.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewState>(R.layout.activity_main) {
    private val bookAdapter = KakaoBookAdapter()
    override val viewModel: MainViewModel by viewModels()
    override fun initUi() {
        with(binding) {
            binding.viewModel = this@MainActivity.viewModel
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
    override fun onChangedViewState(viewState: MainViewState) {
        when(viewState) {
            is MainViewState.ShowToast -> {
                showToast(message = viewState.message)

            }
            is MainViewState.ShowLoading -> {
                binding.progressBar.visibility = viewState.flag
            }
            is MainViewState.GetData -> {
                binding.tvNoData.visibility = View.INVISIBLE
                bookAdapter.addAll(viewState.list)

            }
            is MainViewState.Clear -> {
                bookAdapter.clear()
                binding.tvNoData.visibility = View.VISIBLE
            }
        }
    }


}