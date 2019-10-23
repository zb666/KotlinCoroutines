package rx;

import android.util.Log;

import java.util.Timer;

import rx.thread.ObservableOnIo;

/**
 * @ClassName: TestRx
 * @Description:
 * @CreateDate: 2019/10/23
 */
public class TestRx {

    public static void main(String args[]) {
        Observable.just("1111")
                .map(new Function<String, Integer>() {

                    @Override
                    public Integer apply(String result) {
                        return result.hashCode();
                    }
                })
                .observableOn()
                .subscribeOn()
                .subscribe(new Observer<Integer>() {
                    //订阅的时候最终 ObservableOnIo->subscribe 中的线程池中的Run方法中的代码
                    @Override
                    public void onNext(Integer result) {
                        String a = new String("111");
                        boolean resultAAA = (a.equals("111"));
                        System.out.println("MapResult:" + Thread.currentThread().getName() + result + " 常量池: " + resultAAA);
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
                });
    }


}
