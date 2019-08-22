package com.example.bod.kotlincoroutines

import android.util.Log

class LogUtils {

    companion object {
        const val name:String = "object single"

        fun addName() = "伴深对象中的方法"

        fun showLog(tag:String = "Bob",value:String){
            Log.d(tag,value)
        }
    }

}