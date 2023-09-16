package com.example.exam01.ui.search

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.exam01.R
import com.example.exam01.base.BaseFragment
import com.example.exam01.base.ViewState
import com.example.exam01.databinding.FragmentSearchBinding
import com.example.exam01.ui.MainViewModel
import com.example.exam01.ui.adapter.ItemClickType
import com.example.exam01.ui.adapter.MarvelCharacterAdapter
import com.example.exam01.ext.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val mainViewModel by activityViewModels<MainViewModel>()

    private val marvelAdapter = MarvelCharacterAdapter { clickType ->
        when (clickType) {
            is ItemClickType.AddBookmark -> {
                mainViewModel.addBookmark(clickType.item)
            }

            is ItemClickType.DeleteBookmark -> {
                mainViewModel.deleteBookmark(clickType.item)
            }

            is ItemClickType.ItemClick -> {
                findNavController().navigate(
                    SearchFragmentDirections.actionFragmentSearchToFragmentDetail(
                        clickType.item
                    )
                )
            }
        }
    }

    override val viewModel: SearchViewModel by viewModels()

    override fun initUi() {
        with(binding) {
            binding.viewModel = this@SearchFragment.viewModel
            rvCharacters.adapter = marvelAdapter
        }
        lifecycleScope.launch {
            mainViewModel.uiState.collect{
                onChangedViewState(it)
            }
        }
    }

    override fun onChangedViewState(viewState: ViewState) {
        when (viewState) {

            //Search
            is SearchViewState.ShowToast -> {
                showToast(message = viewState.message)
            }

            is SearchViewState.ShowLoading -> {
                binding.progressBar.isVisible = viewState.flag
            }

            is SearchViewState.GetData -> {
                marvelAdapter.addAll(viewState.list)
            }
            is SearchViewState.Refresh -> {
               marvelAdapter.clear()
               marvelAdapter.addAll(viewState.list)
               binding.srLayout.isRefreshing = false
            }


        }
    }

}