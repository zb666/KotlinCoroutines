package com.example.bod.kotlincoroutines.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.example.bod.kotlincoroutines.utils.SoundHelper
import timber.log.Timber

class KotlinApp:Application() {

    companion object{
        public lateinit var application:Application
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        Timber.plant(Timber.DebugTree())
        Utils.init(this)
        SoundHelper.init(this)
    }
}