package com.example.bod.kotlincoroutines.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.io.InputStream

/**
 *
 * @ClassName: LargeImageView
 * @Description:
 * @CreateDate: 2019/8/29
 */
class LargeImageView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val options = BitmapFactory.Options()

    init {
        options.inPreferredConfig = Bitmap.Config.RGB_565
    }

    private val mRect = Rect()

    private  var regionDecoder: BitmapRegionDecoder?=null

    private var imageWidth = 0
    private var imageHeight = 0

    fun setInputStream(inputStream: InputStream) {
        regionDecoder = BitmapRegionDecoder.newInstance(inputStream, false)
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inJustDecodeBounds = true
        BitmapFactory.decodeStream(inputStream, null, bitmapOptions)
        imageWidth = bitmapOptions.outWidth
        imageHeight = bitmapOptions.outHeight
        requestLayout()
        invalidate()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = measuredWidth
        val height = measuredHeight

        val ivWidth = imageWidth
        val ivHeight = imageHeight
        mRect.left = ivWidth / 2 - width / 2
        mRect.top = ivHeight / 2 - height / 2
        mRect.right = mRect.left + width
        mRect.bottom = mRect.top + height

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val bitmap = regionDecoder?.decodeRegion(mRect, options)
        bitmap?.let {
            canvas?.drawBitmap(it,null,mRect,null)
        }
    }

}