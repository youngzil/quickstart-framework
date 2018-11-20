package org.quickstart.reactivex.rxjava.example;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class RxFilterTest {

    private void start() {
        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Observable.from(integers)//
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 2 != 0;
                    }
                }).subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer.toString() + ",");
                    }
                });
    }
}
