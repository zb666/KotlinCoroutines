package com.example.bod.kotlincoroutines.plugin;

import android.content.Context;
import android.content.res.Resources;

import com.blankj.utilcode.util.Utils;

import java.lang.reflect.Field;

/**
 * @ClassName: LoadUtils
 * @Description:
 * @CreateDate: 2020/1/12
 */
public class LoadUtils {

    void aaa(Context context){
        Class<? extends Context> claZZ = context.getClass();
        try {
            Field classFiled = claZZ.getDeclaredField("");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
