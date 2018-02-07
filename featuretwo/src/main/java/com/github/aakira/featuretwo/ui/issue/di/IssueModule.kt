package com.github.aakira.featuretwo.ui.issue.di

import com.github.aakira.daggerinstantapps.data.repository.GithubRepository
import com.github.aakira.daggerinstantapps.di.PerUiScope
import com.github.aakira.daggerinstantapps.ui.DispatcherProvider
import com.github.aakira.featuretwo.ui.issue.flux.IssueAction
import com.github.aakira.featuretwo.ui.issue.flux.IssueDispatcher
import com.github.aakira.featuretwo.ui.issue.flux.IssueStore
import dagger.Module
import dagger.Provides

@Module
class IssueModule {

    @PerUiScope
    @Provides
    fun provideIssueDispatcher(provider: DispatcherProvider) = IssueDispatcher(provider)

    @PerUiScope
    @Provides
    fun provideIssueAction(mainDispatcher: IssueDispatcher, githubRepository: GithubRepository) =
            IssueAction(mainDispatcher, githubRepository)

    @PerUiScope
    @Provides
    fun provideIssueStore(mainDispatcher: IssueDispatcher) = IssueStore(mainDispatcher)
}