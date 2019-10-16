package com.example.bod.kotlincoroutines.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.scale
import androidx.core.graphics.withRotation
import androidx.core.graphics.withTranslation
import com.example.bod.kotlincoroutines.R

/**
 *
 * @ClassName: XferModeView
 * @Description:
 * @CreateDate: 2019/10/15
 */
class XferModeView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val dstBitmap = BitmapFactory.decodeResource(resources,R.drawable.focus_earth).apply {
        scaleX = 0.5f
        scaleY = 0.5f
    }

    private val srcBitmap = BitmapFactory.decodeResource(resources,R.drawable.focus_default_division_avatar)

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            .apply {
                color = Color.BLUE
                textSize = 10f
            }

//    private val mCanvas = Canvas(srcBitmap)

    private val xferMode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        setMeasuredDimension(resolveSize(150,widthMeasureSpec), resolveSize(100,heightMeasureSpec))
//    }

    @SuppressLint("NewApi")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.withRotation(degrees = 90f){

        }
//        val layerId = canvas?.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), mPaint)
        canvas?.drawBitmap(dstBitmap,0f,0f,mPaint)
        mPaint.xfermode = xferMode
        canvas?.drawBitmap(srcBitmap,width*0.5f,height*0.5f,mPaint)
        mPaint.xfermode = null
//        if (layerId != null) {
//            canvas.restoreToCount(layerId)
//        }
        canvas?.withTranslation(50f,50f) {

        }

        canvas?.withRotation(degrees = 90f){

        }

    }

}