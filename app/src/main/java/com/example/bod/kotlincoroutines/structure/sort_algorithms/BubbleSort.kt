package com.example.bod.kotlincoroutines.structure.sort_algorithms

import android.util.Log

import android.content.ContentValues.TAG

/**
 * @ClassName: BubbleSort
 * @Description:
 * @CreateDate: 2020/1/30
 */
object BubbleSort {

    @JvmStatic
    fun main(array: Array<String>) {
        //第一次比较 n
        //第二次比较 n-1
        //第三次比较 n-2
        //依次类推
        val types = intArrayOf(1, 2, 3, 8, 7, 3, 10, 9)
        for (i in types.indices) {
            //每次i的循环都会找出一个最小的数据
            for (j in 0 until types.size - 1 - i) {
                //1 expect self
                //i expect sorted operation
                if (types[j] > types[j + 1]) {
                    val temp = types[j + 1]
                    types[j + 1] = types[j]
                    types[j] = temp
                }
            }
        }
        for (type in types) {
            println("Types: $type")
        }
    }


}
