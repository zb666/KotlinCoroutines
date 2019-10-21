package com.example.bod.kotlincoroutines.rxjavacreate;

/**
 * @ClassName: Observable
 * @Description:
 * @CreateDate: 2019/10/21
 */
public class Observable<T> {

    //new->构造->调用发射的方法
    ObservableOnSubscribe<T> observalEimtter;

    private Observable(ObservableOnSubscribe<T> observalEimtter) {
        this.observalEimtter = observalEimtter;
    }

    //同样被构造出来的还有Emitter发射器
    public static <T> Observable<T> create(ObservableOnSubscribe<T> observalEimtter) {
        return new Observable<>(observalEimtter); //可以被订阅了 那如何发送数据呢
    }

    public void subscribe(Observer<T> tObserver){
        //发送数据化
       observalEimtter.subscribe(tObserver);
    }

}
