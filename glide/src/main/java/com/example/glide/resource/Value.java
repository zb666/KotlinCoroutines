package com.example.glide.resource;

import android.graphics.Bitmap;

/**
 * @ClassName: Value
 * @Description:
 * @CreateDate: 2020/2/8
 */
public class Value {

    private final String TAG = Value.class.getSimpleName();

    private static Value value;

    public static Value getInstance() {
        if (null == value) {
            synchronized (Value.class) {
                if (null == value) {
                    value = new Value();
                }
            }
        }
        return value;
    }

    private Bitmap mBitmap;
    //计数
    private int count;
    //监听
    private ValueCallback callback;

    private String key;

    public String getTAG() {
        return TAG;
    }

    public static Value getValue() {
        return value;
    }

    public static void setValue(Value value) {
        Value.value = value;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ValueCallback getCallback() {
        return callback;
    }

    public void setCallback(ValueCallback callback) {
        this.callback = callback;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void useAction() {
        Tool.checkNotEmpty(mBitmap);
        if (mBitmap.isRecycled()) {
            //已经被回收了
            return;
        }
        count++;
    }

    public void nonUseAction() {
        if (count-- <= 0 && callback != null) {
            //不再使用
            callback.valueNonUserListener(key, this);
        }
    }

    public void recycleBitmap() {
        if (count > 0) {
            return;
        }
        if (mBitmap != null && mBitmap.isRecycled()) {
            return;
        }
        if (mBitmap != null) {
            mBitmap.recycle();
        }
    }

}
