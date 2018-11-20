package org.quickstart.reactivex.rxjava.v2.samples.ui.operators;

import java.util.concurrent.TimeUnit;

import org.quickstart.reactivex.rxjava.v2.samples.utils.AppConstant;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 22/12/16.
 */

public class DebounceExample  {

    private static final String TAG = DebounceExample.class.getSimpleName();

    /*
    * Using debounce() -> only emit an item from an Observable if a particular time-span has
    * passed without it emitting another item, so it will emit 2, 4, 5 as we have simulated it.
    */
    private void doSomeWork() {
        getObservable()
                .debounce(500, TimeUnit.MILLISECONDS)
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<Integer> getObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // send events with simulated time wait
                emitter.onNext(1); // skip
                Thread.sleep(400);
                emitter.onNext(2); // deliver
                Thread.sleep(505);
                emitter.onNext(3); // skip
                Thread.sleep(100);
                emitter.onNext(4); // deliver
                Thread.sleep(605);
                emitter.onNext(5); // deliver
                Thread.sleep(510);
                emitter.onComplete();
            }
        });
    }

    private Observer<Integer> getObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println( " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                System.out.println( " onNext ");
                System.out.println( " value : " + value);
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