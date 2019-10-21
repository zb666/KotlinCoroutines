package com.example.bod.kotlincoroutines.rxjavacreate

/**
 *
 * @ClassName: ObservalEimtter
 * @Description:
 * @CreateDate: 2019/10/21
 */
interface ObservalEimtter<T> {

    fun onNextt(result:T)

    fun onError()

}