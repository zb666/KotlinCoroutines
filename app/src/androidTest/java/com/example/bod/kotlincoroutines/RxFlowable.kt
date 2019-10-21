package com.example.bod.kotlincoroutines

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import org.junit.Test
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import timber.log.Timber

/**
 *
 * @ClassName: RxFlowable
 * @Description:
 * @CreateDate: 2019/10/21
 */
class RxFlowable {

    private var mSub: Subscription? = null

    @Test
    fun userFlowable() {
        Flowable.create(FlowableOnSubscribe<Int> { emt ->
            (1..10).toList().forEach {
                emt.onNext(it)
            }
            emt.onComplete()
        },
                BackpressureStrategy.BUFFER
        )
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Int> {
                    override fun onSubscribe(sub: Subscription?) {
                        mSub = sub
                        sub?.request(50) //之请求
                    }

                    override fun onNext(t: Int?) {
                        Timber.d("Flowable: $t")
                    }

                    override fun onError(t: Throwable?) {
                    }

                    override fun onComplete() {
                        Timber.d("Flowable: completed")
                    }
                })
    }

    fun testSub(){
        //点击一下发送10个
        mSub?.request(10)
    }

}