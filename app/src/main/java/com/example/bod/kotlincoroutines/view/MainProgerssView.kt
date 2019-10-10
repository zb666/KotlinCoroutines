package com.example.bod.kotlincoroutines.view

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.bod.kotlincoroutines.R
import kotlinx.android.synthetic.main.view_progress.view.*

/**
 *
 * @ClassName: MainProgerssView
 * @Description:
 * @CreateDate: 2019/9/29
 */
class MainProgerssView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    init {
        LayoutInflater.from(getContext()).inflate(R.layout.view_progress,null).let {
            addView(it)
        }
    }

    fun setProgressDay(finishedDay:Int = 0){
        ValueAnimator.ofInt(finishedDay,21)
                .apply {
                    duration = 1500
                    progressBar.progress = (animatedValue as Float).toInt()
                }.start()
    }
}