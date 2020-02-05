package com.example.okhttp_master;

/**
 * @ClassName: UseConnectionPool
 * @Description:
 * @CreateDate: 2020/2/4
 */
public class UseConnectionPool {

    //ConnectionPool的管理
    private final String TAG = UseConnectionPool.class.getName();

    //复用连接的时候 判断是否存在这个连接
    //存在就进行复用
    //同时更新最新的使用时间
    public void useConnectionPool(ConnectionPool connectionPool,String host,int port){
        HttpConnection connection = connectionPool.getConnection(host, port);
        if (connection == null){
            //没有对象
            connection = new HttpConnection(host,port);
        }else {
            //复用连接
        }
        connection.lastUseTime = System.currentTimeMillis();
        connectionPool.putConnection(connection);
    }

}
