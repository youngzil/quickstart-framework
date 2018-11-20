package org.quickstart.reactivex.rxjava.v2.samples.ui.operators;

import org.quickstart.reactivex.rxjava.v2.samples.utils.AppConstant;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by amitshekhar on 17/12/16.
 */

public class PublishSubjectExample extends AbstractObserver{

    private static final String TAG = PublishSubjectExample.class.getSimpleName();

    /* PublishSubject emits to an observer only those items that are emitted
     * by the source Observable, subsequent to the time of the subscription.
     */
    private void doSomeWork() {

        PublishSubject<Integer> source = PublishSubject.create();

        source.subscribe(getFirstObserver()); // it will get 1, 2, 3, 4 and onComplete

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        /*
         * it will emit 4 and onComplete for second observer also.
         */
        source.subscribe(getSecondObserver());

        source.onNext(4);
        source.onComplete();

    }

}