package com.example.bod.kotlincoroutines

import com.blankj.utilcode.util.LogUtils

/**
 * @ClassName: JavaClass
 * @Description:
 * @CreateDate: 2019/9/2
 */
object JavaClass {
    @JvmStatic
    fun main(args: Array<String>) {

        arrayOf("1").iterator().forEach {

        }

    }


    inline fun <reified T,R> ArrayList<T>.transMap(tranform: (T) -> R): List<R> {
        val mapList = ArrayList<R>(size)
        for (element in this) {
            mapList.add(tranform(element))
        }
        return mapList
    }


}
