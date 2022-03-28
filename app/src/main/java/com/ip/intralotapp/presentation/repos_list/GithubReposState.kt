package com.ip.intralotapp.presentation.repos_list

import com.ip.intralotapp.domain.models.GithubRepo

/**
 * Data class that contains the state of our data
 */
data class GithubReposState(

    val isLoading: Boolean = false,
    var repos: List<GithubRepo> = emptyList(),
    val error: String = ""
)