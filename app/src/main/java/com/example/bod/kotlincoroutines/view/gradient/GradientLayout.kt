package com.example.bod.kotlincoroutines.view.gradient

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.bod.kotlincoroutines.R

/**
 *
 * @ClassName: GradientLayout
 * @Description:
 * @CreateDate: 2019/10/1
 */
class GradientLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mPaint: Paint
    init {
        val avatarBitmap = BitmapFactory.decodeResource(resources, R.drawable.avatar_default)
        val bitmapShader = BitmapShader(avatarBitmap,Shader.TileMode.CLAMP,Shader.TileMode.MIRROR)
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
                .apply {
                    style = Paint.Style.FILL
                    strokeWidth = 6f
                    color = Color.RED
                    setShader(bitmapShader)
                }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(200f,200f,100f,mPaint)
    }


}