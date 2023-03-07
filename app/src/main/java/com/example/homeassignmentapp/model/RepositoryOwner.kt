package com.example.homeassignmentapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryOwner(

    @SerialName("login")
    val name: String?,

    @SerialName("avatar_url")
    val avatarUrl: String?

)
