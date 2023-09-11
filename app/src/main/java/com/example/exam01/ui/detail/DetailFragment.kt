package com.example.exam01.ui.detail

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
            webview.apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(args.item.resourceURI)
            }
            fabFavorite.isChecked = args.item.isBookmark
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, WebViewOnBackPressedCallback(binding.webview, onBackPress = {
                findNavController().popBackStack()
            })
        )

        binding.fabFavorite.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mainViewModel.addBookmark(args.item)
            } else {
                mainViewModel.deleteBookmark(args.item)
            }
        }
    }
    override fun onChangedViewState(viewState: ViewState) {
    }

    override fun onPause() {
        binding.webview.onPause()
        super.onPause()
    }

    override fun onResume() {
        binding.webview.onResume()
        super.onResume()
    }
}

class WebViewOnBackPressedCallback(
    private val webView: WebView,
    private val onBackPress: () -> Unit
) :
    OnBackPressedCallback(webView.isEnabled) {
    override fun handleOnBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            onBackPress()
        }
    }
}