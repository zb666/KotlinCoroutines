package com.example.bod.kotlincoroutines.anim

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ViewAnimator
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_anim.*
import timber.log.Timber

/**
 *
 * @ClassName: AnimActivityt
 * @Description:
 * @CreateDate: 2020/1/28
 */
class AnimActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)

        tv_context.setOnClickListener {
           it.animate().alpha(0.5f)
                   .apply {
                       duration = 2500
                   }.start()

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                it.animate()
//                        .alpha(0.5f)
//                        .setDuration(1500)
//                        .scaleX(0.5f)
//                        .scaleY(0.5f)
//                        .setInterpolator(AccelerateDecelerateInterpolator())
//                        .setUpdateListener {
//                            it.animatedFraction.apply {
//                                Timber.d("BobAnim: Fraction $this")
//                            }
//                            it.animatedValue.apply {
//                                Timber.d("BobAnim: Value $this")
//                            }
//                        }.start()
//            }
        }
    }
}