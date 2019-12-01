package com.example.bod.kotlincoroutines.demo.sync

import com.example.bod.kotlincoroutines.utils.log
import java.util.concurrent.atomic.AtomicInteger

/**
 *
 * @ClassName: SynDemo1
 * @Description:
 * @CreateDate: 2019/12/1
 */

fun main() {

// synScope() 锁的作用域
    Thread(Runnable {
        reEnter()
    }).start()

    Thread.sleep(100)

    reEnter()
}

fun reEnter() {
    reTest()
}

val atomic = AtomicInteger(0)

@Synchronized
fun reTest() {
    //syn获取到锁之后 递归的访问该方法，不需要重新获取到锁
    log("start log")
    if (atomic.incrementAndGet()==1) {
        reTest()
    }
    log("end log")
}

//锁的作用域
//对象(普通方法)
//类锁，实例锁
private fun synScope() {
    Thread(Runnable {
        Test().testA()
    }).start()

    Thread(Runnable {
        Test().testA()
    }).start()
}

class Test {
    fun testA() {
        synchronized(this) {
            log("111")
            Thread.sleep(100)
            log("222")
        }
    }
}
