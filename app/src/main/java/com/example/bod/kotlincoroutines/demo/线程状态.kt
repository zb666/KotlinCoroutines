package com.example.bod.kotlincoroutines.demo

import com.example.bod.kotlincoroutines.utils.log

/**
 * @ClassName: 线程状态
 * @Description:
 * @CreateDate: 2019/11/30
 */
object 线程状态 {


    @JvmStatic
    fun main(args: Array<String>) {
        val thread1 = Thread(Runnable { log("111") }, "thread 111")

        //New
        log(thread1.state)

        thread1.start()

        //Runnable
        log("after invoke ${thread1.state}")

        //让线程被CPU调度起来
        Thread.sleep(2000)

        log("finally state: ${thread1.state}")

        val blockThread = Thread(Runnable {
            synchronized(线程状态::class.java) {
                log("block printing:${Thread.currentThread().state}")
            }
        }, "Block")

        synchronized(线程状态::class.java){
            log(blockThread.state) //就绪状态
            blockThread.start()
            log(blockThread.state)
            Thread.sleep(200)
            log(Thread.currentThread().state)
        }

        log("After Block, BlockThread have got syn lock :${blockThread.state}")

        Thread.sleep(1000)
        log(blockThread.state)

    }
}
