package com.example.bod.kotlincoroutines.demo

import android.os.Message
import android.os.SystemClock
import com.example.bod.kotlincoroutines.utils.log
import java.util.concurrent.atomic.AtomicInteger

/**
 *
 * @ClassName: 原子性
 * @Description:
 * @CreateDate: 2019/12/1
 *
 * CAS
 * 只在方法中存在的线程私有数据
 * int cur = 1
 * int value = +cur
 * i = value
 * CAS硬件指令。java语言无法改变内存.
 *
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
        Message.obtain()
        atomicInteger.incrementAndGet()
    }

}