package rx;

import rx.thread.ObservableOnIo;
import rx.thread.ObservableOnMain;

/**
 * @ClassName: Observable
 * @Description:
 * @CreateDate: 2019/10/23
 */
public class Observable<T> {

    //给与外界决定消费的能力
    private ObservableOnSubscribe<T> observableOnSubscribe;

    private Observable(ObservableOnSubscribe<T> observableOnSubscribe) {
        this.observableOnSubscribe = observableOnSubscribe;
    }

    //接口回调
    public static <T> Observable<T> create(ObservableOnSubscribe<T> observableOnSubscribe) {
        return new Observable<>(observableOnSubscribe);
    }

    public static <T> Observable<T> just(final T result) {
        return new Observable<>(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(Observer<? super T> observer) {
                observer.onNext(result);
            }
        });
    }

    public <R> Observable<R> map(final Function<? super T, ? extends R> function) {
        //source T 类型的Observable apply T->R (T是输入类型，R是输出类型)
        //控制下游的能力ObservableMap->MapObserver<T>->subscribe的时候最终调用的是MapObserver中的OnNext方法
        ObservableMap<T, R> observableMap = new ObservableMap<>(observableOnSubscribe, function);
        return new Observable<>(observableMap);
    }
    //多个just同理

    public void subscribe(Observer<T> observer) {
        observableOnSubscribe.subscribe(observer);
    }

    public Observable<T> observableOn() {
        return create(new ObservableOnIo<T>(observableOnSubscribe));
    }

    public Observable<T> subscribeOn(){
        return create(new ObservableOnMain<T>(observableOnSubscribe));
    }

}
