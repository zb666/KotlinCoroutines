package com.example.bod.kotlincoroutines.rxjavacreate;

/**
 * @ClassName: Observable
 * @Description:
 * @CreateDate: 2019/10/21
 */
public class Observable<T> {

    //new->构造->调用发射的方法
    ObservableOnSubscribe<T> source;

    private Observable(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    //同样被构造出来的还有Emitter发射器
    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return new Observable<>(source); //可以被订阅了 那如何发送数据呢
    }

    public static <T> Observable<T> just(final T t1,final T t2) {
        //让source不为空
        //这里由自己我们帮助new
        return new Observable<>(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(Observer<? super T> emitterObserver) {
                emitterObserver.onNext(t1);
                emitterObserver.onNext(t2);
            }
        });
    }

    public void subscribe(Observer<T> tObserver) {
        tObserver.onSubscribe();
        source.subscribe(tObserver);
    }

    /**
     * map变换操作符号
     * T 上一层的类型
     * R 下一层的类型 (Return)
     * @return
     */
    public <R> Observable<R> map(Function<? super T,? extends R> function){
        ObservableMap<T,R> observableMap = new ObservableMap<>(source,function);
        return new Observable<>(observableMap);
    }


}
