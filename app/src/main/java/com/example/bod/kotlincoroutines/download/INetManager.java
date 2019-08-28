package com.example.bod.kotlincoroutines.download;

import java.io.File;

/**
 * @ClassName: INetManager
 * @Description:
 * @CreateDate: 2019/8/27
 */
interface INetManager {
    //回调和取消请求的结果
    void get(String url,INetCallback iNetCallback,Object requestTag);

    void download(String uir, File targetFile,INetDownloadCallback iNetDownloadCallback,Object fileTargetTag);

    void cancle(Object tag);
}
