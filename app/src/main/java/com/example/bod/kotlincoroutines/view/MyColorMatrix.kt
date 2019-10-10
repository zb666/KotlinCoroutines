package com.example.bod.kotlincoroutines.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.example.bod.kotlincoroutines.R

/**
 *
 * @ClassName: MyColorMatrix
 * @Description:
 * @CreateDate: 2019/10/9
 */
class MyColorMatrix @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.focus_ic_add)

    private val mPaint = Paint().apply {
        isAntiAlias = true
        color = Color.RED
        strokeWidth = 6f
    }

    private val colorMatrix = ColorMatrixColorFilter(ColorMatrix(floatArrayOf(
          0f, 0f, 0f, 0f, 0f,
          0f, 0f, 0f, 0f, 0f,
          0f, 0f, 1f, 0f, 0f,
          0f, 0f, 0f, 1f, 0f
    )
  ))

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //绘制原始图形
        canvas?.drawBitmap(mBitmap, 0f, 0f, mPaint)

        mPaint.colorFilter = colorMatrix

        canvas?.apply {
//            save()

            translate(0f, (mBitmap.height*2).toFloat())

            mPaint.colorFilter = colorMatrix

            canvas.drawBitmap(mBitmap, 0f, 0f, mPaint)

//            canvas.restore()
        }
    }

}