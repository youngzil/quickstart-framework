package org.quickstart.reactivex.rxjava.v2.samples;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by threshold on 2017/1/12.
 */

public class MyApplication {

    public static void main(String[] args) throws IOException {
        Observable.timer(2, TimeUnit.SECONDS)//
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        System.out.println("sss=" + aLong);
                    }
                });
        
        System.in.read();
    }

}
