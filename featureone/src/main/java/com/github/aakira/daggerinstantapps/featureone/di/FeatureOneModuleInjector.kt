package com.github.aakira.daggerinstantapps.featureone.di

import com.github.aakira.daggerinstantapps.di.AppComponent
import com.github.aakira.daggerinstantapps.di.BaseModuleInjector
import com.github.aakira.daggerinstantapps.di.DaggerComponent
import dagger.android.AndroidInjector

object FeatureOneModuleInjector : BaseModuleInjector() {

    override fun moduleInjector(daggerComponent: DaggerComponent): AndroidInjector<out BaseModuleInjector> {
        return DaggerFeatureOneComponent.builder()
                .appComponent(daggerComponent as AppComponent)
                .build()
    }
}