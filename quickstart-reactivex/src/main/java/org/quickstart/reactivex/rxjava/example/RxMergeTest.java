package org.quickstart.reactivex.rxjava.example;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class RxMergeTest {

    public static void main(String[] args) {

        Observable obs1 = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(500);
                    subscriber.onNext(" aaa");
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.newThread());

        Observable obs2 = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(1500);
                    subscriber.onNext("bbb");
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.newThread());

        Observable.merge(obs1, obs2)
                // .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    StringBuffer sb = new StringBuffer();

                    @Override
                    public void onCompleted() {
                        System.out.println("两个任务都处理完毕！！\n");
                        System.out.println("更新数据：" + sb + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {

                }

                    @Override
                    public void onNext(String s) {
                        sb.append(s + ",");
                        System.out.println("得到一个数据：" + s + "\n");
                    }
                });
    }
}
