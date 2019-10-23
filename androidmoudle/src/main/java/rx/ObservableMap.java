package rx;

/**
 * @ClassName: ObservableMap
 * @Description:
 * @CreateDate: 2019/10/23
 */

//变化的时候需要用 super 和 extends
public class ObservableMap<T, R> implements ObservableOnSubscribe<R> {

    //source Obs && Function
    private ObservableOnSubscribe<T> source;

    private Function<? super T, ? extends R> function;

    private Observer<? super R> observerEmitter;

    public ObservableMap(
            ObservableOnSubscribe<T> observable,
            Function<? super T, ? extends R> function) {
        this.source = observable;
        this.function = function;
    }

    @Override
    public void subscribe(Observer<? super R> observerEmitter) {
        //订阅时生成新的发射R类型的数据
        this.observerEmitter = observerEmitter;
        MapObserver<T> observerMap = new MapObserver<>(observerEmitter, source, function);
        source.subscribe(observerMap);
    }

    //因为source需要传递Observer的实现类
    class MapObserver<T> implements Observer<T> {

        //控制下一层
        private Observer<? super R> observerEmitter;

        //上一层
        private ObservableOnSubscribe<? super T> source;

        //转化数据类型个
        private Function<? super T, ? extends R> function;

        private MapObserver(Observer<? super R> observerEmitter,
                            ObservableOnSubscribe<? super T> source,
                            Function<? super T, ? extends R> function) {
            this.observerEmitter = observerEmitter;
            this.source = source;
            this.function = function;
        }

        @Override
        public void onNext(T result) {
            //onNext交由外界
            //ObservableMap->MapObserver<T> -> OnNext()输出数据
            R mapResultR = function.apply(result);
            observerEmitter.onNext(mapResultR);
        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onError() {

        }

        @Override
        public void onSubscribe() {

        }
    }
}
