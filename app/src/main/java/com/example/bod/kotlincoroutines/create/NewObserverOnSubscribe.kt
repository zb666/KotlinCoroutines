package com.example.bod.kotlincoroutines.create

/**
 * @ClassName: NewObserverOnSubscribe
 * @Description:
 * @CreateDate: 2019/10/21
 */
interface NewObserverOnSubscribe<T>{

    fun subscribe(observer: Observer<T>)

}
