package com.example.bod.kotlincoroutines.socket

import com.example.bod.kotlincoroutines.utils.log
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.ServerSocket

/**
 *
 * @ClassName: SocketTest
 * @Description:
 * @CreateDate: 2020/1/27
 */

fun main() {
    ServerSocket(1081).apply {
        log("Waiting...")
        val mSocket = accept()
        //receive msg from client
        log("Starting...")
        mSocket.use {
            it.getInputStream().use { input ->
                var data: String?
                BufferedReader(InputStreamReader(input)).use {
                    while (!(readLine()
                                    .apply { data = this }
                                    .isNullOrEmpty())) {
                        log("Msg From Client $data")
                    }
                }
            }
            it.shutdownInput()
        }
    }


}