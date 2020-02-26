package com.example.bod.kotlincoroutines.aidlfile

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 *
 * @ClassName: ServerService
 * @Description:
 * @CreateDate: 2020/2/22
 */
class ServerService: Service() {

    //创建服务对外提供具体的业务
    override fun onBind(intent: Intent?): IBinder? {
        return BookImpl()
    }

}