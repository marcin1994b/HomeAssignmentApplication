package com.example.homeassignmentapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.homeassignmentapp.cloud.calls.RepositoryCalls
import com.example.homeassignmentapp.cloud.exceptions.GithubException
import com.example.homeassignmentapp.cloud.exceptions.NoResultsException
import com.example.homeassignmentapp.cloud.isErrorState
import com.example.homeassignmentapp.model.Repositories
import com.example.homeassignmentapp.model.Repository
import com.example.homeassignmentapp.model.getTotalCount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repositoryCalls: RepositoryCalls
) : ViewModel() {

    private val TIMEOUT_IN_MILLIS = 500L
    private val ITEMS_PER_PAGE = 30

    private var fetchRepositoriesJob: Job? = null
    private var queryObserverJob: Job? = null
    private var page: Int = 0
    private var totalItemCount: Int = 0

    val isLoading = MutableLiveData(false)
    val errorState = MutableLiveData<Throwable?>()

    val queryData = MutableLiveData<String>()
    val repositoriesList = MutableLiveData<List<Repository>>(emptyList())

    fun onStart() {
        Log.d(TAG, "onStart")
        if (queryObserverJob?.isActive == true) {
            return
        }
        queryObserverJob = viewModelScope.launch {
            queryData.asFlow()
                .debounce(TIMEOUT_IN_MILLIS)
                .distinctUntilChanged()
                .collect { query ->
                    when (query.isNullOrEmpty()) {
                        true -> repositoriesList.value = emptyList()
                        else -> fetchNewQuery(query)
                    }
                }
        }
    }

    fun isLastPage(): Boolean = page * ITEMS_PER_PAGE > totalItemCount

    fun fetchNextPage() {
        Log.d(TAG, "fetchNextPage")
        queryData.value?.let {
            if (it.isNotEmpty() && fetchRepositoriesJob?.isCompleted == true) {
                page += 1
                fetchRepositories(it, page)
            }
        }
    }

    private fun fetchNewQuery(query: String) {
        Log.d(TAG, "fetchNewQuery")
        page = 1
        repositoriesList.value = emptyList()
        fetchRepositories(query, page)
    }

    private fun fetchRepositories(query: String, page: Int) {
        Log.d(TAG, "fetchRepositories: query: $query page: $page")

            viewModelScope.launch {
                isLoading.value = true
                fetchRepositoriesJob?.cancel()
                fetchRepositoriesJob = async(Dispatchers.Main) {
                    try {
                        processResponse(repositoryCalls.getRepositories(query, page, ITEMS_PER_PAGE))
                    } catch (e: Throwable) {
                        errorState.value = e
                    }
                }
            }
    }

    private fun processResponse(response: Response<Repositories>?) {
        Log.d(TAG, "processResponse: $response")
        if (response?.isErrorState() == true) {
            errorState.value = GithubException()
            isLoading.value = false
            return
        }

        response?.body()?.let { repositories ->
            val newItems = repositories.items ?: emptyList()
            val oldItems = repositoriesList.value ?: emptyList()
            repositoriesList.value = (oldItems + newItems)

            totalItemCount = repositories.getTotalCount()
            isLoading.value = false

            if (totalItemCount == 0) {
                errorState.value = NoResultsException()
            }
        }
    }

    companion object {
        val TAG = "ViewModel"
    }
}