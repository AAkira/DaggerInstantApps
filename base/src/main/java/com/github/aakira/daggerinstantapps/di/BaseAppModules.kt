package com.github.aakira.daggerinstantapps.di

import com.github.aakira.daggerinstantapps.ui.DispatcherProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Common app modules(debug and release)
 */
@Module
class BaseAppModules {

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DispatcherProvider()
}