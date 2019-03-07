package com.example.bod.kotlincoroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.experimental.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)  = runBlocking{
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addName = LogUtils.addName()
        toast(addName)

//        testLaunch() 协程 挂起 主线程阻塞
        runBlocking {
            repeat(1) {
                toast("协程挂起+$it " + Thread.currentThread().name)
                delay(500)
            }
        }
        toast("主线程开始进行余下的操作 " + Thread.currentThread().name)

        toast("launch 的方式不会造成主线程的阻塞")

        launch(CommonPool) {
            delay(3000L)
            toast("协程执行完毕")
        }

        toast("主线程执行完毕")

        //方式三
        val job = async(CommonPool) {
            delay(500L)
            toast("async ${Thread.currentThread().name}")
            return@async "hello"
        }


        toast(job.await())


    }


    fun testLaunch() {
        launch {
            delay(1000L)
            toast("World")
        }
        toast("Hello")

        runBlocking {
            delay(2000L)
        }
    }

    fun MainActivity.toast(logString: String) {
        Log.d("MainActivityToast", logString)
    }
}
