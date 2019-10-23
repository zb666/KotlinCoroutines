package rx;

import android.util.Log;

import java.util.Timer;

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
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onNext(Integer result) {
                String a = new String("111");
                boolean resultAAA = (a.equals("111"));
                System.out.println("MapResult:" + result+" 常亮池: "+resultAAA);
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
