package com.example.bod.kotlincoroutines.glidefolder

import java.util.concurrent.LinkedBlockingQueue

/**
 *
 * @ClassName: RequestManager
 * @Description:
 * @CreateDate: 2019/9/5
 */
object RequestManager {

    //描述请求
     val linkedBlockingQueue = LinkedBlockingQueue<BitmapRequest>()

    fun addBitmapRequest(bitmapRequest: BitmapRequest){
        if (linkedBlockingQueue.contains(bitmapRequest)){

        }
    }

}