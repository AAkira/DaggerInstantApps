package com.github.aakira.featuretwo.ui.issue.flux

import com.github.aakira.daggerinstantapps.data.repository.GithubRepository

class IssueAction(
        private val dispatcher: IssueDispatcher,
        private val githubRepository: GithubRepository
) {

    fun getIssueList(ownerName: String, repositoryName: String) {
        githubRepository.getRepositoryIssues(ownerName, repositoryName)
                .subscribe({
                    dispatcher.issuesProcessor.onNext(it)
                }, {
                    dispatcher.onError(this, it)
                })
    }
}