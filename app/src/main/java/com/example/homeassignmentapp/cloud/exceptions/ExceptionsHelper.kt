package com.example.homeassignmentapp.cloud.exceptions

import android.content.Context
import com.example.homeassignmentapp.R
import java.io.IOException

fun Throwable.getUserMessage(context: Context) = getUserMessage()?.let { context.getString(it) }

fun Throwable.getUserMessage() : Int? = when (this) {
    is IOException -> R.string.connection_error_msg
    is GithubException -> R.string.github_error_msg
    is NoResultsException -> R.string.no_results_msg
    else -> null
}