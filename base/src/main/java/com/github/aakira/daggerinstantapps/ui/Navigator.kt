package com.github.aakira.daggerinstantapps.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.github.aakira.daggerinstantapps.R
import com.github.aakira.daggerinstantapps.data.model.GithubRepo

const val KEY_REPO_GITHUB_REPO = "key_repo_github_repo"

fun Activity.startRepoActivity(githubRepo: GithubRepo) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(githubRepo.buildRepositoryUrl(this))).apply {
        `package` = packageName
        addCategory(Intent.CATEGORY_BROWSABLE)
        putExtra(KEY_REPO_GITHUB_REPO, githubRepo)
    })
}

fun Activity.startIssueActivity(githubRepo: GithubRepo) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("${githubRepo.buildRepositoryUrl(this)}/issues")).apply {
        `package` = packageName
        addCategory(Intent.CATEGORY_BROWSABLE)
        putExtra(KEY_REPO_GITHUB_REPO, githubRepo)
    })
}

fun Activity.startPullRequestActivity(githubRepo: GithubRepo) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("${githubRepo.buildRepositoryUrl(this)}/pulls")).apply {
        `package` = packageName
        addCategory(Intent.CATEGORY_BROWSABLE)
        putExtra(KEY_REPO_GITHUB_REPO, githubRepo)
    })
}

private fun GithubRepo.buildRepositoryUrl(context: Context) = "${context.getString(R.string.base_url)}/${owner.login}/$name"