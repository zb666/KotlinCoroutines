package com.example.bod.kotlincoroutines;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

/**
 * @ClassName: MyNested
 * @Description:
 * @CreateDate: 2019/11/10
 */
public class MyNested extends NestedScrollView {
    public MyNested(@NonNull Context context) {
        super(context);
    }

    public MyNested(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNested(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
