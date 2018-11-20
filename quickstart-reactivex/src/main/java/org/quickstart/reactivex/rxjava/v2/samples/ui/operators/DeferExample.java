package org.quickstart.reactivex.rxjava.v2.samples.ui.operators;

import org.quickstart.reactivex.rxjava.v2.samples.model.Car;
import org.quickstart.reactivex.rxjava.v2.samples.utils.AppConstant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by amitshekhar on 30/08/16.
 */
public class DeferExample  {

    private static final String TAG = DeferExample.class.getSimpleName();


    /*
     * Defer used for Deferring Observable code until subscription in RxJava
     */
    private void doSomeWork() {

        Car car = new Car();

        Observable<String> brandDeferObservable = car.brandDeferObservable();

        car.setBrand("BMW");  // Even if we are setting the brand after creating Observable
        // we will get the brand as BMW.
        // If we had not used defer, we would have got null as the brand.

        brandDeferObservable
                .subscribe(getObserver());
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