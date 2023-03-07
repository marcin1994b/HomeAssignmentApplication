package com.example.homeassignmentapp.adapter.repository

import com.example.homeassignmentapp.adapter.base.ListItem
import com.example.homeassignmentapp.model.Repository

data class RepositoryItem(
    override val id: Long,
    val repository: Repository
) : ListItem