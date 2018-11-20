package org.quickstart.reactivex.rxjava.v2.samples.ui.operators;

import java.util.concurrent.TimeUnit;

import org.quickstart.reactivex.rxjava.v2.samples.utils.AppConstant;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class CompletableObserverExample {

    private static final String TAG = CompletableObserverExample.class.getSimpleName();

    /*
     * simple example using CompletableObserver
     */
    private void doSomeWork() {
        Completable completable = Completable.timer(1000, TimeUnit.MILLISECONDS);

        completable
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getCompletableObserver());
    }

    private CompletableObserver getCompletableObserver() {
        return new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(" onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onComplete() {
                System.out.println(" onComplete");
                System.out.println(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(" onError : " + e.getMessage());
                System.out.println(AppConstant.LINE_SEPARATOR);
            }
        };
    }

}