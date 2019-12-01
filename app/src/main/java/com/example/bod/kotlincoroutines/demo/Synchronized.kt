package com.example.bod.kotlincoroutines.demo

import com.example.bod.kotlincoroutines.utils.log

/**
 *
 * @ClassName: Synchronized
 * @Description:
 * @CreateDate: 2019/12/1
 */
object Synchronized {

    //监视器只能被一个线程锁定

    @JvmStatic
    fun main(args: Array<String>) {
        log("111")
    }
    fun test(){
        log("--start print--")
        Thread.sleep(1000)
    }
}