package com.example.exam01.ui.detail

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.exam01.R
import com.example.exam01.base.BaseFragment
import com.example.exam01.base.ViewState
import com.example.exam01.databinding.FragmentDetailBinding
import com.example.exam01.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    override val viewModel: DetailViewModel by viewModels()

    private val args by navArgs<DetailFragmentArgs>()

    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun initUi() {
        with(binding) {
            item = args.item
        }
    }
    override fun onChangedViewState(viewState: ViewState) {
    }

}
