package com.github.aakira.featuretwo.di

import com.github.aakira.daggerinstantapps.di.AppComponent
import com.github.aakira.daggerinstantapps.di.BaseModuleInjector
import dagger.android.AndroidInjector

object FeatureTwoModuleInjector : BaseModuleInjector() {

    override fun moduleInjector(appComponent: AppComponent): AndroidInjector<out BaseModuleInjector> {
        return DaggerFeatureTwoComponent.builder()
                .appComponent(appComponent)
                .build()
    }
}