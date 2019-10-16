package com.example.bod.kotlincoroutines.view

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class FocusProgressView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var progress = 0f
        set(value) {
            if (field != value) {
                field = value
                invalidate()
            }
        }

    private val strokeW by lazy {
        context.dp2pxf(7f)
    }

    private val halfStrokeWidth by lazy {
        strokeW / 2f
    }

    private val backgroundPaint by lazy {
        Paint().apply {
            color = Color.BLUE
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeWidth = strokeW
            isAntiAlias = true
        }
    }

    private val mPaint by lazy {
        Paint().apply {
            strokeJoin = Paint.Join.ROUND
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            textSize = 12f
            color = Color.BLACK
        }
    }

    private val progressPaint by lazy {
        Paint().apply {
            color = Color.GRAY
            style = Paint.Style.STROKE
            strokeWidth = strokeW
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }
    }

    private var startAngle = 156f

    private var sweepAngle = 228f

    fun setAngle(startAngle:Float,sweepAngle:Float){
        this.startAngle = startAngle
        this.sweepAngle = sweepAngle
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = resolveSize(context.dp2px(150f), widthMeasureSpec)
        val h = resolveSize(context.dp2px(150f), heightMeasureSpec)
        setMeasuredDimension(w, h)
    }

    private val mArcPath = Path().apply {
        addArc(halfStrokeWidth, halfStrokeWidth,
                width - halfStrokeWidth,
                height - halfStrokeWidth,
                startAngle, sweepAngle)
    }

    @SuppressLint("DrawAllocation", "NewApi")
    override fun onDraw(canvas: Canvas) {
        if (width > 0 && height > 0) {

            canvas.drawArc(halfStrokeWidth, halfStrokeWidth,
                    width - halfStrokeWidth,
                    height - halfStrokeWidth,
                    startAngle, sweepAngle,false,backgroundPaint)

            canvas.drawArc(halfStrokeWidth, halfStrokeWidth,
                    width - halfStrokeWidth,
                    height - halfStrokeWidth,
                    startAngle,
                    sweepAngle * progress,
                    false, progressPaint)
        }
    }
}

fun Context.dp2pxf(dp: Float): Float {
    val scale = resources.displayMetrics.density
    return dp * scale
}

fun Context.dp2px(dp: Float): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}