package org.quickstart.reactivex.rxjava.v2.samples.ui.operators;

import java.util.List;

import org.quickstart.reactivex.rxjava.v2.samples.utils.AppConstant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class BufferExample {

    private static final String TAG = BufferExample.class.getSimpleName();
    
    public static void main(String[] args) {
        new BufferExample().doSomeWork();
    }

    /*
     * simple example using buffer operator - bundles all emitted values into a list
     */
    private void doSomeWork() {

        Observable<List<String>> buffered = getObservable().buffer(3, 1);

        // 3 means,  it takes max of three from its start index and create list
        // 1 means, it jumps one step every time
        // so the it gives the following list
        // 1 - one, two, three
        // 2 - two, three, four
        // 3 - three, four, five
        // 4 - four, five
        // 5 - five

        buffered.subscribe(getObserver());
    }

    private Observable<String> getObservable() {
        return Observable.just("one", "two", "three", "four", "five");
    }

    private Observer<List<String>> getObserver() {
        return new Observer<List<String>>() {

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println( " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(List<String> stringList) {
                System.out.println(" onNext size : " + stringList.size());
                System.out.println(AppConstant.LINE_SEPARATOR);
                for (String value : stringList) {
                    System.out.println(" value : " + value);
                    System.out.println(AppConstant.LINE_SEPARATOR);
                }

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