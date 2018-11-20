package org.quickstart.reactivex.rxjava.v2.samples.ui.compose;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by werockstar on 5/19/2017.
 */

public class RxSchedulers {

    public <T> ObservableTransformer<T, T> applyObservableAsync() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.single());
//                .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public <T> ObservableTransformer<T, T> applyObservableCompute() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.computation())
                        .observeOn(Schedulers.single());
//                .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public <T> ObservableTransformer<T, T> applyObservableMainThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.observeOn(Schedulers.single());
//                return observable.observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public <T> FlowableTransformer<T, T> applyFlowableMainThread() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> flowable) {
                return flowable.observeOn(Schedulers.single());
//                return flowable.observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public <T> FlowableTransformer<T, T> applyFlowableAsysnc() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> flowable) {
                return flowable.observeOn(Schedulers.single());
//                return flowable.observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public <T> FlowableTransformer<T, T> applyFlowableCompute() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> flowable) {
                return flowable.observeOn(Schedulers.single());
//                return flowable.observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
