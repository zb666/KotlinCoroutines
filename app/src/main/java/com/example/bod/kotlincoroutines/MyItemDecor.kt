package com.example.bod.kotlincoroutines

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

/**
 *
 * @ClassName: MyItemDecor
 * @Description:
 * @CreateDate: 2019/10/8
 */
class MyItemDecor(private val context:Context) : RecyclerView.ItemDecoration() {

    private val circleBitmap = BitmapFactory.decodeResource(context.resources,R.drawable.ic_focus_round_logo)

    private val mPaint = Paint().apply {
        color = Color.RED
        strokeWidth = 6f
        style = Paint.Style.FILL
    }

    private val mPaintOver = Paint().apply {
        color = Color.GRAY
        strokeWidth = 6f
        style = Paint.Style.STROKE
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount - 1) {
            outRect.set(30, 10, 10, 0) //流出空隙
        }
    }

    val width = 20f
    val dividerHeight = 10

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)
        val childCount = parent.childCount
        val topIcon = circleBitmap.height
        for (index in 0 until childCount) {
            val child = parent.getChildAt(index)
            val top = child.top
            val bottom = child.bottom
            //vertical line
            val left = parent.paddingLeft+width/2+20
            canvas.drawRect(left, (if (index == 0) top+topIcon else top).toFloat(),left+dividerHeight, (bottom+dividerHeight).toFloat(),mPaint)

            //drawCircle
            val ovalCenterX = top+30f+4f
            if (index == 0){
                val bitmapDrawable = circleBitmap.toDrawable(context.resources)
                val bitmapWidth = circleBitmap.width
                bitmapDrawable.setBounds((left-bitmapWidth/2).toInt(), (top+30f).toInt(), (left+bitmapWidth*2).toInt(), (30f+circleBitmap.height).toInt())
                bitmapDrawable.draw(canvas)
            }else{
                canvas.drawCircle(left,ovalCenterX,10f,mPaint)
            }
        }


    }

}