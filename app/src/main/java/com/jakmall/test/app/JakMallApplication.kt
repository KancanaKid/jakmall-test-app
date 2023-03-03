package com.jakmall.test.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.hilt.android.HiltAndroidApp

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 */
@HiltAndroidApp
class JakMallApplication : Application() {
    private val lifecycleCallbacks by lazy { LifeCycleCallbacks() }

    override fun onCreate() {
        super.onCreate()
    }

    private inner class LifeCycleCallbacks : ActivityLifecycleCallbacks{
        override fun onActivityCreated(p0: Activity, p1: Bundle?) {}

        override fun onActivityStarted(p0: Activity) {}

        override fun onActivityResumed(p0: Activity) {}

        override fun onActivityPaused(p0: Activity) {}

        override fun onActivityStopped(p0: Activity) {}

        override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}

        override fun onActivityDestroyed(p0: Activity) {}

    }
}