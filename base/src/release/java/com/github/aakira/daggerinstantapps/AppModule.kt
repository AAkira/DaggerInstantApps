package com.github.aakira.daggerinstantapps

import com.github.aakira.daggerinstantapps.data.ReleaseDataModule
import com.github.aakira.daggerinstantapps.di.BaseAppModules
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [BaseAppModules::class, ReleaseDataModule::class])
internal object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideAppLifecycleCallbacks(): AppLifecycleCallbacks = ReleaseAppLifecycleCallbacks()
}