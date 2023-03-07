package com.example.homeassignmentapp.adapter.repository

import com.example.homeassignmentapp.R
import com.example.homeassignmentapp.adapter.base.BaseAdapter
import com.example.homeassignmentapp.databinding.ItemRepositoryBinding

class RepositoryAdapter(
    itemsList: List<RepositoryItem>
) : BaseAdapter<ItemRepositoryBinding, RepositoryItem>(itemsList) {

    override val layoutId: Int = R.layout.item_repository

    override fun bind(binding: ItemRepositoryBinding, item: RepositoryItem) {
        binding.apply {
            this.item = item
            executePendingBindings()
        }
    }

}