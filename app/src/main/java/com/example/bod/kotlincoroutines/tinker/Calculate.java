package com.example.bod.kotlincoroutines.tinker;

import android.content.Context;
import android.widget.Toast;

/**
 * @ClassName: Calculate
 * @Description:
 * @CreateDate: 2020/2/15
 */
public class Calculate {
    public void calculate(Context context){
        int a = 666;
        int b = 1;
        Toast.makeText(context, ""+a/b, Toast.LENGTH_SHORT).show();
    }
}
