package com.ip.intralotapp.data.remote

import com.ip.intralotapp.data.remote.dto.GithubRepoDTO
import retrofit2.http.GET

/**
 * Our API interface
 * Here we define the different functions we want to access
 */
interface GithubProjectsApi {

    @GET("repos")
    suspend fun getRepos(): List<GithubRepoDTO>
}