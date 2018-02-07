package com.github.aakira.daggerinstantapps.featureone.ui.repo.di

import com.github.aakira.daggerinstantapps.data.repository.GithubRepository
import com.github.aakira.daggerinstantapps.di.PerUiScope
import com.github.aakira.daggerinstantapps.featureone.ui.repo.flux.RepoAction
import com.github.aakira.daggerinstantapps.featureone.ui.repo.flux.RepoDispatcher
import com.github.aakira.daggerinstantapps.featureone.ui.repo.flux.RepoStore
import com.github.aakira.daggerinstantapps.ui.DispatcherProvider
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @PerUiScope
    @Provides
    fun provideRepoListDispatcher(provider: DispatcherProvider) = RepoDispatcher(provider)

    @PerUiScope
    @Provides
    fun provideRepoListAction(mainDispatcher: RepoDispatcher, githubRepository: GithubRepository) =
            RepoAction(mainDispatcher, githubRepository)

    @PerUiScope
    @Provides
    fun provideRepoListStore(mainDispatcher: RepoDispatcher) = RepoStore(mainDispatcher)
}