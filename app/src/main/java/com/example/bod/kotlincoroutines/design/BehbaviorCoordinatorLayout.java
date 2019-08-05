package com.example.bod.kotlincoroutines.design;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.core.view.NestedScrollingParent;

/**
 * @ClassName: BehbaviorCoordinatorLayout
 * @Description:
 * @CreateDate: 2019/8/5
 */
public class BehbaviorCoordinatorLayout extends RelativeLayout implements NestedScrollingParent {
    public BehbaviorCoordinatorLayout(Context context) {
        super(context);
    }

    public BehbaviorCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BehbaviorCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }
}
