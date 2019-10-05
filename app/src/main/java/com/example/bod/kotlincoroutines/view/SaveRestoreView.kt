package com.example.bod.kotlincoroutines.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 *
 * @ClassName: SaveRestoreView
 * @Description:
 * @CreateDate: 2019/10/1
 */
class SaveRestoreView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
            .apply {
                color = Color.RED
            }



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //也可以通过Matrix进行旋转平移 缩放等操作
        val matrix = Matrix().apply {
            setTranslate(100f,100f)
            setScale(1.5f,1.5f)
                   setRotate(45f)
        }

        canvas?.matrix = matrix
        canvas?.drawRect(0f,0f,700f,700f,paint)
    }
}