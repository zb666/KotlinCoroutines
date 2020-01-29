package com.example.bod.kotlincoroutines.structure

import com.example.bod.kotlincoroutines.exception.OutException
import com.example.bod.kotlincoroutines.structure.linked.LNode
import com.example.bod.kotlincoroutines.structure.linked.NodeList
import com.example.bod.kotlincoroutines.utils.log
import timber.log.Timber
import java.util.*

/**
 *
 * @ClassName: list
 * @Description:
 * @CreateDate: 2020/1/29
 */
fun main() {
    val nodeList = NodeList()

    nodeList.addData(1)
    nodeList.addData(2)
    nodeList.addData(3)


    nodeList.reverse()
    nodeList.showNodeList()

}