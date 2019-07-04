/**
 * 项目名称：quickstart-reactivex 文件名：ReactivePullTest.java 版本信息： 日期：2018年11月19日 Copyright yangzl Corporation 2018 版权所有 *
 */
package org.quickstart.reactivex.rxjava.v1;

import java.io.IOException;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * ReactivePullTest
 * 
 * @author：youngzil@163.com
 * @2018年11月19日 下午2:26:35
 * @since 1.0
 */
public class ReactivePullTest {

  public static void main(String[] args) throws IOException {


    // 这是因为interval操作符本身并不支持背压策略，它并不响应request(n)，也就是说，它发送事件的速度是不受控制的，而range这类操作符是支持背压的，它发送事件的速度可以被控制。

    // 被观察者将产生100000个事件
    Observable observable = Observable.range(1, 100000);

    class MySubscriber extends Subscriber<Integer> {
      @Override
      public void onStart() {

        System.out.println("onStart");
        // 一定要在onStart中通知被观察者先发送一个事件
        request(1);
      }

      @Override
      public void onCompleted() {
        System.out.println("onCompleted");
        // ...
      }

      @Override
      public void onError(Throwable e) {

        System.out.println("onError");
        e.printStackTrace();
        // ...
      }

      @Override
      public void onNext(Integer n) {

        System.out.println("n=" + n);
        // ...
        // ...
        // 处理完毕之后，在通知被观察者发送下一个事件
        request(1);

        // 如果你想取消这种backpressure 策略，调用quest(Long.MAX_VALUE)即可。


      }
    }

    // Subscriber ss = new MySubscriber();
    // observable.subscribe(ss);

    // 在不指定线程的情况下， RxJava 遵循的是线程不变的原则，即：在哪个线程调用 subscribe()，就在哪个线程生产事件；在哪个线程生产事件，就在哪个线程消费事件。如果需要切换线程，就需要用到 Scheduler （调度器）。

    observable.observeOn(Schedulers.newThread()).subscribe(new MySubscriber());
    System.in.read();

  }

}
