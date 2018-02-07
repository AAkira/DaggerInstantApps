package com.github.aakira.featuretwo.ui.issue

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.aakira.daggerinstantapps.data.model.GithubRepo
import com.github.aakira.daggerinstantapps.ui.AbstractActivity
import com.github.aakira.daggerinstantapps.ui.KEY_REPO_GITHUB_REPO
import com.github.aakira.daggerinstantapps.util.ext.showEmptywToast
import com.github.aakira.daggerinstantapps.util.ext.showErrorToast
import com.github.aakira.featuretwo.R
import com.github.aakira.featuretwo.di.FeatureTwoModuleInjector
import com.github.aakira.featuretwo.ui.issue.flux.IssueAction
import com.github.aakira.featuretwo.ui.issue.flux.IssueStore
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class IssueListActivity : AbstractActivity() {

    @Inject lateinit var issueAction: IssueAction
    @Inject lateinit var issueStore: IssueStore

    override fun onCreate(savedInstanceState: Bundle?) {
        FeatureTwoModuleInjector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)

        issueStore.issues()
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe {
                    if (it.isEmpty()) showEmptywToast()

                    recyclerView.adapter = IssueRecyclerAdapter(it) {
                        showErrorToast("Not implemented")
                    }
                }

        intent.getParcelableExtra<GithubRepo>(KEY_REPO_GITHUB_REPO)?.let {
            issueAction.getIssueList(it.owner.login, it.name)
        } ?: run {
            try {
                val path = intent.data.path.split("/")
                issueAction.getIssueList(path[1], path[2])
            } catch (e: Exception) {
                showErrorToast()
            }
        }
    }
}