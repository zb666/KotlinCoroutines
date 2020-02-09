package com.example.glide

import com.example.glide.cache.MemoryCache
import com.example.glide.resource.Value

/**
 *
 * @ClassName: CacheTest
 * @Description:
 * @CreateDate: 2020/2/9
 */
fun main(){
    val memoryCache = MemoryCache(16)
    memoryCache.put("Bob",Value())
    val valueGet = memoryCache.get("Bob")
    println("GetedValue: $valueGet")
    memoryCache.shoundOnRemove("Bob")
    memoryCache.setiMemoryCacheCallback { key, oldValue ->
        println("CacheCallBack：$key $oldValue")
    }
}