package com.example.bod.kotlincoroutines.structure

import android.annotation.SuppressLint
import com.example.bod.kotlincoroutines.structure.linked.NodeList
import com.example.bod.kotlincoroutines.utils.log
import java.util.*
import java.util.concurrent.*

/**
 *
 * @ClassName: list
 * @Description:
 * @CreateDate: 2020/1/29
 */
@SuppressLint("NewApi")
fun main() {
    val nodeList = NodeList()

    nodeList.addData(1)
    nodeList.addData(2)
    nodeList.addData(3)

    nodeList.reverse()
    nodeList.showNodeList()

    val linkedBlockingDeque = LinkedBlockingDeque<String>()
    linkedBlockingDeque.add("1")
    linkedBlockingDeque.add("1")
    linkedBlockingDeque.add("1")
    linkedBlockingDeque.pop()
    linkedBlockingDeque.pollLast()

    val priorityQueue =
        PriorityQueue<Int>()

    priorityQueue.add(1)
    priorityQueue.add(11)
    priorityQueue.add(5)
    priorityQueue.add(6)

    priorityQueue.forEach {
        log("$it")
    }

    val poolExecutor = ThreadPoolExecutor(
            3,
            6,
            10,
            TimeUnit.SECONDS,
            LinkedBlockingQueue<Runnable>(10))

    (1..20).forEach {
        poolExecutor.submit(MyRunnable(it))
    }

    poolExecutor.shutdown()

    val mStack = Stack<String>()
    mStack.apply {
        add("1")
        add("2")
        add("4")
        add("3")
        add("5")
    }
    val topElement = mStack.pop()
    log("StackValue: "+topElement)


    ArrayDeque<String>().apply {
        add("1")
    }
}

class MyRunnable(private var index: Int) :Runnable{
    override fun run() {
        Thread.sleep(500)
        log("任务: $index")
    }

}