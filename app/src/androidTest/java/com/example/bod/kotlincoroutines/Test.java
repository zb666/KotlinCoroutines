package com.example.bod.kotlincoroutines;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @ClassName: Test
 * @Description:
 * @CreateDate: 2019/10/21
 */
public class Test {
    void main() {

        //构建新的Observable发射数据

        com.example.bod.kotlincoroutines.rxjavacreate.Observable.create(new com.example.bod.kotlincoroutines.rxjavacreate.ObservableOnSubscribe<String>()  {
            @Override
            public void subscribe(com.example.bod.kotlincoroutines.rxjavacreate.Observer<? super String> emitterObserver) {
                emitterObserver.onNext("111");
            }
        }).map(new com.example.bod.kotlincoroutines.rxjavacreate.Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return null;
            }
        }).subscribe(new com.example.bod.kotlincoroutines.rxjavacreate.Observer<Integer>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(Integer result) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("");
                emitter.onComplete();
                //就成
            }
        })
                .map(new Function<String, Integer>() { //这层的变化是如何实现的
                    @Override
                    public Integer apply(String s) throws Exception {
                        return s.hashCode();
                    }
                })
                .map(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(Integer integer) throws Exception {
                        return integer == 1;
                    }
                }).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
