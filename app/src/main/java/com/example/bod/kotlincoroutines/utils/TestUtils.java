package com.example.bod.kotlincoroutines.utils;

import android.content.Context;
import android.util.Log;

import com.example.bod.kotlincoroutines.LogUtils;

/**
 * @ClassName: TestUtils
 * @Description:
 * @CreateDate: 2019/8/2
 */
public class TestUtils {

    private static volatile Context sContext;

    public static void init(Context context){
       sContext = context.getApplicationContext();
    }

    public static synchronized void startPrint(String contextStr){
        Log.d("BobContext",sContext.toString());
    }


}
