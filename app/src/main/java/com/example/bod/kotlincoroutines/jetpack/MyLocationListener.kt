package com.example.bod.kotlincoroutines.jetpack

import android.content.Context
import android.location.Location

/**
 *
 * @ClassName: MyLocationListener
 * @Description:
 * @CreateDate: 2019/8/1
 */
class MyLocationListener(
        private val context: Context,
        private inline val callback: (Int) -> String) {

    fun test() = callback(1)

    //函数对象
    fun testCallBack(){
        callback.invoke(1)
        //或者这样调用也是可以的
        callback(3)
    }

}