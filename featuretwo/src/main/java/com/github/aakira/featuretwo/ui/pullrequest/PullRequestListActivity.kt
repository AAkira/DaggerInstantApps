package com.github.aakira.featuretwo.ui.pullrequest

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
import com.github.aakira.featuretwo.ui.pullrequest.flux.PullRequestAction
import com.github.aakira.featuretwo.ui.pullrequest.flux.PullRequestStore
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PullRequestListActivity : AbstractActivity() {

    @Inject lateinit var pullRequestAction: PullRequestAction
    @Inject lateinit var pullRequestStore: PullRequestStore

    override fun onCreate(savedInstanceState: Bundle?) {
        FeatureTwoModuleInjector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)

        pullRequestStore.pullRequests()
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe {
                    if (it.isEmpty()) showEmptywToast()

                    recyclerView.adapter = PullRequestRecyclerAdapter(it) {
                        showErrorToast("Not implemented")
                    }
                }

        intent.getParcelableExtra<GithubRepo>(KEY_REPO_GITHUB_REPO)?.let {
            pullRequestAction.getPullRequestList(it.owner.login, it.name)
        } ?: run {
            try {
                val path = intent.data.path.split("/")
                pullRequestAction.getPullRequestList(path[1], path[2])
            } catch (e: Exception) {
                showErrorToast()
            }
        }
    }
}