package org.quickstart.reactivex.rxjava.v2.samples.ui.operators;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by amitshekhar on 17/12/16.
 */

public class BehaviorSubjectExample extends AbstractObserver {

    private static final String TAG = BehaviorSubjectExample.class.getSimpleName();

    /* When an observer subscribes to a BehaviorSubject, it begins by emitting the item most
     * recently emitted by the source Observable (or a seed/default value if none has yet been
     * emitted) and then continues to emit any other items emitted later by the source Observable(s).
     * It is different from Async Subject as async emits the last value (and only the last value)
     * but the Behavior Subject emits the last and the subsequent values also.
     */
    private void doSomeWork() {

        BehaviorSubject<Integer> source = BehaviorSubject.create();

        source.subscribe(getFirstObserver()); // it will get 1, 2, 3, 4 and onComplete

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        /*
         * it will emit 3(last emitted), 4 and onComplete for second observer also.
         */
        source.subscribe(getSecondObserver());

        source.onNext(4);
        source.onComplete();

    }

}
