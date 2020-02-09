package com.example.glide.cache;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.collection.LruCache;

import com.example.glide.resource.Value;

/**
 * @ClassName: MemoryCache
 * @Description:
 * @CreateDate: 2020/2/9
 */
public class MemoryCache extends LruCache<String, Value> {

    private boolean shoudOnRemove;

    public MemoryCache(int maxSize) {
        super(maxSize);
    }

    public Value shoundOnRemove(String key){
        shoudOnRemove = true;
        Value value = remove(key);
        shoudOnRemove = false; //!shoundOnRemove
        return value;
    }

    private IMemoryCacheCallback iMemoryCacheCallback;

    public void setiMemoryCacheCallback(IMemoryCacheCallback iMemoryCacheCallback) {
        this.iMemoryCacheCallback = iMemoryCacheCallback;
    }

    @Override
    protected int sizeOf(String key, Value value) {
        Bitmap bitmap = value.getmBitmap();
        int sdkInt = Build.VERSION.SDK_INT;
        //复用
        //API 12 3.0 bitmap.getByteCount()
        //API 19 4.4 bitmap.getAllocationByteCount()

        if (sdkInt>=Build.VERSION_CODES.KITKAT){
            return bitmap.getAllocationByteCount();
        }
        return bitmap.getByteCount();
    }

    /**
     * 重复的key
     * 最少使用的元素会被移除,数据被动移除
     * @param evicted
     * @param key
     * @param oldValue
     * @param newValue
     */
    @Override
    protected void entryRemoved(
            boolean evicted,
            String key,
            Value oldValue,
            Value newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);
        if (iMemoryCacheCallback!=null &&!shoudOnRemove){
            iMemoryCacheCallback.entryRemovedMemoryCache(key,oldValue);
        }
    }


}
