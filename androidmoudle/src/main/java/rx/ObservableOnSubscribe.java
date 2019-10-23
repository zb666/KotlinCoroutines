package rx;

/**
 * @ClassName: ObservableOnSubscribe
 * @Description:
 * @CreateDate: 2019/10/23
 */
public interface ObservableOnSubscribe<T> {

    void subscribe(Observer<? super T> observerEmitter);

}
