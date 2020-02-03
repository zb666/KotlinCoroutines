package com.example.library;

import android.app.Activity;
import android.view.View;

/**
 * @ClassName: ButterKnife
 * @Description:
 * @CreateDate: 2020/2/3
 */
public class ButterKnife {

    public static void bind(Activity activity) {
        //ButterKnifeActivity$ViewBinder
        String className = activity.getClass().getName() + "$ViewBinder";
        try {
            Class<?> viewBindClass = Class.forName(className);
            //bind(final com.example.bod.kotlincoroutines.activity.ButterKnifeActivity target)
            //预编译生成的文件中可以绑定属性
            ViewBinder viewBinder = (ViewBinder) viewBindClass.newInstance();
            viewBinder.bind(activity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
