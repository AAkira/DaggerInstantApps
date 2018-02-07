package com.github.aakira.daggerinstantapps.featureone.ui.repo.flux

import com.github.aakira.daggerinstantapps.ui.CommonStore

class RepoStore(private val dispatcher: RepoDispatcher) : CommonStore(dispatcher) {

    fun githubRepo() = dispatcher.githubRepoProcessor

    fun readMe() = dispatcher.readMeProcessor
}