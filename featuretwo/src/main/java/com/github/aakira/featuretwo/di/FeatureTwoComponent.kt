package com.github.aakira.featuretwo.di

import com.github.aakira.daggerinstantapps.di.AppComponent
import com.github.aakira.daggerinstantapps.di.PerModuleScope
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerModuleScope
@Component(
        dependencies = [AppComponent::class],
        modules = [
            AndroidSupportInjectionModule::class,
            FeatureTwoUiBuilder::class,
            FeatureTwoModule::class
        ]
)
interface FeatureTwoComponent : AndroidInjector<FeatureTwoModuleInjector> {

}