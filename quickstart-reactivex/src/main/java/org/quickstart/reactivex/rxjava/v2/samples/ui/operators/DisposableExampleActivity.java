package org.quickstart.reactivex.rxjava.v2.samples.ui.operators;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.quickstart.reactivex.rxjava.v2.samples.utils.AppConstant;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class DisposableExampleActivity  {

    private static final String TAG = DisposableExampleActivity.class.getSimpleName();
    private final CompositeDisposable disposables = new CompositeDisposable();

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        disposables.clear(); // do not send event after activity has been destroyed
//    }

    /*
     * Example to understand how to use disposables.
     * disposables is cleared in onDestroy of this activity.
     */
    void doSomeWork() {
        disposables.add(sampleObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
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

                    @Override
                    public void onNext(String value) {
                        System.out.println(" onNext : value : " + value);
                        System.out.println(AppConstant.LINE_SEPARATOR);
                    }
                }));
    }

    static Observable<String> sampleObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() {
                // Do some long running operation
                try {
                    TimeUnit.SECONDS.sleep(2000L);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }
}
