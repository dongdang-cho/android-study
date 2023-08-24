package com.example.exam01

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam01.base.BaseActivity
import com.example.exam01.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewState>(R.layout.activity_main) {
    private val marvelAdapter = MarvelCharacterAdapter()

    override val viewModel: MainViewModel by viewModels()
    val limit : Int = 20
    override fun initUi() {
        with(binding) {
            binding.viewModel = this@MainActivity.viewModel
            val manager = GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false);
            rvCharacters.layoutManager = manager;
            rvCharacters.adapter = marvelAdapter
            viewModel?.search(0,limit)

            binding.srLayout.setOnRefreshListener { viewModel?.refresh(0, limit) }
            binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (!rvCharacters.canScrollVertically(1)) {
                        viewModel?.search(marvelAdapter.itemCount, limit)
                    }
                }
            })
        }
    }
    override fun onChangedViewState(viewState: MainViewState) {
        when (viewState) {
            is MainViewState.GetData -> {
                marvelAdapter.addAll(viewState.list)

            }
            is MainViewState.ShowToast -> {
                Toast.makeText(this, viewState.message, Toast.LENGTH_SHORT).show()
            }

            is MainViewState.Refresh -> {
                marvelAdapter.clear()
                marvelAdapter.addAll(viewState.list)

                binding.srLayout.isRefreshing = false
            }
            is MainViewState.ShowLoading -> {
                binding.progressBar.visibility = viewState.flag

            }

            is MainViewState.LastData -> {
                Toast.makeText(this, viewState.message, Toast.LENGTH_SHORT).show()
            }


        }
    }


}