package org.quickstart.reactivex.rxjava.v2.samples.ui.operators;

import io.reactivex.subjects.AsyncSubject;

/**
 * Created by amitshekhar on 17/12/16.
 */

public class AsyncSubjectExample extends AbstractObserver{

    private static final String TAG = AsyncSubjectExample.class.getSimpleName();

    /* An AsyncSubject emits the last value (and only the last value) emitted by the source
     * Observable, and only after that source Observable completes. (If the source Observable
     * does not emit any values, the AsyncSubject also completes without emitting any values.)
     */
    private void doSomeWork() {

        AsyncSubject<Integer> source = AsyncSubject.create();

        source.subscribe(getFirstObserver()); // it will emit only 4 and onComplete

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