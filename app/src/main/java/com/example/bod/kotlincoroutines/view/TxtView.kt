package com.example.bod.kotlincoroutines.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import timber.log.Timber

/**
 *
 * @ClassName: TxtView
 * @Description:
 * @CreateDate: 2019/10/10
 */
class TxtView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            .apply {
                color = Color.BLUE
                strokeWidth = 20f
                textSize = 30f
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.ROUND
                //注意TextAlign.Center不能滥用
            }

    private val strName = "21天训练计划"

    private var rectF = Rect()

    private var mPath = Path()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = resolveSize(context.dp2px(150f), widthMeasureSpec)
        val height = resolveSize(context.dp2px(95f), widthMeasureSpec)
        setMeasuredDimension(width,height)
    }

    private val mRectF = RectF(100f,10f,200f,100f)

    @SuppressLint("NewApi")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.getTextBounds(strName, 0, strName.length, rectF)
        val textOffsetY = rectF.bottom - rectF.top
        val width = mPaint.measureText(strName)
        val width1 = rectF.width()
        val height = rectF.height()

        Timber.d("TxtView: measureText: $width   $width1   $height ")
        canvas?.drawText(strName, 0f, textOffsetY.toFloat(), mPaint)

        canvas?.translate(0f,30f)
        canvas?.drawArc(
                20f, height.toFloat(),
                getWidth().toFloat()-height,
                getHeight().toFloat()-height
                ,120f,250f,false,mPaint)

        mPath.arcTo(mRectF,120f,200f,true)
//        canvas?.drawPath(mPath,mPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
             MotionEvent.ACTION_CANCEL,MotionEvent.ACTION_UP-> {
                 //取消播放
            }
        }
        return super.onTouchEvent(event)
    }

    fun Context.dp2pxf(dp: Float): Float {
        val scale = resources.displayMetrics.density
        return dp * scale
    }

    fun Context.dp2px(dp: Float): Int {
        val scale = resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

}
