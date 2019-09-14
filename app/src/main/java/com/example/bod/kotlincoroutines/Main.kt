package com.example.bod.kotlincoroutines

import kotlinx.coroutines.*
import log

/**
 *
 * @ClassName: Main
 * @Description:
 * @CreateDate: 2019/8/30
 */


fun main() {
//    print("aaaaa\n")l
//
//    val list = listOf(0..10,1..20,2..30)
//
//    //把集合的集合打平
//    // 其次把里面的集合元素做map
//   list.flatMap {
//       it.map {
//           "flatMap $it \n"
//       }
//   }.forEach {
//       print(it)
//   }
//
//    list.map {
//        it.toString()
//    }


    GlobalScope.launch {
        log("111")
    }
    log("start")
    log("end " + loadString())

    GlobalScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.Unconfined) {
            delay(1000)
            log("load start")
        }
        log("loadData finish")
    }

    Thread.sleep(2000)

}

suspend fun addEle() {
    loadData()
}


suspend fun loadData() {
    withContext(Dispatchers.IO) {
        (1.100).takeIf {
            it.equals("2")
        }
        log("loadData async")
    }
}

//runBlocking 会造成当前的阻塞
fun loadString() = runBlocking {
    delay(1_000)
    withContext(Dispatchers.Default) { loadAsync() }
}

fun loadAsync() = "loadAsync"

