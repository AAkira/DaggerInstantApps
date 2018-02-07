package com.github.aakira.featuretwo.di

import com.github.aakira.daggerinstantapps.di.PerUiScope
import com.github.aakira.featuretwo.ui.issue.IssueListActivity
import com.github.aakira.featuretwo.ui.issue.di.IssueModule
import com.github.aakira.featuretwo.ui.pullrequest.PullRequestListActivity
import com.github.aakira.featuretwo.ui.pullrequest.di.PullRequestModule
import com.github.aakira.featuretwo.ui.repolist.RepositoryListActivity
import com.github.aakira.featuretwo.ui.repolist.di.RepoListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FeatureTwoUiBuilder {

    @PerUiScope
    @ContributesAndroidInjector(modules = [IssueModule::class])
    internal abstract fun bindIssueListActivity(): IssueListActivity

    @PerUiScope
    @ContributesAndroidInjector(modules = [PullRequestModule::class])
    internal abstract fun bindPullRequestListActivity(): PullRequestListActivity

    @PerUiScope
    @ContributesAndroidInjector(modules = [RepoListModule::class])
    internal abstract fun bindRepositoryListActivity(): RepositoryListActivity
}