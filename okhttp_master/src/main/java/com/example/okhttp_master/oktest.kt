package com.example.okhttp_master

import java.util.concurrent.*

/**
 *
 * @ClassName: oktest
 * @Description:
 * @CreateDate: 2020/2/4
 */
fun main(){
    //60秒的闲置时间 超过就回收，然后实例化新的

    (1..20).map {
        executorService.execute(Runnable {
            println("${Thread.currentThread().name}")
        })
    }
    requestStart()
}

fun requestStart(){
    val connectionPool = ConnectionPool()
    Thread(Runnable {
        UseConnectionPool().run {
            useConnectionPool(connectionPool,"restapi.amap.com", 80)
            useConnectionPool(connectionPool,"restapi.amap.com", 80)
            useConnectionPool(connectionPool,"restapi.amap.com", 80)
            useConnectionPool(connectionPool,"restapi.amap.com", 80)
        }
    }).start()
}

val executorService = ThreadPoolExecutor(
        0,
        60,
        Long.MAX_VALUE,
        TimeUnit.SECONDS,
        LinkedBlockingDeque<Runnable>()
)