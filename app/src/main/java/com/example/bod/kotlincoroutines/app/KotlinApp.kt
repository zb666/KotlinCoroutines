package com.example.bod.kotlincoroutines.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import timber.log.Timber

class KotlinApp:Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Utils.init(this)
    }
}