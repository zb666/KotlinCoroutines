package com.example.bod.kotlincoroutines.create

/**
 *
 * @ClassName: Observer
 * @Description:
 * @CreateDate: 2019/10/21
 */
interface Observer<T> {

    fun onNext(t:T)

    fun onError()

    fun onCompleted()

}