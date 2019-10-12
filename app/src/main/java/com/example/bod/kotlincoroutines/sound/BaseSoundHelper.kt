package com.example.bod.kotlincoroutines.sound

import android.annotation.SuppressLint
import android.content.Context
import android.media.SoundPool
import androidx.annotation.RawRes
import com.example.bod.kotlincoroutines.app.KotlinApp
import timber.log.Timber

/**
 *
 * @ClassName: BaseSoundHelper
 * @Description:
 * @CreateDate: 2019/10/11
 */
@SuppressLint("NewApi")
abstract class BaseSoundHelper {

    private val soundPool: SoundPool by lazy {
        SoundPool.Builder().setMaxStreams(5).build()
    }

    private companion object{
        const val NO_LOOP = 0
        const val LOOP_FOREVER = -1
    }


    abstract val soundResources: Map<Int, SoundResource>

    private var currentID = 0

    private fun initSoundRes() {
        soundResources.forEach {
            it.value.soundId = soundPool.load(KotlinApp.application, it.value.soundRes, 1)
        }
    }

    init {
        initSoundRes()
    }


    open fun play(id: Int, loop: Boolean = false) {
        if (currentID > 0) stop()
        //(0 = no loop, -1 = loop forever
        soundPool.setVolume(id, 0.5f, 0.5f)
        currentID = soundPool.play(id, 1f, 1f, 0,
                if (loop) LOOP_FOREVER else NO_LOOP, 1f)
    }

    fun release() {
        soundPool.release()
    }

    fun resume() {
        soundPool.autoResume()
    }

    fun setVolume(volume: Float) {
        soundPool.setVolume(currentID, volume, volume)
    }

    fun stop() {
        try {
            soundPool.stop(currentID)
            Timber.d("stop  id is $currentID")
        } catch (e: Exception) {
            Timber.d("stop sound pool exception$e")
            e.printStackTrace()
        }
    }

    class SoundResource(@RawRes val soundRes: Int, var soundId: Int)
}