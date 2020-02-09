package com.example.glide.load_data;

import com.example.glide.resource.Value;

import java.util.concurrent.Executor;

/**
 * @ClassName: ResponseListener
 * @Description:
 * @CreateDate: 2020/2/9
 */
public interface ResponseListener {
    void responseSuccess(Value value);
    void responseException(Exception ex);
}
