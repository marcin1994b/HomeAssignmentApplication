package com.example.homeassignmentapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repository(

    @SerialName("id")
    val id: Int?,

    @SerialName("name")
    val name: String?,

    @SerialName("full_name")
    val fullName: String?,

    @SerialName("description")
    val description: String?,

    @SerialName("html_url")
    val url: String?,

    @SerialName("owner")
    val owner: RepositoryOwner?

)