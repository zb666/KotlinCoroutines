package com.example.bod.kotlincoroutines.demo

import com.example.bod.kotlincoroutines.utils.log
import java.util.*
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.LinkedBlockingQueue

/**
 *
 * @ClassName: 线程封闭
 * @Description:
 * @CreateDate: 2019/11/30
 */
object 线程封闭 {

    private val threadLocal = ThreadLocal<String>()

    //java   并不是所有的情况都需要共享,比如ThreadLocal
    //2 局部变量
    @JvmStatic
    fun main(args: Array<String>) {

        threadLocal.set("aaa")

        val deque = ArrayDeque<String>()
        deque.add("aaa")

        Thread(Runnable {
            log(threadLocal.get())
        }).start()

    }

}