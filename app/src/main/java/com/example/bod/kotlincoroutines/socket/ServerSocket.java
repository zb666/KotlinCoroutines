package com.example.bod.kotlincoroutines.socket;

import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @ClassName: ServerSocket
 * @Description:
 * @CreateDate: 2020/1/27
 */
public class ServerSocket {

    private static java.net.ServerSocket mServer = null;
    private static final int PORT = 12306;
    private List<Socket> mClients = new ArrayList<>();
    private ExecutorService mExec = null;

    public ServerSocket(){
        //开启服务

    }

    public static void main(String ...args){

    }

}
