package com.example.bod.kotlincoroutines.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.bod.kotlincoroutines.utils.log

/**
 *
 * @ClassName: MyObServer
 * @Description:
 * @CreateDate: 2020/1/11
 */
class MyObServer:LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectedListener(){
        log("connected() invoked")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onStop(){
        log("onStop() invoked")
    }

}