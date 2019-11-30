package com.example.bod.kotlincoroutines.demo

import com.example.bod.kotlincoroutines.utils.log
import java.util.*

/**
 *
 * @ClassName: 线程死锁
 * @Description:
 * @CreateDate: 2019/11/30
 */
object 线程死锁 {
    const val a = "a"
    const val b = "b"

    @JvmStatic
    fun main(args: Array<String>) {
        val thread1 = Thread(Runnable {
            synchronized(a) {
                log("A get a")
                Thread.sleep(1000)//让B有足够时间获取到B锁
                synchronized(b) { //如果注释此行，那么A顺利执行完，让出b锁，B也就能顺利执行完了
                    log("A finish work")
                }
            }
        })
        val thread2 = Thread(Runnable {
            Thread.sleep(100)//有可能A比B快让出Cpu时间片  让A先进行打印
            synchronized(b) {
                log("B get b ")
                synchronized(a) {
                    log("B finish work")
                }
            }
        })
        thread1.start()
        thread2.start()
    }

}