package com.github.aakira.daggerinstantapps

import android.app.Application

class ReleaseAppLifecycleCallbacks : AppLifecycleCallbacks {

    override fun onCreate(application: Application) {
//        Timber.plant(CrashlyticsTree())
    }

    override fun onTerminate(application: Application) {

    }
}