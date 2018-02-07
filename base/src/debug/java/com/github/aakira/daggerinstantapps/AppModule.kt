package com.github.aakira.daggerinstantapps

import com.github.aakira.daggerinstantapps.data.DebugDataModule
import com.github.aakira.daggerinstantapps.di.BaseAppModules
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [BaseAppModules::class, DebugDataModule::class])
internal object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideAppLifecycleCallbacks(): AppLifecycleCallbacks = DebugAppLifecycleCallbacks()
}