package com.example.glide.load_data;

import android.content.Context;

import com.example.glide.resource.Value;

/**
 * @ClassName: ILoadData
 * @Description:
 * @CreateDate: 2020/2/9
 */
public interface ILoadData {
    Value loadResource(String path, ResponseListener responseListener, Context context);
}
