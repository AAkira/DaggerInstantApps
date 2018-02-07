package com.github.aakira.featuretwo.ui.pullrequest.di

import com.github.aakira.daggerinstantapps.data.repository.GithubRepository
import com.github.aakira.daggerinstantapps.di.PerUiScope
import com.github.aakira.daggerinstantapps.ui.DispatcherProvider
import com.github.aakira.featuretwo.ui.pullrequest.flux.PullRequestAction
import com.github.aakira.featuretwo.ui.pullrequest.flux.PullRequestDispatcher
import com.github.aakira.featuretwo.ui.pullrequest.flux.PullRequestStore
import dagger.Module
import dagger.Provides

@Module
class PullRequestModule {

    @PerUiScope
    @Provides
    fun providePullRequestDispatcher(provider: DispatcherProvider) = PullRequestDispatcher(provider)

    @PerUiScope
    @Provides
    fun providePullRequestAction(mainDispatcher: PullRequestDispatcher, githubRepository: GithubRepository) =
            PullRequestAction(mainDispatcher, githubRepository)

    @PerUiScope
    @Provides
    fun providePullRequestStore(mainDispatcher: PullRequestDispatcher) = PullRequestStore(mainDispatcher)
}