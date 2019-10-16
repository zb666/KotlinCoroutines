package com.example.bod.kotlincoroutines.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.scale
import com.example.bod.kotlincoroutines.R
import kotlin.math.min

/**
 *
 * @ClassName: ShaderCircleView
 * @Description:
 * @CreateDate: 2019/10/15
 */
@SuppressLint("NewApi")
class ShaderCircleView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        strokeWidth = 30f
        style = Paint.Style.FILL
        textSize = 30f
        //在背景上绘制Shade图片
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.the_atlantic)
        shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    }

    private val sourceBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_headband_sn)

    private var radius = 50f
    //创建空白的Bitmap 然后绘制圆角图片
    private val mBitmap by lazy {
        val createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        val canvas = Canvas(createBitmap)
        val scaledBitmap = sourceBitmap.scale(300, 200, true)
        //指定印章的样式

        //Paint的作用是绘制遮罩
        mPaint.shader = BitmapShader(scaledBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val diffWidth = scaledBitmap.width
        val diffHeight = scaledBitmap.height

        canvas.drawRoundRect(
                100f,
                300f,
                scaledBitmap.width.toFloat()+100f,
                500f, radius, radius, mPaint)

        sourceBitmap.takeIf { !it.isRecycled }?.recycle()
        scaledBitmap.takeIf { !it.isRecycled }?.recycle()
        createBitmap
    }.also {
        mPaint.reset()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawBitmap(mBitmap, 0f, 0f, mPaint)

    }


}