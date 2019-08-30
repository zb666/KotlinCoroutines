package com.example.bod.kotlincoroutines.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ClassName: MyBitmapView
 * @Description:
 * @CreateDate: 2019/8/30
 */
public class MyBitmapView extends View {
    public MyBitmapView(Context context) {
        super(context);
    }

    public MyBitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyBitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Canvas mCanvas;

    private Rect mRect;

    public void setCanvas(Canvas canvas) {
        mCanvas = canvas;
        mRect = new Rect(50, 50, 150, 150);
        invalidate();
    }

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCanvas != null) {
            mCanvas.drawRect(mRect, mPaint);
        }
    }
}
