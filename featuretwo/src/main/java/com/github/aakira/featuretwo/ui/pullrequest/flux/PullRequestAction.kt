package com.github.aakira.featuretwo.ui.pullrequest.flux

import com.github.aakira.daggerinstantapps.data.repository.GithubRepository

class PullRequestAction(
        private val dispatcher: PullRequestDispatcher,
        private val githubRepository: GithubRepository
) {

    fun getPullRequestList(ownerName: String, repositoryName: String) {
        githubRepository.getRepositoryPullRequests(ownerName, repositoryName)
                .subscribe({
                    dispatcher.pullRequestsProcessor.onNext(it)
                }, {
                    dispatcher.onError(this, it)
                })
    }
}