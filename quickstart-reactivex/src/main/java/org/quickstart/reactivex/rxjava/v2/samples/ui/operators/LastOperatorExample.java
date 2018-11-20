package org.quickstart.reactivex.rxjava.v2.samples.ui.operators;

import java.util.List;

import org.quickstart.reactivex.rxjava.v2.samples.utils.AppConstant;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by techteam on 13/09/16.
 */
public class LastOperatorExample {

    private static final String TAG = DistinctExampleActivity.class.getSimpleName();
    
    public static void main(String[] args) {
        new LastOperatorExample().doSomeWork();
    }

    /*
    * last() emits only the last item emitted by the Observable.
    */
    private void doSomeWork() {
        getObservable().last("A1") // the default item ("A1") to emit if the source ObservableSource is empty
                .subscribe(getObserver());
    }

    private Observable<String> getObservable() {
        return Observable.just("A1", "A2", "A3", "A4", "A5", "A6");
    }

    private SingleObserver<String> getObserver() {
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
