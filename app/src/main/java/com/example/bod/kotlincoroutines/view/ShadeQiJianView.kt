package com.example.bod.kotlincoroutines.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.bod.kotlincoroutines.R
import kotlin.math.min

/**
 *
 * @ClassName: ShadeQiJianView
 * @Description:
 * @CreateDate: 2019/10/16
 */
class ShadeQiJianView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_focus_logo)

    private val mShader = BitmapShader(mBitmap,Shader.TileMode.REPEAT,Shader.TileMode.REPEAT)

    private val mMatrix by lazy {
        Matrix().also {
            it.setScale(width/mBitmap.width*1.0f,height/mBitmap.height*1.0f)
        }
    }

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val half by lazy {
        min(width,height)*0.5f
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
//        setBackgroundColor(Color.BLUE)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mShader.setLocalMatrix(mMatrix)
        mPaint.shader = mShader
        //绘制圆角的话也是同理
//        canvas.drawRoundRect()
        canvas?.drawCircle(half,half,half,mPaint)
    }

}
