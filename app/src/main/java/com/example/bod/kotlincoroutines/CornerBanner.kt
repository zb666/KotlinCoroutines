package com.example.bod.kotlincoroutines

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Region
import android.util.AttributeSet
import com.youth.banner.Banner

/**
 *
 * @ClassName: CornerBanner
 * @Description:
 * @CreateDate: 2019/10/18
 */
@SuppressLint("NewApi")
class CornerBanner @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Banner(context, attrs, defStyleAttr) {

    private val mPath by lazy {
        Path().apply {
            addRoundRect(0f, 0f, width.toFloat(), height.toFloat(), floatArrayOf(50f), Path.Direction.CW)
        }
    }

    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//        canvas?.apply {
//            save()
//            clipPath(mPath)
//            restore()
//        }
        canvas?.clipPath(mPath)
    }

}