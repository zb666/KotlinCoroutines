package com.example.glide.cache;

import com.example.glide.resource.Tool;
import com.example.glide.resource.Value;
import com.example.glide.resource.ValueCallback;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ActiveCache
 * @Description:
 * @CreateDate: 2020/2/9
 */
public class ActiveCache {

    private Map<String, WeakReference<Value>> mapList = new HashMap<>();
    private ReferenceQueue<Value> queue;//监听这个弱引用是否被移除了
    private boolean isCloseThread;
    private Thread thread;
    private boolean isShoudonRemove;
    private ValueCallback valueCallback;

    public ActiveCache(ValueCallback valueCallback) {
        this.valueCallback = valueCallback;
    }

    //添加缓存
    public void put(String key, Value value) {
        Tool.checkNotEmpty(key);
        //绑定Value的监听->value 没有被使用了，就会发起这个监听
        value.setCallback(valueCallback);
        //存储到容器中
        mapList.put(key, new CustomWeakReference(value, getQueue(), key));
    }

    public Value get(String key) {
        WeakReference<Value> valueWeakReference = mapList.get(key);
        if (null != valueWeakReference) {
            return valueWeakReference.get();//返回Value
        }
        return null;
    }

    /**
     * remove value by hand
     * return the removed value
     *
     * @param key
     * @return
     */
    public Value remove(String key) {
        isShoudonRemove = true;
        WeakReference<Value> referenceValue = mapList.remove(key);
        isShoudonRemove = false;
        if (null != referenceValue) {
            return referenceValue.get();
        }
        return null;
    }

    public void closeThread() {
        isCloseThread = true;
//        if (null!=thread){
//            thread.interrupt();//中断线程
//            try {
//                thread.join(TimeUnit.SECONDS.toMillis(5));
//                if (thread.isAlive()){
//                    throw new IllegalStateException("stop thread failed");
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        mapList.clear();
        System.gc();
    }

    private ReferenceQueue<Value> getQueue() {
        if (queue == null) {
            queue = new ReferenceQueue<>();
        }
        //循环监听弱引用
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                if (!isCloseThread) {
                    //阻塞式的方法
                    try {
                        //已经被回收了 就会执行该方法
                        Reference<? extends Value> remove = queue.remove();
                        CustomWeakReference weakReference = (CustomWeakReference) remove;
                        if (mapList != null && !mapList.isEmpty() && !isShoudonRemove) {
                            mapList.remove(weakReference.get());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
        return queue;
    }


    //自定义弱引用，目的是为了在弱引用创建的初期
    //就定义好监听
    public class CustomWeakReference extends WeakReference<Value> {

        private String key;

        public CustomWeakReference(Value referent, ReferenceQueue<? super Value> q, String key) {
            super(referent, q);
            key = key;
        }
    }

}
