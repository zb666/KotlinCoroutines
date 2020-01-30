package com.example.bod.kotlincoroutines.structure.sort_algorithms

import java.util.Arrays


/**
 * @ClassName: BinarySearch
 * @Description:
 * @CreateDate: 2020/1/30
 */
object BinarySearch {
    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9,9, 10)
        val key = 8
        val targetIndex = binarySh(array, key)
        println("找到目标数据${array[targetIndex]}")
    }

    private fun binarySh(array: IntArray, key: Int): Int {
        var start = 0
        var end = array.size - 1
        while (start <= end) {
            val mid = (start + end) / 2
            when {
                key > array[mid] -> start = mid + 1 //动态的
                key < array[mid] -> end = mid - 1
                else -> return mid
            }
        }
        return -1
    }

}
