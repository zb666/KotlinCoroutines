package com.example.bod.kotlincoroutines.utils

import android.content.Context

/**
 * Created by Linmin Li on 2018/3/8.
 * Copyright © 2018Year BrainCo. All rights reserved.
 */

class ScreenUtil {
    companion object {
        fun dp2px(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }

        /**
         * 将px值转换为dip或dp值，保证尺寸大小不变
         *
         * @param context
         * @param pxValue
         * @return
         */
        fun px2dip(context: Context, pxValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }

        /**
         * 将dip或dp值转换为px值，保证尺寸不变
         *
         * @param context
         * @param dipValue
         * @return
         */
        fun dip2px(context: Context, dipValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }

        /**
         * 将px值转换为sp值，保证文字大小不变
         *
         * @param context
         * @param pxValue
         * @return
         */
        fun px2sp(context: Context, pxValue: Float): Int {
            val fontScale = context.resources.displayMetrics.scaledDensity
            return (pxValue / fontScale + 0.5f).toInt()
        }

        /**
         * 将sp值转换为px值，保证文字大小不变
         *
         * @param context
         * @param spValue
         * @return
         */
        fun sp2px(context: Context, spValue: Float): Int {
            val fontScale = context.resources.displayMetrics.scaledDensity
            return (spValue * fontScale + 0.5f).toInt()
        }

        fun getScreenWidth(context: Context): Int {
            return context.resources.displayMetrics.widthPixels
        }

        fun getScreenHeight(context: Context): Int {
            return context.resources.displayMetrics.heightPixels
        }
    }
}
