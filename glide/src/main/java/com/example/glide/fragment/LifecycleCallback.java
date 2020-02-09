package com.example.glide.fragment;

/**
 * @ClassName: LifecycleCallback
 * @Description:
 * @CreateDate: 2020/2/9
 */
public interface LifecycleCallback {
    //生命周期初始化
    void glideInitAction();
    //停止
    void glideStopAction();
    //生命周期释放
    void glideRecycleAction();
}
