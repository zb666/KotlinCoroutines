package com.example.bod.kotlincoroutines.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.ViewGroup
import com.example.bod.kotlincoroutines.LogUtils

/**
 * @ClassName: MyViewGroup
 * @Description:
 * @CreateDate: 2019/8/21
 */
class MyViewGroup @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private var testValue = "Value"

    init {
        LogUtils.showLog(tag = "ViewGroup",value = "init() invoked"+testValue)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        LogUtils.showLog(tag = "ViewGroup",value = "onAttachedToWindow"+width+" "+height+" "+measuredHeight+" "+measuredWidth)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        LogUtils.showLog(tag = "ViewGroup",value = "onFinishInflate"+width+" "+height+" "+measuredHeight+" "+measuredWidth)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        LogUtils.showLog(tag = "ViewGroup",value = "onMeasure")
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        LogUtils.showLog(tag = "ViewGroup",value = "onLayout")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        LogUtils.showLog(tag = "ViewGroup",value = "onSizeChanged"+width+" "+height+" "+measuredHeight+" "+measuredWidth)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        LogUtils.showLog(tag = "ViewGroup",value = "onDraw")
        canvas?.drawARGB(0,0,0,0)
    }
}
