package com.github.aakira.daggerinstantapps.di

import com.github.aakira.daggerinstantapps.data.api.GitHubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Common app modules(debug and release)
 */
@Module
internal object ApiModules {

    @JvmStatic
    @Singleton
    @Provides
    fun provideGitHubService(retrofit: Retrofit) = retrofit.create(GitHubService::class.java)
}