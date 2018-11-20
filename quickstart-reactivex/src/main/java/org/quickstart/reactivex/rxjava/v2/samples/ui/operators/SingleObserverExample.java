package org.quickstart.reactivex.rxjava.v2.samples.ui.operators;

import org.quickstart.reactivex.rxjava.v2.samples.utils.AppConstant;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class SingleObserverExample {

    private static final String TAG = SingleObserverExample.class.getSimpleName();
    
    public static void main(String[] args) {
        new SingleObserverExample().doSomeWork();
        for(;;) {}
    }

    /*
     * simple example using SingleObserver
     */
    private void doSomeWork() {
        Single.just("Amit")
                .subscribe(getSingleObserver());
    }

    private SingleObserver<String> getSingleObserver() {
        return new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println( " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onSuccess(String value) {
                System.out.println(" onNext : value : " + value);
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