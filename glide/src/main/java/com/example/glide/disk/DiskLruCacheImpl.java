package com.example.glide.disk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.example.glide.resource.Tool;
import com.example.glide.resource.Value;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @ClassName: DiskLruCacheImpl
 * @Description:
 * @CreateDate: 2020/2/9
 */
public class DiskLruCacheImpl {

    private final String TGA = DiskLruCacheImpl.class.getSimpleName();
    private final String DISKLRU_CACHE_DIR = "disk_lru_cache_dir"; // 磁盘缓存的的目录
    private final int APP_VERSION = 1;//版本号,一旦修改，之前的缓存会失效
    private final int VALUE_COUNT = 1;
    private final long MAX_SIZE = 1024*1024*10;

    private DiskLruCache diskLruCache;

    public DiskLruCacheImpl(){
        File file = new File(Environment.getExternalStorageDirectory()+File.separator+DISKLRU_CACHE_DIR);
        try {
            diskLruCache = DiskLruCache.open(file, APP_VERSION, VALUE_COUNT, MAX_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void put(String key, Value value){
        Tool.checkNotEmpty(key);
        DiskLruCache.Editor editor = null;
        OutputStream outputStream = null;

        try {
            diskLruCache.edit(key);
            outputStream = editor.newOutputStream(0);
            Bitmap bitmap = value.getmBitmap();
            //将Bitmap数据传输到OutPutStream流中
            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            //失败
            try {
                editor.abort();
            } catch (IOException ex) {
                ex.printStackTrace();
            }finally {
                try {
                    editor.commit();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (outputStream!=null){

                    try {
                        outputStream.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public Value get(String key){
        Tool.checkNotEmpty(key);

        InputStream inputStream = null;

        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
            if (snapshot!=null){
                Value value = Value.getInstance();
                inputStream = snapshot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                value.setmBitmap(bitmap);
                //保存Key 唯一的标识
                value.setKey(key);
                return value;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;//为了后续好判断
        }
    }

}
