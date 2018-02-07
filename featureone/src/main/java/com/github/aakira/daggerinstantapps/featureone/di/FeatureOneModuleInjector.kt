package com.github.aakira.daggerinstantapps.featureone.di

import com.github.aakira.daggerinstantapps.di.AppComponent
import com.github.aakira.daggerinstantapps.di.BaseModuleInjector
import dagger.android.AndroidInjector

object FeatureOneModuleInjector : BaseModuleInjector() {

    override fun moduleInjector(appComponent: AppComponent): AndroidInjector<out BaseModuleInjector> {
        return DaggerFeatureOneComponent.builder()
                .appComponent(appComponent)
                .build()
    }
}