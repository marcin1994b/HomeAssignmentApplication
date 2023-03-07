package com.example.homeassignmentapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repositories(

    @SerialName("total_count")
    val totalCount: Int?,

    @SerialName("items")
    val items: List<Repository>?

)
