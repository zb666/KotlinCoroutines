package com.example.bod.kotlincoroutines.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.bod.kotlincoroutines.R

/**
 *
 * @ClassName: BitmapShaderView
 * @Description:
 * @CreateDate: 2019/10/16
 */
class BitmapShaderView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        shader = BitmapShader(BitmapFactory.decodeResource(resources, R.drawable.oceania),Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
    }

    private val mSourceBitmap = BitmapFactory.decodeResource(resources, R.drawable.oceania)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(mSourceBitmap.width*2,mSourceBitmap.height*2)
    }

    @SuppressLint("NewApi")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setBackgroundColor(Color.WHITE)
        canvas?.drawRoundRect(0f,0f,width.toFloat(),height.toFloat(),50f,50f,mPaint)
    }


}