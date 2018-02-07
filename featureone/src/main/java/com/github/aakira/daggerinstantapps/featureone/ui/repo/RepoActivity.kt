package com.github.aakira.daggerinstantapps.featureone.ui.repo

import android.os.Bundle
import android.webkit.WebView
import android.widget.TextView
import com.github.aakira.daggerinstantapps.data.model.GithubRepo
import com.github.aakira.daggerinstantapps.featureone.R
import com.github.aakira.daggerinstantapps.featureone.di.FeatureOneModuleInjector
import com.github.aakira.daggerinstantapps.featureone.ui.repo.flux.RepoAction
import com.github.aakira.daggerinstantapps.featureone.ui.repo.flux.RepoStore
import com.github.aakira.daggerinstantapps.ui.AbstractActivity
import com.github.aakira.daggerinstantapps.ui.KEY_REPO_GITHUB_REPO
import com.github.aakira.daggerinstantapps.ui.startIssueActivity
import com.github.aakira.daggerinstantapps.ui.startPullRequestActivity
import com.github.aakira.daggerinstantapps.util.ToastUtil
import com.github.aakira.daggerinstantapps.util.ext.showErrorToast
import com.google.android.instantapps.InstantApps
import com.jakewharton.rxbinding2.view.clicks
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.withLatestFrom
import javax.inject.Inject

class RepoActivity : AbstractActivity() {

    @Inject lateinit var repoAction: RepoAction
    @Inject lateinit var repoStore: RepoStore

    override fun onCreate(savedInstanceState: Bundle?) {
        FeatureOneModuleInjector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        val titleText: TextView = findViewById(R.id.titleText)
        val descriptionText: TextView = findViewById(R.id.descriptionText)
        val issueButton: TextView = findViewById(R.id.issueButton)
        val prButton: TextView = findViewById(R.id.prButton)
        val starText: TextView = findViewById(R.id.starText)
        val readmeView: WebView = findViewById(R.id.readmeView)

        issueButton.clicks()
                .toFlowable(BackpressureStrategy.LATEST)
                .withLatestFrom(repoStore.githubRepo()) { _, repo -> repo }
                .bindToLifecycle(this)
                .subscribe {
                    if (InstantApps.isInstantApp(this)) showInstantAppsToast()
                    else startIssueActivity(it)
                }

        prButton.clicks()
                .toFlowable(BackpressureStrategy.LATEST)
                .withLatestFrom(repoStore.githubRepo()) { _, repo -> repo }
                .bindToLifecycle(this)
                .subscribe {
                    if (InstantApps.isInstantApp(this)) showInstantAppsToast()
                    else startPullRequestActivity(it)
                }

        repoStore.githubRepo()
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe {
                    supportActionBar?.title = it.fullName
                    titleText.text = it.fullName
                    descriptionText.text = it.description
                    starText.text = it.stargazersCount.toString()
                }

        repoStore.readMe()
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe {
                    readmeView.loadUrl(it.htmlUrl)
                    readmeView.scrollY = 300 // scroll position at first
                }

        repoStore.errors()
                .filter { it.action is RepoAction }
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe {
                    showErrorToast()
                }

        intent.getParcelableExtra<GithubRepo>(KEY_REPO_GITHUB_REPO)?.let {
            repoAction.refreshRepo(it)
        } ?: run {
            try {
                val path = intent.data.path.split("/")
                repoAction.getRepo(path[1], path[2])
                repoAction.getReadMe(path[1], path[2])
            } catch (e: Exception) {
                showErrorToast()
            }
        }
    }

    private fun showInstantAppsToast() {
        ToastUtil.showToast(this, "InstantApps not support")
    }
}