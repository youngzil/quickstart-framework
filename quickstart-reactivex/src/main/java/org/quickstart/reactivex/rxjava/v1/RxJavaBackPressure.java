package org.quickstart.reactivex.rxjava.v1;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-03 22:24
 */
public class RxJavaBackPressure {

  public static void main(String[] args) throws IOException {

    // 抛出MissingBackpressureException往往就是因为，被观察者发送事件的速度太快，而观察者处理太慢，而且你还没有做相应措施，所以报异常。

    // 这是因为interval操作符本身并不支持背压策略，它并不响应request(n)，也就是说，它发送事件的速度是不受控制的，而range这类操作符是支持背压的，它发送事件的速度可以被控制。

    // 被观察者在主线程中，每1ms发送一个事件
    Observable.interval(1, TimeUnit.MILLISECONDS)
        // 被观察者在新线程中
        // .subscribeOn(Schedulers.newThread())

        // 将观察者的工作放在新线程环境中
        .observeOn(Schedulers.newThread())
        // 观察者处理每1000ms才处理一个事件
        .subscribe(new Action1<Long>() {

          @Override
          public void call(Long aLong) {
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }

            System.out.println("TAG---->" + aLong);
          }
        });

    Observable.interval(1, TimeUnit.MILLISECONDS)//
        .observeOn(Schedulers.newThread())//
        // 这个操作符简单理解就是每隔200ms发送里时间点最近那个事件，
        // 其他的事件浪费掉
        .sample(200, TimeUnit.MILLISECONDS)//
        .subscribe(new Action1<Long>() {
          @Override
          public void call(Long aLong) {
            try {
              Thread.sleep(200);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            System.out.println("TAG---->" + aLong);
          }
        });

    Observable.interval(1, TimeUnit.MILLISECONDS)//
        .observeOn(Schedulers.newThread())//
        // 这个操作符简单理解就是把100毫秒内的事件打包成list发送
        .buffer(100, TimeUnit.MILLISECONDS)//
        .subscribe(new Action1<List>() {
          @Override
          public void call(List aLong) {
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            System.out.println("TAG---->size=" + aLong.size());
          }
        });

    Observable.interval(1, TimeUnit.MILLISECONDS)//
        .onBackpressureDrop()//
        .observeOn(Schedulers.newThread())//
        .subscribe(new Subscriber<Long>() {

          @Override
          public void onStart() {
            System.out.println("onBackpressureDrop onStart");
            // request(1);
          }

          @Override
          public void onCompleted() {
            System.out.println("onBackpressureDrop onCompleted");
          }

          @Override
          public void onError(Throwable e) {
            System.out.println("onBackpressureDrop onError");
            e.printStackTrace();
          }

          @Override
          public void onNext(Long aLong) {
            System.out.println("onBackpressureDrop TAG---->" + aLong);
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });

    System.in.read();

  }

}
