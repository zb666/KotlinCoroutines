package com.example.bod.kotlincoroutines.rxjavacreate;

/**
 * @ClassName: ObservableOnSubscribe
 * @Description:
 * @CreateDate: 2019/10/21
 */
public interface ObservableOnSubscribe<T> {

    //这里是真正的下游
    void subscribe(Observer<T> emitterObserver);

}
