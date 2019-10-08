package com.example.bod.kotlincoroutines.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 *
 * @ClassName: PathView
 * @Description:
 * @CreateDate: 2019/10/2
 */
class PathView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, override val coroutineContext: CoroutineContext

) : View(context, attrs, defStyleAttr),CoroutineScope {

    // ContextScope(SupervisorJob() + Dispatchers.Main)
//    override val coroutineContext: CoroutineContext
//        get() = Dispatchers.Main+mJob

    private var mJob:Job = launch {
        coroutineScope {

        }
    }

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            .apply {
                style = Paint.Style.STROKE
                color = Color.RED
                strokeWidth = 6f
            }

    private val mPath = Path()

    //CW顺时针
    //CCW逆时针绘制
    @SuppressLint("NewApi")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPath.addArc(0f, 0f, 200f, 200f, -225f, 225f)
        mPath.apply {
            //            rLineTo(200f,0f)
//            rLineTo(-200f,500f)
//            rMoveTo(350f,-250f)
//            addCircle(300f,300f,50f,Path.Direction.CW)
            moveTo(60f, 50f)
            rLineTo(90f, 0f)
            rLineTo(-70f, 160f)

            addCircle(245f, 125f, 75f, Path.Direction.CW)

            moveTo(400f, 400f)

//            quadTo(500f,300f,600f,400f)
            rQuadTo(100f, -100f, 200f, 0f)

            moveTo(400f, 600f)

//            rCubicTo(100f, -200f, 200f, 200f, 300f, 0f)
            rCubicTo(100f,-400f,200f,200f,500f,0f)

        }
        canvas?.drawPath(mPath, mPaint)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        cancel()
    }

}