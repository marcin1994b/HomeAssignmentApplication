package com.example.homeassignmentapp.adapter.repository

import android.content.Context
import com.example.homeassignmentapp.R

fun RepositoryItem.getAvatarUrl() = repository.owner?.avatarUrl

fun RepositoryItem.getOwnerName(context: Context) =
    context.getString(R.string.repository_item_owner) + ": " + this.repository.owner?.name.getStringValueWithDefault(context)

fun RepositoryItem.getRepositoryName(context: Context) =
    context.getString(R.string.repository_item_name) + ": " + repository.name.getStringValueWithDefault(context)

fun RepositoryItem.getRepositoryTitle(context: Context) =
    context.getString(R.string.repository_item_title) + ": " + repository.fullName.getStringValueWithDefault(context)

fun RepositoryItem.getDescription(context: Context) =
    context.getString(R.string.repository_item_description) + ": \n" + repository.description.getStringValueWithDefault(context)

fun RepositoryItem.getUrl(context: Context) =
    context.getString(R.string.repository_item_url) + ": " + repository.url.getStringValueWithDefault(context)

private fun String?.getStringValueWithDefault(context: Context) =
    this ?: context.getString(R.string.unknown_label)