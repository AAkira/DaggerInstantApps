package com.github.aakira.featuretwo.ui.repolist.flux

import com.github.aakira.daggerinstantapps.data.repository.GithubRepository
import timber.log.Timber

class RepoListAction (
        private val dispatcher: RepoListDispatcher,
        private val githubRepository: GithubRepository
) {

    fun getRepoList(userName: String) {
        githubRepository.getRepositories(userName)
                .subscribe({
                    dispatcher.githubRepoListProcessor.onNext(it)
                }, {
                    Timber.e(it)
                })
    }
}