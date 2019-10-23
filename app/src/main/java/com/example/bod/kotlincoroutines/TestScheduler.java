package com.example.bod.kotlincoroutines;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

/**
 * @ClassName: TestScheduler
 * @Description:
 * @CreateDate: 2019/10/23
 */
public class TestScheduler extends Scheduler {
    @Override
    public Worker createWorker() {
        return new Worker() {
            @Override
            public Disposable schedule(Runnable run, long delay, TimeUnit unit) {
                return null;
            }

            @Override
            public void dispose() {

            }

            @Override
            public boolean isDisposed() {
                return false;
            }
        };
    }
}
