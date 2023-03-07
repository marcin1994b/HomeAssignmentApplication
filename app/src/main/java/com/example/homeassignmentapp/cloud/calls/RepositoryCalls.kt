package com.example.homeassignmentapp.cloud.calls

import com.example.homeassignmentapp.cloud.service.RepositoryService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RepositoryCalls @Inject constructor(private val retrofitInstance: Retrofit) {

    suspend fun getRepositories(query: String, page: Int, perPage: Int) = retrofitInstance
        .create(RepositoryService::class.java)
        .getRepositories(query, page, perPage)

}