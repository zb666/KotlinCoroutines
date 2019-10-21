package com.example.bod.kotlincoroutines.create;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

/**
 * @ClassName: TestCreate
 * @Description:
 * @CreateDate: 2019/10/21
 */
public class TestCreate {

    void main(){
       Observable.create(new NewObserverOnSubscribe<String>() {
           @Override
           public void subscribe(@NotNull Observer<String> observer) {
               observer.onNext("123456");
           }
       }).delegateSubscribe(new Observer<String>() {
           @Override
           public void onNext(String s) {
               Timber.d("OnNext:"+s);
           }

           @Override
           public void onError() {

           }

           @Override
           public void onCompleted() {

           }
       });
    }
}
