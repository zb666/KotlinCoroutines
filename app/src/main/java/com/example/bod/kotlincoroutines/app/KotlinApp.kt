package com.example.bod.kotlincoroutines.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.example.bod.kotlincoroutines.activity.ktlist.globalMoudle
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class KotlinApp:Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Utils.init(this)
        startKoin {
            androidContext(this@KotlinApp)
            modules(globalMoudle)
        }
    }
}