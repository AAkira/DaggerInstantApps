package com.github.aakira.featuretwo.ui.issue.flux

import com.github.aakira.daggerinstantapps.ui.CommonStore

class IssueStore(private val dispatcher: IssueDispatcher) : CommonStore(dispatcher) {

    fun issues() = dispatcher.issuesProcessor
}