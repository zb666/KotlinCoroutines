package com.example.glide.referencetest

import java.lang.ref.PhantomReference
import java.lang.ref.ReferenceQueue
import java.lang.ref.SoftReference
import java.lang.ref.WeakReference
import kotlin.math.log

/**
 *
 * @ClassName: refer
 * @Description:
 * @CreateDate: 2020/2/9
 */
fun main() {
    val referenceQueue = ReferenceQueue<Bob>()
    Thread(Runnable {
        while (true) {
            println("开始进行监听")
            val remove = referenceQueue.remove()
            println("检测到引用被回收了${remove.get()}")
        }
    }).start()

    //SoftReferenceQueue 不行，Weak和Phantom Reference都行
    val weakReference = WeakReference(Bob(), referenceQueue)
    Thread.sleep(1000)
    System.gc()
    val afterGCValue = weakReference.get()
    println("Final Value: $afterGCValue")
}

class Bob {

}