package com.example.bod.kotlincoroutines

import com.example.bod.kotlincoroutines.utils.log
import org.jetbrains.annotations.TestOnly
import java.util.concurrent.*

/**
 * @ClassName: TestOkhttp
 * @Description:
 * @CreateDate: 2019/10/24
 */

fun main() {
    //Executor
    //--ExecutorSertvice
    //     -- AbsExecutorService
    //        -- ThreadPoolExecutorService

    // core核心线程数 0
    // max最大线程数 max_value
    // 限制时长

    //workQueue: BlockingQueue 阻塞队列 把超出核心线程数大小的任务给保存起来
    //60s闲置时间 60s内复用,60s后就会回收任务
    val executorService: ExecutorService = ThreadPoolExecutor(
            2, Int.MAX_VALUE,
            60, TimeUnit.SECONDS, object : LinkedBlockingDeque<Runnable>() {})

    for (index in 0..20) {
        executorService.execute(kotlinx.coroutines.Runnable {
            Thread.sleep(1000)
//            log("MyName")
        })
    }

    /**
     * ThreadPoolExecutor(0, Integer.MAX_VALUE,
    60L, TimeUnit.SECONDS,
    new SynchronousQueue<Runnable>()
     复用线程池->缓存方案
     线程池工厂
     */

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    Executors.newCachedThreadPool().execute {
        log("ThreadPool")
    }

}


