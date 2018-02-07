package com.github.aakira.featuretwo.ui.repolist.di

import com.github.aakira.daggerinstantapps.data.repository.GithubRepository
import com.github.aakira.daggerinstantapps.di.PerUiScope
import com.github.aakira.daggerinstantapps.ui.DispatcherProvider
import com.github.aakira.featuretwo.ui.repolist.flux.RepoListAction
import com.github.aakira.featuretwo.ui.repolist.flux.RepoListDispatcher
import com.github.aakira.featuretwo.ui.repolist.flux.RepoListStore
import dagger.Module
import dagger.Provides

@Module
class RepoListModule {

    @PerUiScope
    @Provides
    fun provideRepoListDispatcher(provider: DispatcherProvider) = RepoListDispatcher(provider)

    @PerUiScope
    @Provides
    fun provideRepoListAction(mainDispatcher: RepoListDispatcher, githubRepository: GithubRepository) =
            RepoListAction(mainDispatcher, githubRepository)

    @PerUiScope
    @Provides
    fun provideRepoListStore(mainDispatcher: RepoListDispatcher) = RepoListStore(mainDispatcher)
}