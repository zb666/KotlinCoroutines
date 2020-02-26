package com.example.bod.kotlincoroutines.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.example.bod.kotlincoroutines.R

/**
 *
 * @ClassName: SuspensionView
 * @Description:
 * @CreateDate: 2020/2/24
 */
class SuspensionView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr){

    private var mBitmap:Bitmap?=null
    init {
       mBitmap =  BitmapFactory.decodeResource(resources, R.drawable.ic_launcher)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(width,height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(mBitmap,0f,0f,null)
    }

}