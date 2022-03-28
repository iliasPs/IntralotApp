package com.ip.intralotapp.domain.models

data class GithubRepo(

    val cloneUrl: String,
    val createdAt: String,
    val defaultBranch: String,
    val description: String?,
    val disabled: Boolean,
    val downloadsUrl: String,
    val fork: Boolean,
    val forks: Int,
    val forksCount: Int,
    val fullName: String,
    val gitUrl: String,
    val homepage: String?,
    val htmlUrl: String,
    val id: Int,
    val language: String?,
    val license: License?,
    val name: String,
    val openIssues: Int,
    val openIssuesCount: Int,
    val owner: Owner?,
    val permissions: Permissions?,
    val size: Int,
    val updatedAt: String,
    val url: String,
    val watchers: Int,
    val watchersCount: Int
)

