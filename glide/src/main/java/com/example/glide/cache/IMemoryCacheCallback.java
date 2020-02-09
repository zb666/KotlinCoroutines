package com.example.glide.cache;

import com.example.glide.resource.Value;

/**
 * @ClassName: IMemoryCacheCallback
 * @Description:
 * @CreateDate: 2020/2/9
 */
public interface IMemoryCacheCallback {
    /**
     * 内存缓存回调
     * @param key
     * @param oldValue
     */
    void entryRemovedMemoryCache(String key, Value oldValue);
}
