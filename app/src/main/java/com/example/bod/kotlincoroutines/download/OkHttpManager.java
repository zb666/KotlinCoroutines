package com.example.bod.kotlincoroutines.download;

import android.os.Handler;
import android.os.Looper;

import com.blankj.utilcode.util.Utils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ClassName: OkHttpManager
 * @Description:
 * @CreateDate: 2019/8/27
 */
//可能有其他的Manager5，反正可以提供 get download cancle都可以理解成
// 一个网络请求工具
public class OkHttpManager implements INetManager {


    private static OkHttpClient sOKhttpClient;

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        sOKhttpClient = builder.build();
    }

    @Override
    public void get(String url, final INetCallback iNetCallback, Object requestTag) {
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).get().tag(requestTag).build();
        Call call = sOKhttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                sHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (iNetCallback != null) {
                            iNetCallback.failed(e);
                        }
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                sHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (iNetCallback != null) {
                            try {
                                iNetCallback.success(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                                iNetCallback.failed(e);
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public void download(String url, final File appInstallFile, final INetDownloadCallback iNetDownloadCallback, Object fileTargetTag) {
        if (!appInstallFile.exists()) {
            appInstallFile.getParentFile().mkdirs();
        }
        //下载成功开始写入
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).get().tag(fileTargetTag).build();
        Call call = sOKhttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (iNetDownloadCallback != null) {
                    iNetDownloadCallback.failed(e);
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (iNetDownloadCallback != null) {
                    //开始写入
                    InputStream inputStream = null;
                    OutputStream outputStream = null;
                    //是文件就要有后缀，不然会被当做文件夹
                    final File file1 = new File(Utils.getApp().getCacheDir(), "target");
                    if (!file1.exists()) {
                        file1.mkdir(); //目录不存在 就创建目录
                    }
                    final File file = new File(file1,"aaa.apk");
                    try {
                        inputStream = response.body().byteStream();
                        //指定输出流写入的文件夹
                        outputStream = new FileOutputStream(file);

                        final long totalLen = response.body().contentLength();

                        long curLen = 0;
                        //创建读写缓冲区
                        byte[] buffer = new byte[8 * 1024];
                        int bufferLen = 0;
                        while (!call.isCanceled() && (bufferLen = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer);
                            outputStream.flush();
                            final int finalBufferLen = bufferLen;
                            curLen += bufferLen;
                            final long finalCurLen = curLen;
                            sHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    iNetDownloadCallback.progress(finalCurLen * 1.0f / totalLen);
                                }
                            });
                        }

                        try {
                            appInstallFile.setExecutable(true);
                            appInstallFile.setReadable(true, false);
                            appInstallFile.setWritable(true, false);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        sHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                iNetDownloadCallback.success(file);
                            }
                        });

                    } catch (final Exception ex) {
                        ex.printStackTrace();
                        sHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                iNetDownloadCallback.failed(ex);
                            }
                        });
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void cancle(Object tag) {
        //入队的
        List<Call> calls = sOKhttpClient.dispatcher().queuedCalls();
        for (Call call : calls) {
            if (call != null && call.equals(tag)) {
                call.cancel();
            }
        }

        //正在运行的
        List<Call> runningCalls = sOKhttpClient.dispatcher().runningCalls();
        for (Call runningCall : runningCalls) {
            if (runningCall != null && runningCall.equals(tag)) {
                runningCall.cancel();
            }
        }
    }
}
