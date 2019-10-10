package com.example.bod.kotlincoroutines.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.text.Layout
import android.text.Spanned
import android.text.StaticLayout
import android.text.TextPaint
import android.text.style.RelativeSizeSpan
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withTranslation
import androidx.core.text.toSpannable

class CircleProgressBar222 @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var progress = 0f
        set(value) {
            if (field != value) {
                field = value
                invalidate()
            }
        }

    var progressColor = 0xFF457CFD.toInt()
        set(value) {
            field = value
            progressPaint.color = field
            invalidate()
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
            strokeWidth = strokeW
            isAntiAlias = true
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

    private val textPaint by lazy {
        TextPaint().apply {
            color = Color.BLACK
            isAntiAlias = true
            textSize = context.dp2pxf(30f)
        }
    }

    private var radius = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val m = if (w >= h) {
            h
        } else {
            w
        }
        radius = (m - strokeW) / 2f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = resolveSize(context.dp2px(140f), widthMeasureSpec)
        val h = resolveSize(context.dp2px(140f), heightMeasureSpec)
        setMeasuredDimension(w, h)
    }

    @SuppressLint("DrawAllocation", "NewApi")
    override fun onDraw(canvas: Canvas) {
        if (width > 0 && height > 0) {
//            canvas.drawCircle(width / 2f, height / 2f, radius, backgroundPaint)

            canvas.drawArc(halfStrokeWidth, halfStrokeWidth,
                    width - halfStrokeWidth, height - halfStrokeWidth,
                    180f, 180f,
                    false, backgroundPaint)

            canvas.drawArc(halfStrokeWidth, halfStrokeWidth,
                    width - halfStrokeWidth, height - halfStrokeWidth,
                    180f, 180f * progress,
                    false, progressPaint)

            val span = String.format("%d/21天", 21 * progress.toInt()).toSpannable().apply {
                setSpan(RelativeSizeSpan(0.5f), this.length - 2, this.length - 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
            }
            val staticLayout =
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                        @Suppress("DEPRECATION")
                        StaticLayout(span, textPaint, width, Layout.Alignment.ALIGN_CENTER,
                                1f, 0f, false)
                    } else {
                        StaticLayout.Builder.obtain(span, 0, span.length, textPaint, width)
                                .setAlignment(Layout.Alignment.ALIGN_CENTER)
                                .build()
                    }
            canvas.withTranslation(0f, (height - staticLayout.height) / 2f) {
                staticLayout.draw(canvas)
            }
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