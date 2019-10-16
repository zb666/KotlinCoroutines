package com.example.bod.kotlincoroutines

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.scale
import kotlin.math.min

/**
 *
 * @ClassName: WaterMarkView
 * @Description:
 * @CreateDate: 2019/10/16
 */
class WaterMarkView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    val sourceBitmap = BitmapFactory.decodeResource(resources, R.drawable.oceania).copy(Bitmap.Config.ARGB_8888, true)

    fun generateMarkView(): Bitmap {
        val newBt = Bitmap.createBitmap(sourceBitmap.width, sourceBitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(newBt)//画布
        //绘制原图
        canvas.drawBitmap(sourceBitmap, 0f, 0f, mPaint)
        //然后绘制水印图片
        val markBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_share_qrcode).copy(Bitmap.Config.ARGB_8888, true)
        val height = sourceBitmap.height / 4
        sourceBitmap.recycle()
        val scaleMask = markBitmap.scale(200, 200, true)
        //绘制水印
        val widthSrc = (sourceBitmap.width/2).toFloat()
        val heightSrc = (sourceBitmap.height/2).toFloat()
        canvas.drawBitmap(scaleMask, widthSrc-scaleMask.width/2, heightSrc, mPaint)
        return newBt
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(generateMarkView(), 0f, 0f, mPaint)
    }

}