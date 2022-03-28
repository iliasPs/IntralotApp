package com.ip.intralotapp.data.repository

import com.ip.intralotapp.data.remote.GithubProjectsApi
import com.ip.intralotapp.data.remote.dto.GithubRepoDTO
import com.ip.intralotapp.domain.repository.GithubReposRepository
import javax.inject.Inject

/**
 * Implementation of Repository
 */
class GithubReposRepositoryImpl @Inject constructor(
    private val api: GithubProjectsApi
) : GithubReposRepository {

    override suspend fun getRepos(): List<GithubRepoDTO> {
        return api.getRepos()
    }
}