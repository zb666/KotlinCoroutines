package com.coroutine.bod.kotlinnewproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieCompositionFactory
import com.coroutine.bod.kotlinnewproject.activity.OtherActivity
import com.coroutine.bod.kotlinnewproject.helper.SoundHelper
import com.coroutine.bod.kotlinnewproject.helper.SoundHelper.FOCUSPLANET_ENERGY
import com.coroutine.bod.kotlinnewproject.helper.SoundHelper.FOCUSPLANET_GET_COIN
import com.coroutine.bod.kotlinnewproject.jetpack.MainViewModel
import com.coroutine.bod.kotlinnewproject.sealed.KotlinSealed
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.*
import log
import timber.log.Timber

class MainActivity : AppCompatActivity() {


    var test: Float = 3.12345f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOne.setOnClickListener {
            SoundHelper.play(4)
        }

        lt_slide.addAnimatorUpdateListener {
        }

        Timber.d("start to log")

//        lt_mail.setAnimation("buble.json",LottieAnimationView.DRAWING_CACHE_QUALITY_AUTO)
        lt_mail.loop(true)
        lt_mail.playAnimation()


        val mapOf = mapOf(
                111 to 20,
                112 to 30,
                115 to 23
        )
        tvOne.text = mapOf[111].toString()

        tv222.setOnClickListener {
            //            sealedFun(KotlinSealed.SealedB())
            SoundHelper.play(5)
        }

        tv333.setOnClickListener {
            la_entry.setMinFrame(0)
            la_entry.playAnimation()
            la_entry.addAnimatorUpdateListener {
                log("${la_entry.minFrame}" + " " + la_entry.maxFrame + " " + la_entry.frame)
            }
            SoundHelper.play(6)
        }

        tv444.setOnClickListener {
        }

        tvOne.text = resources.getString(R.string.appaar)

        GlobalScope.launch {
            log("111")
            delay(5000)
            log("333")
            log("444")
        }
        log("222")

        tv555.setOnClickListener {
            GlobalScope.launch(context = Dispatchers.Main) {
                la_entry.playAnimation()
                SoundHelper.play(34)
                delay(300)
                SoundHelper.play(FOCUSPLANET_ENERGY)
                la_entry.playAnimation()
                delay(100)
                SoundHelper.play(35)

            }

        }


    }


    fun sealedFun(sealed: KotlinSealed) = when (sealed) {
        is KotlinSealed.SealedA -> {
        }
        is KotlinSealed.SealedB -> {
        }
        is KotlinSealed.SealedC -> {
        }
    }

    fun simpleFun(int: Int) {
        when (int) {
            1 -> {
            }
            2 -> {

            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

}
