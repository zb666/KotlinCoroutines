package rx;

/**
 * @ClassName: Observer
 * @Description:
 * @CreateDate: 2019/10/23
 */
public interface Observer<T> {

    void onNext(T result);

    void onComplete();

    void onError();

    void onSubscribe();

}
