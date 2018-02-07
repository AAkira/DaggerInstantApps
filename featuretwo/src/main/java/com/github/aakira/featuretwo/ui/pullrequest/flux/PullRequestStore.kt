package com.github.aakira.featuretwo.ui.pullrequest.flux

import com.github.aakira.daggerinstantapps.ui.CommonStore

class PullRequestStore(private val dispatcher: PullRequestDispatcher) : CommonStore(dispatcher) {

    fun pullRequests() = dispatcher.pullRequestsProcessor
}