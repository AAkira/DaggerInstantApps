package com.github.aakira.daggerinstantapps.featureone.ui.repo.flux

import com.github.aakira.daggerinstantapps.data.model.GithubRepo
import com.github.aakira.daggerinstantapps.data.model.ReadMe
import com.github.aakira.daggerinstantapps.ui.CommonDispatcher
import com.github.aakira.daggerinstantapps.ui.DispatcherProvider
import io.reactivex.processors.BehaviorProcessor

class RepoDispatcher(provider: DispatcherProvider) : CommonDispatcher(provider) {

    val githubRepoProcessor = BehaviorProcessor.create<GithubRepo>().toSerialized()
    val readMeProcessor = BehaviorProcessor.create<ReadMe>().toSerialized()
}