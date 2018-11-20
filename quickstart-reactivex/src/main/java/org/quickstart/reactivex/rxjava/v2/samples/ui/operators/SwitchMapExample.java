package org.quickstart.reactivex.rxjava.v2.samples.ui.operators;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.quickstart.reactivex.rxjava.v2.samples.utils.AppConstant;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by thanhtuan on 26/04/18.
 */
public class SwitchMapExample {

    private static final String TAG = SwitchMapExample.class.getSimpleName();

    /* whenever a new item is emitted by the source Observable, it will unsubscribe to and stop
     * mirroring the Observable that was generated from the previously-emitted item,
     * and begin only mirroring the current one.
     *
     * Result: 5x
     */
    private void doSomeWork() {
        getObservable()
                .switchMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) {
                        int delay = new Random().nextInt(2);

                        return Observable.just(integer.toString() + "x")
                                .delay(delay, TimeUnit.SECONDS, Schedulers.io());
                    }
                })
                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<Integer> getObservable() {
        return Observable.just(1, 2, 3, 4, 5);
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println( " onSubscribe : " + d.isDisposed());
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
