package com.coroutine.bod.kotlinnewproject

import android.app.Application
import android.media.SoundPool
import com.coroutine.bod.kotlinnewproject.helper.SoundHelper
import timber.log.Timber

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        SoundHelper.init(this)
    }
}