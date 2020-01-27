package com.example.bod.kotlincoroutines.socket.shortlink

import timber.log.Timber
import java.net.DatagramPacket
import java.net.DatagramSocket

/**
 *
 * @ClassName: UdpService
 * @Description:
 * @CreateDate: 2020/1/27
 */
fun main() {
    val datagramSocket = DatagramSocket(1080)
    val bytes = ByteArray(1024)
    //packet接收数据
    val datagramPacket = DatagramPacket(bytes, bytes.size)
    //socket发送数据
    //This method blocks until a datagram is received
    datagramSocket.receive(datagramPacket)

    datagramPacket.data.run {
        val str = String(this)
       print("Data $str")
    }
}