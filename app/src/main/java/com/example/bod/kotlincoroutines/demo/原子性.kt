package com.example.bod.kotlincoroutines.demo

import android.os.SystemClock
import com.example.bod.kotlincoroutines.utils.log
import java.util.concurrent.atomic.AtomicInteger

/**
 *
 * @ClassName: 原子性
 * @Description:
 * @CreateDate: 2019/12/1
 */
object 原子性 {

    var i = 0
    val atomicInteger = AtomicInteger(i)

    @JvmStatic
    fun main(args: Array<String>) {
        atomicInteger.set(0)
        (0..1).forEach {
            Thread(Runnable {
                (1..1000).forEach {
                    add()
                }
            }).start()
        }
        Thread.sleep(1000)

        log("${atomicInteger.get()}")
    }

    //unsafe类 CAS compare And Swap,原值,旧的预期值,新的预期值
    //自旋
    fun add() {
        atomicInteger.incrementAndGet()
    }

}