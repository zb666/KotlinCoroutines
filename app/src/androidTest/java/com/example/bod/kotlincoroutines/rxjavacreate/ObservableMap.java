package com.example.bod.kotlincoroutines.rxjavacreate;

import com.example.bod.kotlincoroutines.R;

/**
 * @ClassName: ObservableMap
 * @Description:
 * @CreateDate: 2019/10/23
 */

/**
 * 产生新的R类型的Observable数据
 * ObservableOnScribe() -> 订阅的时候产生新的Observable()
 *
 * @param <T> source
 * @param <R> newSource
 */
public class ObservableMap<T, R> implements ObservableOnSubscribe<R> {

    private ObservableOnSubscribe<T> sourceOrigin;

    private Observer<? super R> observableEmitter;

    private Function<? super T, ? extends R> sourceFunction;

    public ObservableMap(
            ObservableOnSubscribe<T> source,  //数据源
            //Observer<? super R> observableEmit,//下一层剧透发送R类型数据的能管理,并且他还是一个生产者
            Function<? super T, ? extends R> function) {
//        this.observableEmitter = observableEmit;
        sourceOrigin = source;
        sourceFunction = function;
    }

    @Override
    public void subscribe(Observer<? super R> sourceObserverEmitter) {
        observableEmitter = sourceObserverEmitter;
        //Onserver<T> -> Map Observer-> T apply R->Observer<R>
        //onNext实际调用的是MapObserver中的onNext()方法
        MapObserver<T> tMapObserver = new MapObserver<>(sourceOrigin, observableEmitter, sourceFunction);
        sourceOrigin.subscribe(tMapObserver);
    }

    //新的Observable具有以下的能力
    //上游的数据  变化  能继续发射数据
    class MapObserver<T> implements Observer<T> {

        private ObservableOnSubscribe<T> sourceOrigin;

        private Observer<? super R> observableEmitter;

        private Function<? super T, ? extends R> sourceFunction;

        public MapObserver(ObservableOnSubscribe<T> sourceOrigin,
                           Observer<? super R> observableEmitter,
                           Function<? super T, ? extends R> sourceFunction) {
            this.sourceOrigin = sourceOrigin;
            this.observableEmitter = observableEmitter;
            this.sourceFunction = sourceFunction;
        }

        @Override
        public void onSubscribe() {
            observableEmitter.onSubscribe();
        }

        @Override
        public void onNext(T result) {
            //变化
            R resultR = sourceFunction.apply(result);
            //发射
            observableEmitter.onNext(resultR);
        }

        @Override
        public void onError(Throwable throwable) {
            observableEmitter.onError(throwable);
        }

        @Override
        public void onComplete() {
           observableEmitter.onComplete();
        }
    }
}
