package com.example.bod.kotlincoroutines.utils

import android.content.Context
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.WindowManager

/**
 * @ClassName: SuspensionWindowUtil
 * @Description:
 * @CreateDate: 2020/2/24
 */
class SuspensionWindowUtil(private val context: Context) {
    private var windowManager: WindowManager? = null

    init {
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
    }

    fun showSuspensionView() {
        //创建布局的参数
        val suspensionView = SuspensionView(context)
        val wiwLapas = WindowManager.LayoutParams().apply {
            x = 200
            y = 3000
            gravity = Gravity.LEFT
            type = WindowManager.LayoutParams.TYPE_DRAWN_APPLICATION
            flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            format = PixelFormat.RGBA_8888
        }
        windowManager?.addView(suspensionView, wiwLapas)
    }

}
