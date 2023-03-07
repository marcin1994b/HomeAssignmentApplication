package com.example.homeassignmentapp.adapter.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (!isLoading() && !isLastPage()) {
            if (shouldLoadMoreItems()) {
                loadMoreItems()
            }
        }
    }

    private fun shouldLoadMoreItems(): Boolean {
        val visibleItemCount: Int = layoutManager.childCount
        val totalItemCount: Int = layoutManager.itemCount
        val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
        return visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0
    }

    protected abstract fun loadMoreItems()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean
}