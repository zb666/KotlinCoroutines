package com.example.bod.kotlincoroutines.download;

import android.app.Activity;

import com.example.bod.kotlincoroutines.LogUtils;

import java.io.File;

/**
 * @ClassName: AppUpdate
 * @Description:
 * @CreateDate: 2019/8/27
 */
public class AppUpdate {

    private static volatile AppUpdate sAppUpdate;


    //具体功能通过接口抽象隔离
    private INetManager mNetManager = new OkHttpManager();

    public void setNetManager(INetManager iNetManager) {
        mNetManager = iNetManager;
    }

    private AppUpdate() {

    }

    public static AppUpdate getInstance() {
        //为了性能
        if (sAppUpdate == null) {
            synchronized (AppUpdate.class) {
                //为了保证单例
                if (sAppUpdate == null) {
                    sAppUpdate = new AppUpdate();
                }
            }
        }
        return sAppUpdate;
    }

    public INetManager getNetManager() {
        return mNetManager;
    }

    //开始检测
    public void checkUpdate(String url, Object target, final INetCallback iNetCallback) {
//        mNetManager.download();
        mNetManager.get(url, new INetCallback() {
            @Override
            public void success(String response) {
                iNetCallback.success(response);
                //这里show出log
            }

            @Override
            public void failed(Throwable throwable) {
                iNetCallback.failed(throwable);
            }
        }, target);
    }

    public void startDonwload(final Activity context, String url) {
        File targetFile = new File(context.getCacheDir(), "target.apk");
        if (!targetFile.exists()){
            targetFile.mkdir();
        }
        //向APK中写入数据
        mNetManager.download(url, targetFile, new INetDownloadCallback() {
            @Override
            public void success(File apkFile) {
                //比对MD5
                String fileMD5 = AppUtils.getFileMD5(apkFile);
                LogUtils.Companion.showLog("DonwLoad", fileMD5);
                //失败 || 成功
                AppUtils.installApk(context, apkFile);

            }

            @Override
            public void progress(float progress) {
                LogUtils.Companion.showLog("startDonwload progress", progress + " ");

            }

            @Override
            public void failed(Throwable throwable) {
                LogUtils.Companion.showLog("Throwable", throwable.toString());
            }
        }, context);
    }

}
