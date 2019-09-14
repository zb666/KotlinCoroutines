package com.example.bod.kotlincoroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 *
 * @ClassName: Edxt
 * @Description:
 * @CreateDate: 2019/9/4
 */
suspend fun zip(vararg f: (suspend () -> Any?)) {
    coroutineScope {
        f.map {
            async { it() }
        }.forEach {
            it.await()
        }
    }
}
