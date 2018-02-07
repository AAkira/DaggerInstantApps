package com.github.aakira.featuretwo.ui.repolist.flux

import com.github.aakira.daggerinstantapps.data.model.GithubRepo
import com.github.aakira.daggerinstantapps.ui.CommonDispatcher
import com.github.aakira.daggerinstantapps.ui.DispatcherProvider
import io.reactivex.processors.BehaviorProcessor

class RepoListDispatcher (provider: DispatcherProvider) : CommonDispatcher(provider) {

    val githubRepoListProcessor = BehaviorProcessor.create<List<GithubRepo>>().toSerialized()
}