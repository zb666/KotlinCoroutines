package com.example.bod.kotlincoroutines.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.bod.kotlincoroutines.R
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth


/**
 *
 * @ClassName: MatrixViewView
 * @Description:
 * @CreateDate: 2019/10/1
 */
class MatrixViewView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mMatrix = matrix

    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_focus_logo)

    private val bitmapCover = BitmapFactory.decodeResource(resources, R.drawable.ic_share_qrcode)

    private var bitmapResource: Bitmap? = null

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            .apply {
                color = Color.RED
                strokeWidth = 5f
//                strokeCap //帽线条
//                strokeJoin //线段连接处样式
//                strokeMiter 画笔倾斜度
                style = Paint.Style.FILL //STROKE 和 FULL_AND STROKE相比差了一个描边的宽度 也就是5f的宽度
            }

    var src = floatArrayOf(0f, 0f, // 左上
            bitmap.width.toFloat(), 0f, // 右上
            bitmap.width.toFloat(), bitmap.height.toFloat(), // 右下
            0f, bitmap.height.toFloat())                        // 左下

    var dst = floatArrayOf(0f, 0f, // 左上
            bitmap.width.toFloat(), 400f, // 右上
            bitmap.width.toFloat(), (bitmap.height - 200).toFloat(), // 右下
            0f, bitmap.height.toFloat())

    private var bitmapCanvas: Canvas? = null

    init {
        //离屏绘制 把绘制的内容绘制到另外一个图层上
//        mMatrix.preScale(2f, 2f) //矩阵前乘
//        mMatrix.preRotate(-45f)
        //set系列会清空之前所有的Matrix变化

        //pointCount 0-4
        //0->等价于reset() 1->平面上滑动 2->在平面上缩放，平移，旋转 3->s,t,r,skew等操作
//        mMatrix.setPolyToPoly(src,0,dst,0,3)
        val srcFloatArray = floatArrayOf(0f, 0f, 400f, 0f, 400f, 400f)
        val dstFloatArray = floatArrayOf(0f, 0f, 400f, 0f, 500f, 400f)
        matrix.setPolyToPoly(srcFloatArray, 0, dstFloatArray, 0, 3)
        mMatrix.postScale(0.6f, 0.6f)
        mMatrix.postTranslate(0f, 200f)
        setLayerType(LAYER_TYPE_SOFTWARE, null)
//        mMatrix.postRotate(30f)
//        mMatrix.preSkew(30f,30f)
        //saveLayer->id->RestoreId(id)
        //收影响的是src和dst相交的部分区域

        bitmapResource = Bitmap.createBitmap(
                bitmap.width * 2,
                bitmap.height * 2,
                Bitmap.Config.ARGB_8888)

        bitmapCanvas = Canvas()
        bitmapCanvas!!.setBitmap(bitmapResource)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        bitmapCanvas?.drawBitmap(bitmapCover, 0f, 0f, mPaint)

        canvas?.drawBitmap(bitmap, 0f, 0f, mPaint)

        canvas?.save()
        canvas?.translate(0f, bitmap.height.toFloat())
        canvas?.drawBitmap(bitmap, mMatrix, mPaint)
        canvas?.restore()

//        canvas?.save()
//        canvas?.translate(0f, bitmap.height.toFloat() * 2)
//        canvas?.drawBitmap(bitmapResource!!, 0f, 0f, mPaint)
//        canvas?.restore()

        canvas?.apply {
            save()
            translate(0f, bitmap.height.toFloat() * 2)
            bitmapCanvas?.drawBitmap(bitmapCover, 0f, 0f, mPaint)
            restore()
        }


    }
}