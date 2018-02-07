package com.github.aakira.daggerinstantapps

import android.content.Context
import com.github.aakira.daggerinstantapps.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class App : DaggerApplication() {

    companion object {
        fun appComponent(context: Context) = (context.applicationContext as App).appComponent
    }

    private val appComponent = DaggerAppComponent.builder().application(this).build()

    @Inject lateinit var appLifecycleCallbacks: AppLifecycleCallbacks

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            appComponent.apply { inject(this@App) }

    override fun onCreate() {
        super.onCreate()
        appLifecycleCallbacks.onCreate(this)
    }

    override fun onTerminate() {
        appLifecycleCallbacks.onTerminate(this)
        super.onTerminate()
    }
}