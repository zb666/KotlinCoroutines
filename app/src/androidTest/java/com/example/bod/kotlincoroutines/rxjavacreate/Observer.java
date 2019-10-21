package com.example.bod.kotlincoroutines.rxjavacreate;

/**
 * @ClassName: Observable
 * @Description:
 * @CreateDate: 2019/10/21
 */
public interface Observer<T> {

    void onSubscribe();

    void onNext(T result);

    void onError(Throwable throwable);

    void onComplete();
}
