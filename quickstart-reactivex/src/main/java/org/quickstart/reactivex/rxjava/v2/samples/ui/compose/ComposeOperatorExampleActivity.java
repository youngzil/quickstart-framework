package org.quickstart.reactivex.rxjava.v2.samples.ui.compose;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class ComposeOperatorExampleActivity {

    private static RxSchedulers schedulers = new RxSchedulers();

    public static void main(String[] args) {

        /*
        Compose for reusable code.
        */
        Observable.just(1, 2, 3, 4, 5)//
                .compose(schedulers.<Integer>applyObservableAsync())//
                .subscribe(/* */);

        Flowable.just(1, 2, 3, 4, 5)//
                .compose(schedulers.<Integer>applyFlowableAsysnc())//
                .subscribe(/* */);

    }
}
