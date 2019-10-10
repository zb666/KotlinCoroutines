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
import androidx.core.content.ContextCompat
import androidx.core.graphics.withTranslation
import androidx.core.text.toSpannable
import com.blankj.utilcode.util.ConvertUtils.dp2px
import com.example.bod.kotlincoroutines.R

class CircleProgressBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var progress:Int = 0
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
        dp2px(7f)
    }

    private val halfStrokeWidth by lazy {
        strokeW / 2f
    }

    private val progressPaint by lazy {
        Paint().apply {
            color = progressColor
            style = Paint.Style.STROKE
            strokeWidth = strokeW.toFloat()
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }
    }

    private val bgProgressPaint by lazy {
        Paint().apply {
            color = ContextCompat.getColor(getContext(),R.color.gray_cfcfcf)
            style = Paint.Style.STROKE
            strokeWidth = strokeW.toFloat()
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
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
        val w = resolveSize(dp2px(140f), widthMeasureSpec)
        val h = resolveSize(dp2px(140f), heightMeasureSpec)
        setMeasuredDimension(w, h)
    }

    @SuppressLint("DrawAllocation", "NewApi")
    override fun onDraw(canvas: Canvas) {
        if (width > 0 && height > 0) {
                        canvas.drawArc(
                    halfStrokeWidth,
                    halfStrokeWidth,
                    width - halfStrokeWidth,
                    height - halfStrokeWidth,
                    180f,
                    180f,
                    false, bgProgressPaint)

            canvas.drawArc(
                    halfStrokeWidth,
                    halfStrokeWidth,
                    width - halfStrokeWidth,
                    height - halfStrokeWidth,
                    180f,
                    180f * progress,
                    false, progressPaint)

        }
    }




}