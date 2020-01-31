package com.example.bod.kotlincoroutines.channnel

import com.example.bod.kotlincoroutines.utils.log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.broadcast
import kotlinx.coroutines.channels.receiveOrNull
import java.nio.file.Files.list
import kotlin.coroutines.experimental.coroutineContext

/**
 *
 * @ClassName: testChannel
 * @Description:
 * @CreateDate: 2020/1/31
 */
fun main() = runBlocking{
//    basics()
    broadCast()
}

suspend fun broadCast(){
//  val broadcastChannel =  BroadcastChannel<Int>(Channel.BUFFERED)

    val sourceChannel = GlobalScope.broadcast {
        for (i in 1..5) {
            send(i)
        }
    }

    //1 - 5好协程都会收到5个数据
    List(5){index->
        GlobalScope.launch {
            val receiveChannel = sourceChannel.openSubscription()
            for (channelIndex in receiveChannel) {
                log("[协程 $channelIndex] received: $channelIndex")
            }
        }.join()
    }

}

suspend fun basics() {
    //一次性发完 然后才调用接受的方法
//    val channel = Channel<Int>(Channel.RENDEZVOUS)
//    val channel = Channel<Int>(Channel.UNLIMITED)
//    val channel = Channel<Int>(Channel.CONFLATED)
    val channel = Channel<Int>(Channel.BUFFERED)
//    val channel = Channel<Int>(1)

    val producer = GlobalScope.launch {
        for (i in 0..3) {
            log("sending:$i")//, i)
            channel.send(i)
            log("sent:$i")//, i)
        }
        channel.close()
    }

    val consumer = GlobalScope.launch {
        while (!channel.isClosedForReceive) {
            log("receiving")
            val value = channel.receiveOrNull()
            log("received: $value")//, )
        }
    }

    producer.join()
    consumer.join()
}


suspend fun test() = suspendCancellableCoroutine<Int> {  }

suspend fun withTest() = withContext(Dispatchers.IO){
    "dsfsdfd"
    123456
    123
    "sdfdsf"
}