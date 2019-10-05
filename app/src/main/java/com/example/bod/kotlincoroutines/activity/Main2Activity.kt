package com.example.bod.kotlincoroutines.activity

import android.animation.ValueAnimator
import android.animation.ValueAnimator.RESTART
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.utils.SoundHelper
import com.example.bod.kotlincoroutines.utils.SoundHelper.DING
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.view_progress.*
import kotlinx.android.synthetic.main.view_progress.view.*
import kotlinx.android.synthetic.main.view_progress.view.progressBar
import timber.log.Timber

/**
 * @ClassName: Main2Activity
 * @Description:
 * @CreateDate: 2019/9/29
 */
class Main2Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        ValueAnimator.ofInt(0, 17)
                .apply {
                    addUpdateListener {
                        val animatedValue = (it.animatedValue as Int) / 21
                        progressBar.progress = animatedValue.toFloat()
                    }
                    repeatMode = RESTART
                    duration = 1500
                }.start()
    }


}
