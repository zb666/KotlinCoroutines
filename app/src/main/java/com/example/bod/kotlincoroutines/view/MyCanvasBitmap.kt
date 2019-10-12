package com.example.bod.kotlincoroutines.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.bod.kotlincoroutines.R

/**
 *
 * @ClassName: MyCanvasBitmap
 * @Description:
 * @CreateDate: 2019/10/11
 */
class MyCanvasBitmap @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        generateCanvasBitmap()
    }

    private val mPaint = Paint().apply {
        isAntiAlias = true
        color = Color.RED
        strokeWidth = 10f
    }

    private fun generateCanvasBitmap(){
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.asia)
        val canvas = Canvas(bitmap)
        canvas.drawCircle(100f,100f,50f,mPaint)
    }

}
