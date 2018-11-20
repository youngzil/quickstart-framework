package org.quickstart.reactivex.rxjava.example;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RxTimerTest {

    public static void main(String[] args) {
        // interval（）是运行在computation Scheduler线程中的，因此需要转到主线程
        Subscription mSubscription = Observable.interval(1, TimeUnit.SECONDS)
                // .observeOn(Schedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("----" + aLong);
                    }
                });
        
        for(;;) {}
        
    }
}
