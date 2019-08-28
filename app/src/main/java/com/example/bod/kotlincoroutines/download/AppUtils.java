package com.example.bod.kotlincoroutines.download;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Process;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: AppUtils
 * @Description:
 * @CreateDate: 2019/8/27
 */
public class AppUtils {


    @RequiresApi(api = Build.VERSION_CODES.P)
    public static long getVersionCode(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.getLongVersionCode();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0L;
    }


    public static void installApk(Activity activity, File apkFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri;
        //Android N FileProvider适配
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".fileprovider", apkFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(apkFile);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        activity.startActivity(intent);
        Process.killProcess(Process.myPid());
    }

    public static String getFileMD5(File targetFile) {
        if (targetFile == null || !targetFile.isFile()) return null;

        MessageDigest digest = null;
        FileInputStream fileInputStream = null;
        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            //从TargetFile读取 每次读取1024个字节
            fileInputStream = new FileInputStream(targetFile);
            digest = MessageDigest.getInstance("md5");
            while ((len = fileInputStream.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //解密MD5
        assert digest != null;
        byte[] result = digest.digest();
        BigInteger bigInt = new BigInteger(1, result);
        return bigInt.toString(16);//16进制表示
    }

}
