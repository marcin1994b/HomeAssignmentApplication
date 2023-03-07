package com.example.homeassignmentapp.ui

import android.content.Context
import android.widget.Toast
import com.example.homeassignmentapp.adapter.repository.RepositoryItem
import com.example.homeassignmentapp.model.Repository

fun List<Repository>?.createRepositoryItems(): List<RepositoryItem> =
    this?.map { repo -> RepositoryItem(repo.id?.toLong() ?: 0L, repo) }?.toList() ?: emptyList()

fun Context.showToast(message: String, toastLength: Int) = Toast.makeText(this, message, toastLength).show()