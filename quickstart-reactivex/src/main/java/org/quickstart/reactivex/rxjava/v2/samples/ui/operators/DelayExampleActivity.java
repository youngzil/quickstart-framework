package org.quickstart.reactivex.rxjava.v2.samples.ui.operators;

import java.util.concurrent.TimeUnit;

import org.quickstart.reactivex.rxjava.v2.samples.utils.AppConstant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 05/03/17.
 */

public class DelayExampleActivity {

    private static final String TAG = DelayExampleActivity.class.getSimpleName();

    /*
     * simple example using delay to emit after 2 second
     */
    private void doSomeWork() {
        getObservable().delay(2, TimeUnit.SECONDS)
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<String> getObservable() {
        return Observable.just("Amit");
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(" onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                System.out.println(" onNext : value : " + value);
                System.out.println(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(" onError : " + e.getMessage());
                System.out.println(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onComplete() {
                System.out.println(" onComplete");
                System.out.println(AppConstant.LINE_SEPARATOR);
            }
        };
    }


}