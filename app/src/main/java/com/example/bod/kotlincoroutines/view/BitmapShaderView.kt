package com.example.bod.kotlincoroutines.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.bod.kotlincoroutines.R
import kotlin.math.max

/**
 *
 * @ClassName: BitmapShaderView
 * @Description:
 * @CreateDate: 2019/10/16
 */
class BitmapShaderView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            shader = BitmapShader(BitmapFactory.decodeResource(resources, R.drawable.oceania), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            shader.setLocalMatrix(mMatrix)
        }
    }

    private val mSourceBitmap = BitmapFactory.decodeResource(resources, R.drawable.oceania)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(mSourceBitmap.width * 2, mSourceBitmap.height * 2)
    }

    private val mMatrix by lazy {
        Matrix().also {
            it.setScale((width / mSourceBitmap.width).toFloat(), (height / mSourceBitmap.height).toFloat())
        }
    }

    @SuppressLint("NewApi")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setBackgroundColor(Color.WHITE)
        canvas?.drawRoundRect(0f, 0f, width.toFloat() / 2, height.toFloat() / 2, 50f, 50f, mPaint)
    }


}