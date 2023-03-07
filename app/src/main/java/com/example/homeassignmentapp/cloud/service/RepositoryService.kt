package com.example.homeassignmentapp.cloud.service

import com.example.homeassignmentapp.model.Repositories
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {

    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ) : Response<Repositories>?

}