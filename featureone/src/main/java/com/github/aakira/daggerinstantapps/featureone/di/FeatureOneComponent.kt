package com.github.aakira.daggerinstantapps.featureone.di

import com.github.aakira.daggerinstantapps.di.AppComponent
import com.github.aakira.daggerinstantapps.di.DaggerComponent
import com.github.aakira.daggerinstantapps.di.PerModuleScope
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerModuleScope
@Component(
        dependencies = [AppComponent::class],
        modules = [
            AndroidSupportInjectionModule::class,
            FeatureOneUiBuilder::class,
            FeatureOneModule::class
        ]
)
interface FeatureOneComponent : AndroidInjector<FeatureOneModuleInjector>, DaggerComponent {

}