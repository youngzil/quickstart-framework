package org.quickstart.reactivex.rxjava.example;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class RxTakeTest {

    public static void main(String[] args) {
        Integer[] number = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Observable.from(number).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer % 2 != 0;
            }
        })
                // 取前四个
                .take(4)
                // 取前四个中的后两个
                .takeLast(2).doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("before onNext（）\n");
                    }
                }).subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("onNext()--->" + integer + "\n");
                    }
                });
    }

}
