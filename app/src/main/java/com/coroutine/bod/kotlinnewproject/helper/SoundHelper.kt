package com.coroutine.bod.kotlinnewproject.helper

import android.annotation.SuppressLint
import android.content.Context
import android.media.SoundPool
import com.coroutine.bod.kotlinnewproject.R

object SoundHelper {


    const val HOME_WORK_COMPLETE = 28

    @SuppressLint("NewApi")
    private val soundPool = SoundPool.Builder().setMaxStreams(HOME_WORK_COMPLETE + 1).build()

    fun init(context: Context) {
        soundPool.load(context, R.raw.popup, 1)
        soundPool.load(context, R.raw.scroll, 1)
    }

    fun play(id:Int){
        soundPool.setVolume(id,0.5f,0.5f)
        soundPool.play(id,1f,1f,0,0,1f)
    }


}