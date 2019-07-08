package com.coroutine.bod.kotlinnewproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieCompositionFactory
import com.coroutine.bod.kotlinnewproject.activity.OtherActivity
import com.coroutine.bod.kotlinnewproject.jetpack.MainViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import log
import org.json.JSONObject
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import kotlin.math.log

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOne.setOnClickListener {
            la_entry.playAnimation()
        }

        lt_slide.addAnimatorUpdateListener {
            //            log(it.animatedFraction.toString())
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

    }

}
