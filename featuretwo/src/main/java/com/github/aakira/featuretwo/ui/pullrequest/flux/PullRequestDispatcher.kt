package com.github.aakira.featuretwo.ui.pullrequest.flux

import com.github.aakira.daggerinstantapps.data.model.PullRequest
import com.github.aakira.daggerinstantapps.ui.CommonDispatcher
import com.github.aakira.daggerinstantapps.ui.DispatcherProvider
import io.reactivex.processors.BehaviorProcessor

class PullRequestDispatcher(provider: DispatcherProvider) : CommonDispatcher(provider) {

    val pullRequestsProcessor = BehaviorProcessor.create<List<PullRequest>>().toSerialized()
}