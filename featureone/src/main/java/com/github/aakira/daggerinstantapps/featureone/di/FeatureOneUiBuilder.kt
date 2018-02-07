package com.github.aakira.daggerinstantapps.featureone.di

import com.github.aakira.daggerinstantapps.di.PerUiScope
import com.github.aakira.daggerinstantapps.featureone.ui.repo.RepoActivity
import com.github.aakira.daggerinstantapps.featureone.ui.repo.di.RepoModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FeatureOneUiBuilder {

    @PerUiScope
    @ContributesAndroidInjector(modules = [RepoModule::class])
    internal abstract fun bindRepoActivity(): RepoActivity
}