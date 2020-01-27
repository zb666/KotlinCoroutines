package com.example.bod.kotlincoroutines.socket

import com.example.bod.kotlincoroutines.utils.log
import timber.log.Timber
import java.io.File
import java.nio.charset.Charset
import java.util.concurrent.LinkedBlockingQueue

/**
 *
 * @ClassName: KotlinUseDemo
 * @Description:
 * @CreateDate: 2020/1/27
 */
fun main(){
    File("D:\\hometest.txt")
            .readLines(charset=Charset.defaultCharset()).forEach {
        log("$it")
    }

    val linkedBlockingQueue = LinkedBlockingQueue<String>()

    fun testQueue(){
        linkedBlockingQueue.put("String A")
        val lbkStr = linkedBlockingQueue.take()
    }
}