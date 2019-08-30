package com.example.bod.kotlincoroutines.utils;

import android.Manifest;
import android.app.Activity;
import android.os.Build;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * @ClassName: EasyUtil
 * @Description:
 * @CreateDate: 2019/8/30
 */
public class EasyUtil {

    public static boolean checkInitPermission(Activity activity) {
        // SDK 小于23默认已经授权
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        String[] perms = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};

        // 手机状态和写SDCARD的权限是必须的
        if (EasyPermissions.hasPermissions(activity, perms)) {
            return true;
        } else {
            //EasyPermissions.requestPermissions(activity, "需要存储数据到设备！", PERMISSION_REQUEST_CODE_INIT, perms);
            return false;
        }
    }

}
