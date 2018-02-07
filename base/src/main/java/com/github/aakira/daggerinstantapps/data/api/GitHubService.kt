package com.github.aakira.daggerinstantapps.data.api

import com.github.aakira.daggerinstantapps.data.model.GithubRepo
import com.github.aakira.daggerinstantapps.data.model.Issue
import com.github.aakira.daggerinstantapps.data.model.PullRequest
import com.github.aakira.daggerinstantapps.data.model.ReadMe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    // https://developer.github.com/v3/repos/#list-user-repositories
    @GET("/users/{user}/repos")
    fun getUserRepositories(@Path("user") userName: String): Single<List<GithubRepo>>

    // https://developer.github.com/v3/repos/#get
    @GET("/repos/{owner}/{repo}")
    fun getRepository(@Path("owner") ownerName: String, @Path("repo") repositoryName: String): Single<GithubRepo>

    @GET("/repos/{owner}/{repo}/readme")
    fun getReadMe(@Path("owner") ownerName: String, @Path("repo") repositoryName: String): Single<ReadMe>

    // https://developer.github.com/v3/issues/#list-issues-for-a-repository
    @GET("/repos/{owner}/{repo}/issues")
    fun getRepositoryIssues(@Path("owner") ownerName: String, @Path("repo") repositoryName: String): Single<List<Issue>>

    // https://developer.github.com/v3/pulls/#list-pull-requests
    @GET("/repos/{owner}/{repo}/pulls")
    fun getRepositoryPullRequests(@Path("owner") ownerName: String, @Path("repo") repositoryName: String): Single<List<PullRequest>>
}