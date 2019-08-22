package com.example.bod.kotlincoroutines.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.utils.ScreenUtil


class CustomerProgressBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val borderColorList = arrayListOf("#8866FF", "#36ACFF", "#DD79FF", "#FF7994", "#FF900B", "#FFAF16")
    private val bgColorList = arrayListOf("#6639FF", "#008BEE", "#008BEE", "#FF7994", "#EF7812", "#E09717")
    private val bgShadowColorList = arrayListOf("#4d000000", "#4d000000", "#4d000000", "#57000000", "#57000000", "#57000000")

    private val borderPaint = Paint()
    private val bgPaint = Paint()
    private val progressPaint = Paint()
    private var progress: Float = 0.6f
    private var progressBitmap: Bitmap
    private var borderColor = Color.parseColor("#8866FF")
    private var bgColor = Color.parseColor("#6639FF")
    private var bgShadowColor = Color.parseColor("#4d000000")

    init {
        borderPaint.isAntiAlias = true
        borderPaint.color = borderColor
        bgPaint.isAntiAlias = true
        bgPaint.color = bgColor
        bgPaint.setShadowLayer(1f, 0f, -1f, bgShadowColor)
        progressBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_progress)
        progressPaint.isAntiAlias = true
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    private fun initOrReset() {
        borderPaint.isAntiAlias = true
        borderPaint.color = borderColor
        bgPaint.isAntiAlias = true
        bgPaint.color = bgColor
        bgPaint.setShadowLayer(1f, 0f, -1f, bgShadowColor)
        progressBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_progress)
        progressPaint.isAntiAlias = true
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        progressPaint.xfermode = porterDuffXfermode
    }

    private lateinit var borderRect: RectF
    private var borderRadio = 0f
    private var margin = 0f

    private lateinit var bgRect: RectF
    private lateinit var layerRectf: RectF
    private lateinit var tempRect: RectF
    private lateinit var srcRect: Rect

    private val porterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    private val bgRadio = ScreenUtil.dp2px(context, 4f) * 1f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bgRect = RectF(margin, margin, width - margin, height - margin)
        layerRectf = RectF(0f, 0f, width * 1f, height * 1f)
        tempRect = RectF(margin, margin, margin + (width - 2 * margin) * progress, height - margin)
        srcRect = Rect(0, 0, progressBitmap.width, progressBitmap.height)

        borderRect = RectF(0f, 0f, width.toFloat(), height.toFloat())
        borderRadio = ScreenUtil.dp2px(context, 15f) * 1f
        margin = ScreenUtil.dp2px(context, 2f) * 1f
        initOrReset()
    }

    @SuppressLint("NewApi")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        initOrReset()
        canvas?.let {
            it.drawRoundRect(borderRect, borderRadio, borderRadio, borderPaint)
            it.drawRoundRect(bgRect, bgRadio, bgRadio, bgPaint)
            val layer = canvas.saveLayer(layerRectf, progressPaint)
            it.drawRoundRect(tempRect, bgRadio, bgRadio, progressPaint)
            progressPaint.xfermode = porterDuffXfermode
            it.drawBitmap(progressBitmap, srcRect, bgRect, progressPaint)
            progressPaint.xfermode = null
            it.restoreToCount(layer)
        }
    }

    fun setProgress(percent: Float, type: Int) {
        this.progress = percent
        borderColor = Color.parseColor(borderColorList[type])
        bgColor = Color.parseColor(bgColorList[type])
        bgShadowColor = Color.parseColor(bgShadowColorList[type])
        invalidate()
    }

}