package com.example.bod.kotlincoroutines;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.provider.CalendarContract;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ClassName: LoadView
 * @Description:
 * @CreateDate: 2019/8/29
 */
public class LoadView extends View {
    public LoadView(Context context) {
        super(context);
    }

    public LoadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Rect mRect = new Rect(10,10,210,210);

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mRect,mPaint);
    }
}
