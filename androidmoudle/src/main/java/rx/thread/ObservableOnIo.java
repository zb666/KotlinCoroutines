package rx.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.ObservableOnSubscribe;
import rx.Observer;

/**
 * @ClassName: ObservableOnIO
 * @Description:
 * @CreateDate: 2019/10/23
 */
public class ObservableOnIo<T> implements ObservableOnSubscribe<T>{

    private final static ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor();

    private ObservableOnSubscribe<T> source;

    public ObservableOnIo(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override
    public void subscribe(final Observer<? super T> observerEmitter) {
        EXECUTOR_SERVICE.execute(new Runnable() {
            @Override
            public void run() {
                source.subscribe(observerEmitter);
            }
        });

        //这样写是主线程
//        source.subscribe(observerEmitter);
    }
}
