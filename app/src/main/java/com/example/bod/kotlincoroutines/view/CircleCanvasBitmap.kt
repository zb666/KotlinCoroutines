package com.example.bod.kotlincoroutines.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.scale
import androidx.core.graphics.withTranslation
import com.example.bod.kotlincoroutines.R
import kotlin.math.min

/**
 *
 * @ClassName: CircleCanvasBitmap
 * @Description:
 * @CreateDate: 2019/10/15
 */
class CircleCanvasBitmap @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    //根据path裁剪画布,生成圆形头像
    private var mBitmap: Bitmap

    private var mRoundBitmap: Bitmap

    private val mPath = Path()

    private val mRoundPath = Path()

    private var mRadius:Float = 0f

    private var scaledBitmap:Bitmap

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
        mRoundBitmap = BitmapFactory.decodeResource(resources, R.drawable.the_arctic_ocean)
        mBitmap = BitmapFactory.decodeResource(resources, R.drawable.asia)
        mRadius = min(mBitmap.width, mBitmap.height).toFloat()
        scaledBitmap = mRoundBitmap.scale(mRadius.toInt()*2, (mRadius*2.toInt()).toInt())
        mPath.addCircle(mRadius, mRadius, mRadius, Path.Direction.CW)
    }

    private val mCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(scaledBitmap.width,scaledBitmap.height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        canvas?.clipPath(mPath)
        canvas?.drawBitmap(scaledBitmap,0f,0f,mCirclePaint)
        canvas?.restore()
//        canvas?.run {
//            save()
//            clipPath(mPath)//裁剪画布
//            drawBitmap(mBitmap,mBitmap.width*0.5f,mBitmap.height*0.5f,mCirclePaint)
//            restore()
//        }
//
//        canvas?.withTranslation(x = 0f, y = 200f) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                mRoundPath.addRoundRect(
//                        0f, 0f,
//                        mRoundBitmap.width.toFloat(),
//                        mRoundBitmap.height.toFloat(),
//                        floatArrayOf(10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f), Path.Direction.CW
//                )
//                canvas.clipPath(mRoundPath)
////                drawBitmap(mRoundBitmap, mBitmap.width * 0.5f, mBitmap.height * 0.5f, mCirclePaint)
//            }
//        }

    }

}
