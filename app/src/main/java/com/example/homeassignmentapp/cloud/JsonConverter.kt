package com.example.homeassignmentapp.cloud

import kotlinx.serialization.json.Json

object JsonConverter {
    val json: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
}