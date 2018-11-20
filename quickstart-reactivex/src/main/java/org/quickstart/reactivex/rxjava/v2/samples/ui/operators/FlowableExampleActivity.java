package org.quickstart.reactivex.rxjava.v2.samples.ui.operators;

import org.quickstart.reactivex.rxjava.v2.samples.utils.AppConstant;

import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class FlowableExampleActivity  {

    private static final String TAG = FlowableExampleActivity.class.getSimpleName();

    /*
     * simple example using Flowable
     */
    private void doSomeWork() {

        Flowable<Integer> observable = Flowable.just(1, 2, 3, 4);

        observable.reduce(50, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2) {
                return t1 + t2;
            }
        }).subscribe(getObserver());

    }

    private SingleObserver<Integer> getObserver() {

        return new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(" onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onSuccess(Integer value) {
                System.out.println(" onSuccess : value : " + value);
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