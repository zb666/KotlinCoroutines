package com.example.bod.kotlincoroutines.demo

import com.example.bod.kotlincoroutines.utils.log
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.RejectedExecutionHandler
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 *
 * @ClassName: 线程池
 * @Description:
 * @CreateDate: 2019/11/30
 */
object 线程池 {
    //tips 池化所需要的开销应该低于线程切换带来的开销
    //Executor:->ExecutorService->ScheduledExecutorService->ThreadPoolExecutor(基础，标准的线程池)->ScheduledThreadPoolExecutor
    //线程池的延时时间
    @JvmStatic
    fun main(args: Array<String>) {

        //核心线程数 最大线程数 超出核心线程数的存活时间比如5秒，最大拒绝策略
        Executors.newFixedThreadPool(1)

        //最大的线程大小是 10+3 ，核心线程数是5，15个任务提交的话 有2个会被拒绝掉
        val threadPoolExecutor = ThreadPoolExecutor(5,10,5,TimeUnit.SECONDS,LinkedBlockingQueue<Runnable>(3), RejectedExecutionHandler { r, executor ->
            log("拒绝策略觉醒")
        })

        (1..15)
                .forEach {
            threadPoolExecutor.submit {
                Thread.sleep(3000L)
            }
        }

//        val threadPool=ThreadPoolExecutor.AbortPolicy.
    }
}