package com.example.okhttp_master;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ConnectionPool
 * @Description: 连接池
 * @CreateDate: 2020/2/4
 */
public class ConnectionPool {


    private List<> mList = new  ArrayList<String>();

    private final String TAG = ConnectionPool.class.getSimpleName();

    private long keepAliveTime;

    private boolean cleanRunnableFlag;

    /**
     * 专门存放连接对象的队列
     */
    private static Deque<HttpConnection> httpConnectionDeque;

    public ConnectionPool() {
        this(1, TimeUnit.MINUTES);
        httpConnectionDeque = new ArrayDeque<>();
    }

    public ConnectionPool(long keepAlice, TimeUnit timeUnit) {
        keepAliveTime = timeUnit.toMillis(keepAlice);
    }

    private Runnable cleanRunnable = new Runnable() {
        @Override
        public void run() {
            //开线程 清理闲置的连接
            long nextCheckCleanTime = clean(System.currentTimeMillis());
            if (nextCheckCleanTime == -1) {
                //结束了
                return;
            }
            if (nextCheckCleanTime > 0) {
                //等待一段时间 再去检查 是否要去清理
                synchronized (ConnectionPool.this) {
                    try {
                        ConnectionPool.this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    private long clean(long currentTimeMills) {
        long idleRecordSave = -1;
        synchronized (this) {
            Iterator<HttpConnection> iterator = httpConnectionDeque.iterator();
            while (iterator.hasNext()) {
                HttpConnection httpConnection = iterator.next();
                //满足条件则进行清理，否则就重置最大的闲置时间
                long idleTime = currentTimeMills - httpConnection.lastUseTime;
                if (idleTime > keepAliveTime) {
                    //>最大的闲置时间
                    iterator.remove();
                    //关闭Socket
                    httpConnection.closeSocket();
                    continue;
                }
                if (idleRecordSave < idleTime) {
                    idleRecordSave = idleTime;
                }
                if (idleRecordSave >= 0) {
                    return keepAliveTime - idleRecordSave;
                }
            }
            return idleRecordSave;
        }
    }

    /**
     * 复用的决策 线程池
     * @param httpConnection
     */
    private Executor threadPoolExecutor =
            new ThreadPoolExecutor(
                    0,
                    Integer.MAX_VALUE,
                    60, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(),
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread thread = new Thread(r,"ConnectionPool");
                            thread.setDaemon(true);
                            return thread;
                        }
                    }
            );

    //添加连接池对象
    public synchronized void putConnection(HttpConnection httpConnection){
        //添加连接的对象
        if (!cleanRunnableFlag){
            cleanRunnableFlag = true;
            threadPoolExecutor.execute(cleanRunnable);
        }
        httpConnectionDeque.add(httpConnection);
        int size = httpConnectionDeque.size();
    }

    //获取连接池中有效的连接
    public HttpConnection getConnection(String host,int port){
        Iterator<HttpConnection> iterator = httpConnectionDeque.iterator();
        while (iterator.hasNext()) {
            HttpConnection httpConnection = iterator.next();
            if (httpConnection.isConnectionActive(host,port)){
                iterator.remove();
                //找到连接了 可以进行复用
                return httpConnection;
            }
        }
        return null;
    }


}

