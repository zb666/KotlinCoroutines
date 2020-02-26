package com.example.bod.kotlincoroutines.activity

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber

/**
 *
 * @ClassName: FlowActivity
 * @Description:
 * @CreateDate: 2020/2/26
 */
class FlowActivity:CoroutineScope by MainScope() {
    init {
        launch {
            flow {
                delay(1000)
                emit("Flow Value")
            }.flatMapConcat{
                flow {
                    delay(2000)
                    emit("Value is : $it")
                }
            }.onStart {
                Timber.d("开始之前")
            }.flowOn(Dispatchers.Default)
                    .onCompletion {
                        Timber.d("已经执行完成了")
                    }.catch {
                        //错误处理
                    }.collect {
                        Timber.d("已经执行完成了Finally Value $it")
                    }
        }
    }
}