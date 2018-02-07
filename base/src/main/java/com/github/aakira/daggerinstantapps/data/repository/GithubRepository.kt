package com.github.aakira.daggerinstantapps.data.repository

import com.github.aakira.daggerinstantapps.data.api.GitHubService
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(private val gitHubService: GitHubService) {

    fun getRepositories(userName: String) = gitHubService.getUserRepositories(userName)
            .subscribeOn(Schedulers.io())

    fun getRepository(ownerName: String, repositoryName: String) = gitHubService.getRepository(ownerName, repositoryName)
            .subscribeOn(Schedulers.io())

    fun getReadMe(ownerName: String, repositoryName: String) = gitHubService.getReadMe(ownerName, repositoryName)
            .subscribeOn(Schedulers.io())

    fun getRepositoryIssues(ownerName: String, repositoryName: String) = gitHubService.getRepositoryIssues(ownerName, repositoryName)
            .subscribeOn(Schedulers.io())

    fun getRepositoryPullRequests(ownerName: String, repositoryName: String) = gitHubService.getRepositoryPullRequests(ownerName, repositoryName)
            .subscribeOn(Schedulers.io())
}