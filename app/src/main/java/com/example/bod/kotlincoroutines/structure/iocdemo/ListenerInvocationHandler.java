package com.example.bod.kotlincoroutines.structure.iocdemo;

import android.app.Activity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName: ListenerInvocationHandler
 * @Description:
 * @CreateDate: 2020/2/20
 */
public class ListenerInvocationHandler implements InvocationHandler {

    //需要再onClick中执行activity.click()

    private Object mActivity;
    private Method activityMethod;

    public ListenerInvocationHandler(Object mActivity, Method activityMethod) {
        this.mActivity = mActivity;
        this.activityMethod = activityMethod;
    }

    /** onClick()->onBobClicked(View view)
     * @param proxy
     * @param method
     * @param args
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return activityMethod.invoke(mActivity,args);
    }
}
