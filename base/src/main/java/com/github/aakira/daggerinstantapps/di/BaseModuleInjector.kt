package com.github.aakira.daggerinstantapps.di

import android.app.Activity
import android.app.Fragment
import android.app.Service
import android.content.BroadcastReceiver
import android.content.ContentProvider
import android.content.Context
import com.github.aakira.daggerinstantapps.App
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasBroadcastReceiverInjector
import dagger.android.HasContentProviderInjector
import dagger.android.HasFragmentInjector
import dagger.android.HasServiceInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import android.support.v4.app.Fragment as SupportFragment

/**
 * Cf. [dagger.android.DaggerApplication]
 */
abstract class BaseModuleInjector : HasActivityInjector,
        HasFragmentInjector,
        HasSupportFragmentInjector,
        HasServiceInjector,
        HasBroadcastReceiverInjector,
        HasContentProviderInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var fragmentSupportInject: DispatchingAndroidInjector<SupportFragment>
    @Inject lateinit var serviceInjector: DispatchingAndroidInjector<Service>
    @Inject lateinit var broadcastReceiverInjector: DispatchingAndroidInjector<BroadcastReceiver>
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var contentProviderInjector: DispatchingAndroidInjector<ContentProvider>

    private var needToInject = true

    abstract fun moduleInjector(appComponent: AppComponent): AndroidInjector<out BaseModuleInjector>

    fun inject(dependerContext: Context) {
        injectIfNecessary(App.appComponent(dependerContext))
        when (dependerContext) {
            is Activity -> activityInjector.inject(dependerContext)
            is SupportFragment -> fragmentSupportInject.inject(dependerContext)
            is Service -> serviceInjector.inject(dependerContext)
            is BroadcastReceiver -> broadcastReceiverInjector.inject(dependerContext)
            is Fragment -> fragmentInjector.inject(dependerContext)
            is ContentProvider -> contentProviderInjector.inject(dependerContext)
        }
    }

    private fun injectIfNecessary(appComponent: AppComponent) {
        if (needToInject) {
            synchronized(this) {
                if (needToInject) {
                    val moduleInjector = moduleInjector(appComponent) as AndroidInjector<BaseModuleInjector>
                    moduleInjector.inject(this)
                    if (needToInject) {
                        throw IllegalStateException(
                                "The AndroidInjector returned from applicationInjector() did not inject the " + "DaggerApplication")
                    }
                }
            }
        }
    }

    @Inject
    internal fun setInjected() {
        needToInject = false
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector

    override fun fragmentInjector(): DispatchingAndroidInjector<Fragment> = fragmentInjector

    override fun broadcastReceiverInjector(): DispatchingAndroidInjector<BroadcastReceiver> = broadcastReceiverInjector

    override fun serviceInjector(): DispatchingAndroidInjector<Service> = serviceInjector

    override fun contentProviderInjector(): DispatchingAndroidInjector<ContentProvider> = contentProviderInjector

    override fun supportFragmentInjector(): DispatchingAndroidInjector<android.support.v4.app.Fragment> = fragmentSupportInject
}