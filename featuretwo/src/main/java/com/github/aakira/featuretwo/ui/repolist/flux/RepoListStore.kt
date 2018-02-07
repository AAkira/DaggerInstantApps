package com.github.aakira.featuretwo.ui.repolist.flux

import com.github.aakira.daggerinstantapps.ui.CommonStore

class RepoListStore(private val dispatcher: RepoListDispatcher) : CommonStore(dispatcher) {

    fun githubRepoList() = dispatcher.githubRepoListProcessor
            .map { it.sortedByDescending { it.stargazersCount } }
}