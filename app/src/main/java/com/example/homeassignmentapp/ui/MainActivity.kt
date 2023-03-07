package com.example.homeassignmentapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeassignmentapp.R
import com.example.homeassignmentapp.adapter.base.PaginationScrollListener
import com.example.homeassignmentapp.adapter.repository.RepositoryAdapter
import com.example.homeassignmentapp.cloud.exceptions.getUserMessage
import com.example.homeassignmentapp.databinding.ActivityMainBinding
import com.example.homeassignmentapp.model.Repository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    lateinit var binding: ActivityMainBinding
    lateinit var repositoryAdapter: RepositoryAdapter

    private val repositoriesObserver = Observer<List<Repository>> {
        repositoryAdapter.updateData(it.createRepositoryItems())
    }

    private val isLoadingObserver = Observer<Boolean> {
        if (it) {
            showToast(getString(R.string.loading_label), Toast.LENGTH_SHORT)
        }
    }

    private val errorStateObserver = Observer<Throwable?> {
       it?.getUserMessage(this)?.let { msg ->
           showToast(msg, Toast.LENGTH_LONG)
       }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initView()
        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    private fun initViewModel() {
        viewModel.repositoriesList.observe(this, repositoriesObserver)
        viewModel.isLoading.observe(this, isLoadingObserver)
        viewModel.errorState.observe(this, errorStateObserver)
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            searchView.doOnTextChanged { text, _, _, _ ->
                this@MainActivity.viewModel.queryData.value = text.toString()
            }
        }
    }

    private fun initRecyclerView() {
        repositoryAdapter = RepositoryAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = repositoryAdapter
            addOnScrollListener(object : PaginationScrollListener(binding.recyclerView.layoutManager as LinearLayoutManager) {
                override fun loadMoreItems() {
                    this@MainActivity.viewModel.fetchNextPage()
                }

                override fun isLastPage(): Boolean =
                    this@MainActivity.viewModel.isLastPage()

                override fun isLoading(): Boolean =
                    this@MainActivity.viewModel.isLoading.value == true
            })
        }
    }
}