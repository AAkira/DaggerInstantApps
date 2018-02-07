package com.github.aakira.daggerinstantapps.featureone.ui.repo.flux

import com.github.aakira.daggerinstantapps.data.model.GithubRepo
import com.github.aakira.daggerinstantapps.data.repository.GithubRepository

class RepoAction(
        private val dispatcher: RepoDispatcher,
        private val githubRepository: GithubRepository
) {

    fun getRepo(ownerName: String, repositoryName: String) {
        githubRepository.getRepository(ownerName, repositoryName)
                .subscribe({
                    dispatcher.githubRepoProcessor.onNext(it)
                }, {
                    dispatcher.onError(this, it)
                })
    }

    fun refreshRepo(githubRepo: GithubRepo) {
        dispatcher.githubRepoProcessor.onNext(githubRepo)
        getReadMe(githubRepo.owner.login, githubRepo.name)
    }

    fun getReadMe(ownerName: String, repositoryName: String) {
        githubRepository.getReadMe(ownerName, repositoryName)
                .subscribe({
                    dispatcher.readMeProcessor.onNext(it)
                }, {
                    dispatcher.onError(this, it)
                })
    }
}