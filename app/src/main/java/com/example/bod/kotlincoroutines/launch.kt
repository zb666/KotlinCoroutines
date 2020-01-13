package com.example.bod.kotlincoroutines

import android.os.SystemClock
import com.example.bod.kotlincoroutines.utils.log
import com.example.bod.kotlincoroutines.utils.printLog
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.intellij.lang.annotations.Flow

fun main() {
  GlobalScope.launch {
   log("Global log")
  }

  runBlocking {
      delay(1000)
      log("Running Block")
  }

    val demo = Demo()
    val f:(String)->Unit = demo::printDemo
    f.invoke("DemoStart")


    val ints = flow<Int> {
        for (i in 1..10){
            delay(100)
            emit(i)
        }
    }

    runBlocking {
        ints.collect {
            print("result: $it")
        }
    }


    Thread.sleep(3000)

}


class Demo{

    fun printDemo(str:String){
        log(str)
    }
}

//var定义了变量 这个变量就会被保存下来
//如果仅写了x:Int 就不能处理
class SimpleClass :IKotlin{
    override val simple: Int
        get() = 123


    init {

    }
}


interface IKotlin{
    fun method(){

    }

    val simple:Int
}