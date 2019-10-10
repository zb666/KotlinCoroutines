package com.example.bod.kotlincoroutines.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.bod.kotlincoroutines.R
import kotlinx.android.synthetic.main.layout_train_plan.view.*

/**
 *
 * @ClassName: TrainCircleView
 * @Description:
 * @CreateDate: 2019/10/10
 */
class TrainCircleView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_train_plan,this,true)
    }

    fun setProgress(animatedValue: Int){
        tvFinishedDay.text = "$animatedValue/21天"
    }

}