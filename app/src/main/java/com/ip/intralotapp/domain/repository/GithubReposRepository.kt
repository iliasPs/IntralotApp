package com.ip.intralotapp.domain.repository

import com.ip.intralotapp.data.remote.dto.GithubRepoDTO


/**
 * There are 2 Repository packages,
 * In this package we only define our repositories,
 * whereas in the Remote.Repository we introduce their implementation
 */
interface GithubReposRepository {

    suspend fun getRepos(): List<GithubRepoDTO>
}