package com.example.glide;

import android.content.Context;
import android.os.Process;
import android.widget.ImageView;

import androidx.loader.app.LoaderManager;

import com.example.glide.cache.ActiveCache;
import com.example.glide.cache.IMemoryCacheCallback;
import com.example.glide.cache.MemoryCache;
import com.example.glide.disk.DiskLruCacheImpl;
import com.example.glide.fragment.LifecycleCallback;
import com.example.glide.load_data.LoadDataManager;
import com.example.glide.load_data.ResponseListener;
import com.example.glide.resource.Key;
import com.example.glide.resource.Tool;
import com.example.glide.resource.Value;
import com.example.glide.resource.ValueCallback;

/**
 * @ClassName: RequestTargetEngine
 * @Description:
 * @CreateDate: 2020/2/9
 */
public class RequestTargetEngine implements LifecycleCallback, ValueCallback, ResponseListener, IMemoryCacheCallback {

    private final String TAG = RequestTargetEngine.class.getSimpleName();

    private ActiveCache activeCache;
    private MemoryCache memoryCache;
    DiskLruCacheImpl diskLruCacheImpl;
    private final int MEMORY_MAX_SIZE = 1024 * 1024 * 60;

    public RequestTargetEngine() {
        if (activeCache == null) {
            activeCache = new ActiveCache(this);
        }
        if (memoryCache == null) {
            memoryCache = new MemoryCache(MEMORY_MAX_SIZE);
        }
        diskLruCacheImpl = new DiskLruCacheImpl();
    }

    private String path;
    private Context glideContext;
    private String key;
    private ImageView imageView;//显示的目标

    @Override
    public void glideInitAction() {
        //Glide生命周期开启之前完成初始化
    }

    @Override
    public void glideStopAction() {
        //Glide生命周期已经停止了
    }

    @Override
    public void glideRecycleAction() {
        //Glide 生命周期 结束之后进行释放，缓存资源释放等操作
        if (activeCache != null) {
            activeCache.closeThread();
        }
    }

    public void loadValueInitAction(String path, Context reqquestManagerContext) {
        this.path = path;
        this.glideContext = reqquestManagerContext;
        key = new Key(path).getKey();
        Tool.assertMainThread();
    }

    public Value cacheAction() {
        Value value = activeCache.get(key);
        //每次被使用后都会加一次
        if (value != null) {
            //Get Value Success From ActiveResource
            value.useAction();
            return value;
        }
        //从内存缓存中去寻找，如果找到了就移动到活动缓存中
        value = memoryCache.get(key);
        if (value != null) {
            //移除活动缓存，加入到活动缓存中
            memoryCache.shoundOnRemove(key);
            activeCache.put(key, value);
            value.useAction();
            //命中的是内存缓存
        }
        //第三部 从磁盘缓存中进行寻找,
        //找到了就加入到活动缓存中
        value = diskLruCacheImpl.get(key);
        if (null != value) {
            activeCache.put(key, value);
            //使用一次加一次
            value.useAction();
            return value;
        }
        //本次加载的资源是从磁盘缓存中获取到的资源
        value = new LoadDataManager().loadResource(path, this, glideContext);
        if (value != null) {
            return value;
        }
        return null;
    }

    /**
     * 活动缓存的资源不再被使用了
     * 回调监听 告诉外界 Value值不再使用了
     *
     * @param key
     * @param value
     */
    @Override
    public void valueNonUserListener(String key, Value value) {
        if (key != null && value != null) {
            memoryCache.put(key, value);
        }
    }

    @Override
    public void responseSuccess(Value value) {
        if (value != null) {
            saveCache(key, value);
            imageView.setImageBitmap(value.getmBitmap());
        }
    }

    private void saveCache(String key, Value value) {
        value.setKey(key);
        if (diskLruCacheImpl != null) {
            diskLruCacheImpl.put(key, value);
        }
    }

    @Override
    public void responseException(Exception ex) {

    }

    /**
     * 内存缓存不再使用了 就添加到复用池
     *
     * @param key
     * @param oldValue
     */
    @Override
    public void entryRemovedMemoryCache(String key, Value oldValue) {
        //添加到复用池
    }

    public void into(ImageView imageView) {
        this.imageView = imageView;

        Tool.checkNotEmpty(imageView);
        Tool.assertMainThread();
        Value value = cacheAction();//从Cache查找
        if (value != null) {
            //使用完之后 减一
            value.nonUseAction();
            imageView.setImageBitmap(value.getmBitmap());
        }
    }
}
