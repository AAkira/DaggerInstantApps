package com.github.aakira.featuretwo.ui.issue.flux

import com.github.aakira.daggerinstantapps.data.model.Issue
import com.github.aakira.daggerinstantapps.ui.CommonDispatcher
import com.github.aakira.daggerinstantapps.ui.DispatcherProvider
import io.reactivex.processors.BehaviorProcessor

class IssueDispatcher(provider: DispatcherProvider) : CommonDispatcher(provider) {

    val issuesProcessor = BehaviorProcessor.create<List<Issue>>().toSerialized()
}