package com.example.bod.kotlincoroutines.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @ClassName: HorizontalView
 * @Description:
 * @CreateDate: 2019/8/26
 */
class HorizontalView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).also {
            it.color = Color.BLUE
                it.shader = SweepGradient(450f,450f,Color.RED,Color.GREEN)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(450f,450f,450f,mPaint)

        canvas?.drawLine(100f,100f,300f,300f,mPaint)
    }

}
