package com.example.bod.kotlincoroutines.socket.shortlink

import android.location.Address
import timber.log.Timber
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

/**
 *
 * @ClassName: UdpClient
 * @Description:
 * @CreateDate: 2020/1/27
 */
fun main(){

    val datagramSocket = DatagramSocket()
    //创建DatagramSocket发送
    val hello = "Hello Service"
    val byteLength = hello.toByteArray()
    //packet 包装 包裹
    DatagramPacket(
            hello.toByteArray(),
            byteLength.size,
            InetAddress.getLocalHost(),
            1080).run {
            datagramSocket.send(this.apply {
            Timber.d("Udp:${this.address.toString()}")
        })
    }


}