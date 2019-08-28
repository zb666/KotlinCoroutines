package com.example.bod.kotlincoroutines.download;

/**
 * @ClassName: INetCallback
 * @Description:
 * @CreateDate: 2019/8/27
 * 这个是下载Json数据的链接
 */
public interface INetCallback {
    void success(String response);

    void failed(Throwable throwable);
}
