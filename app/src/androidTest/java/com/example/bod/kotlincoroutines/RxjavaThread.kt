package com.example.bod.kotlincoroutines

import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import timber.log.Timber

/**
 *
 * @ClassName: RxjavaThread
 * @Description:
 * @CreateDate: 2019/10/20
 */
class RxjavaThread {

    @Test
    fun testRxThread() {
        Observable.just("1")
                .doOnNext {
                    Timber.d("ShowLoading Event() Before onNext")
                }
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread()) //只有第一次有效
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())//每次切换都会产生效果
                .observeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
//                .subscribe {
//                    //简化版本  只有onNext()
//                }
                .subscribe({ str ->
                    sendEvent(UIEvent.ShowLoading("")){
                        Timber.d("ShowLoading Event() invoked")
                    }
                }, { twa ->

                })



    }


    sealed class UIEvent(val name:String) {
        class ShowLoading(name:String) : UIEvent(name)
        class HideLoading(name:String) : UIEvent(name)
    }

    //也可以传递实例子


    sealed class UISealed{
        object WOMAN:UISealed()
        object MAN:UISealed()
    }

    fun sendEvent(event:UIEvent,action:()->Unit){
        action.invoke()
    }


}