package rx.thread;

import android.os.Handler;
import android.os.Looper;

import com.example.androidmoudle.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Function;
import rx.ObservableOnSubscribe;
import rx.Observer;

/**
 * @ClassName: ObservableOnMain
 * @Description:
 * @CreateDate: 2019/10/23
 */
public class ObservableOnMain<T> implements ObservableOnSubscribe<T> {

    private ObservableOnSubscribe<T> source;

    private final static ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor();

    public ObservableOnMain(ObservableOnSubscribe<T> observableOnSubscribe) {
        this.source = observableOnSubscribe;
    }

    @Override
    public void subscribe(final Observer<? super T> observerEmitter) {
        //同时这里由于实现了ObservableOnSubscribe接口,传递新的Emitter对象的方法
        PackageObservable<T> packageObservable = new PackageObservable<>(source, (Observer<T>) observerEmitter);
        //又包装了一层
        source.subscribe(packageObservable);
    }

    class PackageObservable<T> implements Observer<T> {

        //拿到上一层
        private ObservableOnSubscribe<T> preSource;

        //拥有下一层的能力
        private Observer<T> observer;

        public PackageObservable(ObservableOnSubscribe<T> preSource, Observer<T> observer) {
            this.preSource = preSource;
            this.observer = observer;
        }
        //拥有下一层的能力

        @Override
        public void onNext(final T result) {
//            sHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                  //->Android主线程
//                    observer.onNext(result);
//                }
//            });
            Thread main = new Thread(new Runnable() {
                @Override
                public void run() {
                    observer.onNext(result);
                }
            },"Main");
            main.start();
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
//                    observer.onNext(result);
                }
            });
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
