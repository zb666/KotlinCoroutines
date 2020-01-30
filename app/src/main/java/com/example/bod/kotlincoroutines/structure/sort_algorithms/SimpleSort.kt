package com.example.bod.kotlincoroutines.structure.sort_algorithms

import com.example.bod.kotlincoroutines.utils.log

/**
 *
 * @ClassName: SimpleSort
 * @Description:
 * @CreateDate: 2020/1/30
 */

fun main() {
    val intArray = arrayOf(1, 5, 3, 2, 6, 8).toIntArray()
    intArray.forEachIndexed { index, value ->
        var tempIndex = index
        var j = index
        for (indexJ in j until intArray.size) {
            if (intArray[indexJ] < intArray[tempIndex]) {
                tempIndex = indexJ //找到最小的数的index下标
            }
        }
        //找到最小的值 交换,下次排序的时候不用再排在内
        val intTemp = intArray[tempIndex]
        intArray[tempIndex] = intArray[index]
        intArray[index] = intTemp
    }
    for (i in intArray) {
        log("ArrayValue: $i")
    }
}