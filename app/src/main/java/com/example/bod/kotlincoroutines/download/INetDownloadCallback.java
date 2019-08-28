package com.example.bod.kotlincoroutines.download;

import java.io.File;

/**
 * @ClassName: INetDownloadCallback
 * @Description:
 * @CreateDate: 2019/8/27
 * 这个是下载Apk的链接
 */
public interface INetDownloadCallback {
    void success(File apkFile);
    void progress(float progress);
    void failed(Throwable throwable);
}
