package com.example.okhttp_master;

import android.util.Log;

import java.io.IOException;
import java.net.Socket;

/**
 * @ClassName: HttpConnection
 * @Description:
 * @CreateDate: 2020/2/4
 */
public class HttpConnection {
    private final String TAG = HttpConnection.class.getSimpleName();
    Socket socket;
    long lastUseTime;//连接对象最后的使用时间

    public HttpConnection(final String host, final int port) {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void closeSocket() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnectionActive(String host, int port) {
        if (socket == null)
            return false;

        try{
            if (socket.getInetAddress().getHostName()
                    == host && socket.getPort() == port)
                return true;
        }catch (Exception ex){
            Log.e(TAG,"isConnectionActive: "+ex.getMessage());
        }
        return false;
    }
}
