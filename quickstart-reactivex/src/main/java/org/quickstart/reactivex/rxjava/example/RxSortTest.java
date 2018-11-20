package org.quickstart.reactivex.rxjava.example;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class RxSortTest {

    public static void main(String[] args) {
        Integer[] words = {1, 3, 5, 2, 34, 7, 5, 86, 23, 43};
        //
        Observable.from(words)//
                .toSortedList()//
                .flatMap(new Func1<List<Integer>, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(List<Integer> strings) {
                        return Observable.from(strings);
                    }
                }).subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer strings) {
                        System.out.println(strings + "\n");
                    }
                });
    }

}
