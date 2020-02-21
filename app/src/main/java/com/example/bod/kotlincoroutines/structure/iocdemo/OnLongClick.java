package com.example.bod.kotlincoroutines.structure.iocdemo;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: OnLongClick
 * @Description:
 * @CreateDate: 2020/2/21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)//java-->class-->runtime
@EventBase(listenerSetter = "setOnLongClickListener"
        ,listenerType = View.OnLongClickListener.class
        ,callbackMethod = "onLongClick")
public @interface OnLongClick {
    int[] value() default -1;
}

