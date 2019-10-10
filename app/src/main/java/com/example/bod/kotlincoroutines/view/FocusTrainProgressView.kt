package com.example.bod.kotlincoroutines.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

/**
 *
 * @ClassName: FocusTrainProgressView
 * @Description:
 * @CreateDate: 2019/10/10
 */
class FocusTrainProgressView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val mOutCirclePaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.BLUE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            strokeWidth = 10f
            style = Paint.Style.STROKE
        }
    }

    private val mInnerCirclePaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.GRAY
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            strokeWidth = 10f
            style = Paint.Style.STROKE
        }
    }

    private var mParentWidth = 0

    private var mParentHeight = 0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mParentHeight = h
        mParentWidth = w
    }

    private val mOutCirclePath by lazy {
        val radius = mParentHeight.toFloat()
        val left = mParentWidth/2
        val rectF = RectF(left+100f, 10f, 100+radius, radius)
        Path().apply {
            addArc(rectF, 180f, 120f)
        }
    }

    private val mInnerCirclePath by lazy {
        val radius = mParentHeight.toFloat()
        val left = mParentWidth/2
        val rectF = RectF(left+100f, 10f, 100+radius, radius)
        Path().apply {
            addArc(rectF, 180f, 180f)
        }
    }

    private val mTextPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.BLACK
            textSize = 20f
            textAlign = Paint.Align.CENTER
        }
    }

    private val measuredTxtWidth = mTextPaint.measureText("专注力训练计划")

    private val mSmallPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.GRAY
            textSize = 10f
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(mInnerCirclePath, mInnerCirclePaint)
        canvas?.drawPath(mOutCirclePath, mOutCirclePaint)

        val startX = (mParentWidth - measuredTxtWidth) / 2

        canvas?.drawText("13/21", startX, (mParentHeight - 30).toFloat(), mTextPaint)
        canvas?.drawText("专注力训练计划", startX, (mParentHeight - 60).toFloat(), mTextPaint)

    }


}