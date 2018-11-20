package org.quickstart.reactivex.rxjava.example;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Timestamped;

public class TimestampTest {

    public static void main(String[] args) throws IOException {

        Integer[] words = {1, 3, 5, 2, 34, 7, 5, 86, 23, 43};
        //
        // Observable.from(words)
        // .toSortedList()
        // .flatMap(new Func1<List<Integer>, Observable<Integer>>() {
        // @Override
        // public Observable<Integer> call(List<Integer> strings) {
        // return Observable.from(strings);
        // }
        // })
        // .subscribe(new Action1<Integer>() {
        // @Override
        // public void call(Integer strings) {
        // mText.append(strings+"\n");
        // }
        // });

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        Observable.from(words).timestamp()
                // .timestamp(Schedulers.io()) 可指定线程环境，如果指定到子线程，请在最后切换成主线程
                .subscribe(new Action1<Timestamped<Integer>>() {
                    @Override
                    public void call(Timestamped<Integer> integerTimestamped) {

                        System.out.println("value: " + integerTimestamped.getValue() + "       time:   " + sdf.format(new Date(integerTimestamped.getTimestampMillis())) + "\n");

                    }
                });
        
    }

}
