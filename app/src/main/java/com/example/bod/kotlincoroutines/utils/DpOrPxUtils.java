package com.example.bod.kotlincoroutines.utils;

import android.content.Context;

/**
 * @ClassName: DpOrPxUtils
 * @Description:
 * @CreateDate: 2019/9/29
 */
public class DpOrPxUtils {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
