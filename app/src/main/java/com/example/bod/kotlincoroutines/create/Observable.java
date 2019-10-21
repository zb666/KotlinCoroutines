package com.example.bod.kotlincoroutines.create;

/**
 * @ClassName: Observable
 * @Description:
 * @CreateDate: 2019/10/21
 */
public class Observable<T> {

    //new Observable(DelegateSubscriber()).subscrube(Observer())
    //subscribe()传递对象-> delegateSubscriber->
    private NewObserverOnSubscribe<T> newObserverOnSubscribe;

    private Observable(NewObserverOnSubscribe<T> newObserverOnSubscribe) {
        this.newObserverOnSubscribe = newObserverOnSubscribe;
    }

    public static <T> Observable<T> create(NewObserverOnSubscribe<T> newObserverOnSubscribe){
        return new Observable<>(newObserverOnSubscribe);
    }

    public void transObs(Observer<T> tObserver){
        newObserverOnSubscribe.subscribe(tObserver);
    }

}
