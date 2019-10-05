package com.example.bod.kotlincoroutines.utils

import android.content.Context
import android.media.SoundPool
import android.util.Log
import com.example.bod.kotlincoroutines.R

/**
 * Created by 李林敏 on 2018/4/17.
 */
object SoundHelper {

    const val TAG = "SoundHelper"
    const val POP_UP = 1
    const val ClOSE_POP = 2
    const val REACH_GOAL = 3
    const val SUCESS_TONE = 4
    const val FAIL_TONE = 5
    const val SCROLL_SOUND = 6
    const val SCROLL2_SOUND = 7
    const val SWITCH = 8
    const val REPORT1 = 9
    const val REPORT2 = 10
    const val REPORT3 = 11
    const val REPORT4 = 12
    const val ANSWER_CORRECT = 13
    const val ANSWER_WRONG = 14
    const val DING = 15
    const val TICTOK = 16
    const val WATER_DROP_GROW = 17
    const val PAPER = 18
    const val TAG_DELET = 19
    const val PENCIL = 20
    const val OBTAIN_EXP = 21
    const val ENTER_PLANT = 22
    const val COIN = 23
    const val COUNTDOWN = 24
    const val ACCELERATE = 25
    const val REWARD_DIALOG = 26
    const val LOW_ATTENTION_REMIND = 27
    const val HOME_WORK_COMPLETE = 28

    private val soundPool = SoundPool.Builder().setMaxStreams(HOME_WORK_COMPLETE).build()
    private var currentID = 0

    fun init(context: Context) {
        soundPool.load(context, R.raw.popup, 1)
        soundPool.load(context, R.raw.close_pop, 1)
        soundPool.load(context, R.raw.reach_goal, 1)
        soundPool.load(context, R.raw.sucess_tone, 1)
        soundPool.load(context, R.raw.fail_tone, 1)
        soundPool.load(context, R.raw.scroll, 1)
        soundPool.load(context, R.raw.scroll2, 1)
        soundPool.load(context, R.raw.switch_item, 1)
        soundPool.load(context, R.raw.report1, 1)
        soundPool.load(context, R.raw.report2, 1)
        soundPool.load(context, R.raw.report3, 1)
        soundPool.load(context, R.raw.report4, 1)
        soundPool.load(context, R.raw.correct, 1)
        soundPool.load(context, R.raw.wrong, 1)
        soundPool.load(context, R.raw.ding, 1)
        soundPool.load(context, R.raw.tiktok, 1)
        soundPool.load(context, R.raw.waterdrop_grow, 1)
        soundPool.load(context, R.raw.paper, 1)
        soundPool.load(context, R.raw.tag_delet, 1)
        soundPool.load(context, R.raw.pencil, 1)
        soundPool.load(context, R.raw.obtain_exp, 1)
        soundPool.load(context, R.raw.enter_plant, 1)
        soundPool.load(context, R.raw.coin, 1)
        soundPool.load(context, R.raw.countdown, 1)
        soundPool.load(context, R.raw.accelerate, 1)
        soundPool.load(context, R.raw.show_reward_dialog, 1)
        soundPool.load(context, R.raw.low_attention_remind, 1)
        soundPool.load(context, R.raw.homework_complete, 1)

    }

    fun play(id: Int) {
        if (currentID > 0) {
            stop()
        }

        if (currentID == ANSWER_CORRECT) {
        }

        soundPool.setVolume(id, 0.5f, 0.5f)
        currentID = soundPool.play(id, 1f, 1f, 0, 0, 1f)
        Log.i(TAG, " play id is $currentID")
    }

    fun play(id: Int, loop: Boolean) {

        if (!loop) {
            play(id)
        } else {
            if (currentID > 0) {
                stop()
            }
            soundPool.setVolume(id, 0.5f, 0.5f)
            currentID = soundPool.play(id, 1f, 1f, 0, -1, 1f)
            Log.i(TAG, "loop play id is $currentID")
        }
    }

    fun pause() {
        soundPool.autoPause()
    }

    fun resume() {
        soundPool.autoResume()
    }

    fun setVolume(volume: Float) {
        soundPool.setVolume(currentID, volume, volume)
    }

    fun stop() {
        if (soundPool == null) {
            return
        }

        try {
            soundPool.stop(currentID)
            Log.i(TAG, "stop  id is $currentID")

        } catch (e: Exception) {
            Log.i(TAG, "stop sound pool exception" + e.toString())
            e.printStackTrace()
        }

    }


}
