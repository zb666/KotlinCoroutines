package com.example.bod.kotlincoroutines

import com.example.bod.kotlincoroutines.rxjavacreate.Observable
import com.example.bod.kotlincoroutines.rxjavacreate.ObservalEimtter
import com.example.bod.kotlincoroutines.rxjavacreate.Observer
import io.reactivex.*
import io.reactivex.disposables.Disposable

import org.junit.Test
import timber.log.Timber

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {

        testEmitter()

//        Observable.create(ObservableOnSubscribe<Int> { emt ->
//            val intList = (0..100).toList()
//            intList.forEach {
//                if (it == 5) {
//                    emt.onError(IllegalAccessException("参数错误"))
//                }
//                emt.onNext(it)
//            }
//            emt.onComplete()
//        })
//                //上下游之间添加处理异常的操作符号
////              .onErrorReturn {
//                //返回的是普通的数据类型
////                  if (it is IllegalStateException) {
////                      400
////                  }else {
////                      300
////                  }
////              }
//                //错误拦截不到，整个程序还是会崩溃
////              .onErrorResumeNext(
////                      //命名加了resume,可以返回被观察者
////                      ObservableSource {
////                  it.onNext(404)
////              })
//                .onExceptionResumeNext {
//                    it.onNext(404) //能够拦截到错误  但是需要慎重使用，确保该错误是可以被忽略的
//                }
//                .subscribe(object : Observable<Int> {
//                    override fun onComplete() {
//                        Timber.d("OnErrorReturn: completed()")
//                    }
//
//                    override fun onSubscribe(d: Disposable) {
//                    }
//
//                    override fun onNext(t: Int) {
//                        Timber.d("OnErrorReturn: onNext $t")
//                    }
//
//                    override fun onError(e: Throwable) {
//                        Timber.d("OnErrorReturn: onError $e")
//                    }
//                })


    }

    fun testEmitter() {
        //静态方法->创建一个新的具有发送数据能力的Observer
        //->构造方法里面初始化发射器 ->保存在成员变量中

        com.example.bod.kotlincoroutines.rxjavacreate.Observable. create(
                com.example.bod.kotlincoroutines.rxjavacreate.ObservableOnSubscribe<String> {
                    emitterObserver ->
                    //Observable->observalEimtter->onNext()
                    emitterObserver?.onNext("发送数据")
                })
                //.map
                .subscribe(object : com.example.bod.kotlincoroutines.rxjavacreate.Observer<String> {

                    override fun onSubscribe() {
                    }

                    override fun onNext(result: String?) {
                        Timber.d("发送Success :$result")
                    }

                    override fun onError(throwable: Throwable?) {
                    }

                    override fun onComplete() {
                    }

                })


        Observable.just("just:111","just:222")
                .subscribe(object :Observer<String>{
                    override fun onSubscribe() {

                    }

                    override fun onNext(result: String?) {
                        Timber.d("onNext:$result")
                    }

                    override fun onError(throwable: Throwable?) {
                    }

                    override fun onComplete() {
                    }
                })

    }

}
