package com.example.bod.kotlincoroutines.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @ClassName: SocketClient
 * @Description:
 * @CreateDate: 2020/1/27
 */
public class SocketClient {

    public static void main(String... args) throws IOException {
        //指定服务器的ip地址和端口号
        Socket socket = new Socket("127.0.0.1", 1081);
        //获取输出流，向服务器发送
        //向服务器写信息
        OutputStream osClient = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(osClient);
        //tcp缓冲队列 要使用flush进行刷新
        printWriter.write("Hello from client");
        printWriter.flush();
        socket.shutdownOutput();
        socket.close();
        //四次挥手会有Time Wait
    }
}


