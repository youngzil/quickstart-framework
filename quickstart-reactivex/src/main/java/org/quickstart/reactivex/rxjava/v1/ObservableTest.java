/**
 * 项目名称：quickstart-reactivex 文件名：ObservableTest.java 版本信息： 日期：2018年5月20日 Copyright yangzl Corporation 2018 版权所有 *
 */
package org.quickstart.reactivex.rxjava.v1;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * ObservableTest
 * 
 * @author：youngzil@163.com
 * @2018年5月20日 下午1:14:06
 * @since 1.0
 */
public class ObservableTest {

  public static void main(String[] args) {

    Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
      @Override
      public void call(Subscriber<? super String> subscriber) {
        subscriber.onNext("Hello");
        subscriber.onNext("Hi");
        subscriber.onNext("Aloha");
        subscriber.onCompleted();
      }

    })
    // .subscribeOn(Schedulers.immediate())//直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
    // .subscribeOn(Schedulers.newThread())//总是启用新线程，并在新线程执行操作
    // .subscribeOn(Schedulers.io())//I/O 操作
    // .subscribeOn(Schedulers.computation())//计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算
    // .observeOn(Schedulers.newThread())//
    ;

    // 1、创建被观察者,这是最正宗的写法，创建了一个开关类，产生了五个事件，分别是：开，关，开，开，结束。
    Observable switcher = Observable.create(new Observable.OnSubscribe<String>() {

      @Override
      public void call(Subscriber<? super String> subscriber) {
        subscriber.onNext("On");
        subscriber.onNext("Off");
        subscriber.onNext("On");
        subscriber.onNext("On");
        subscriber.onCompleted();
      }
    });

    // 偷懒模式是一种简便的写法，实际上也都是被观察者把那些信息"On","Off","On","On"，包装成onNext（"On"）这样的事件依次发给观察者，当然，它自己补上了onComplete()事件。

    // 偷懒模式1
    Observable switcher1 = Observable.just("On", "Off", "On", "On");

    // 偷懒模式2
    String[] kk = {"On", "Off", "On", "On"};
    Observable switche2 = Observable.from(kk);

    // 2、创建观察者，正常模式：
    Subscriber light = new Subscriber<String>() {
      @Override
      public void onCompleted() {
        // 被观察者的onCompleted()事件会走到这里;
        System.out.println("结束观察...\n");
      }

      @Override
      public void onError(Throwable e) {
        // 出现错误会调用这个方法
        System.out.println("观察者出现异常==" + e);
      }

      @Override
      public void onNext(String s) {
        // 处理传过来的onNext事件
        System.out.println("handle this---" + s);
      }
    };

    // 偷懒模式(非正式写法)
    // 之所以说它是非正式写法，是因为Action1是一个单纯的人畜无害的接口，和Observer没有啥关系， 只不过它可以当做观察者来使，专门处理onNext 事件，这是一种为了简便偷懒的写法。
    // 当然还有Action0，Action2,Action3...,0,1,2,3分别表示call()这个方法能接受几个参数。

    Action1 light2 = new Action1<String>() {
      @Override
      public void call(String s) {
        System.out.println("handle this---" + s);
      }
    };

    // 3、关联观察者和被观察者
    // 台灯观察开关，逻辑是没错的，而且正常来看就应该是light.subscribe(switcher)才对，之所以“开关订阅台灯”，是为了保证流式API调用风格
    switcher.subscribe(light);

    // 这就是RxJava的流式API调用
    Observable.just("On", "Off", "On", "On")
        // 在传递过程中对事件进行过滤操作
        .filter(new Func1<String, Boolean>() {
          @Override
          public Boolean call(String s) {
            return null != s;
          }
        }).subscribe(light);

    // 由于被观察者产生事件，是事件的起点，那么开头就是用Observable这个主体调用来创建被观察者，产生事件，为了保证流式API调用规则，就直接让Observable作为唯一的调用主体，一路调用下去。

    // 创建被观察者，是事件传递的起点
    Observable.just("On", "Off", "On", "On")
        // 这就是在传递过程中对事件进行过滤操作
        .filter(new Func1<String, Boolean>() {
          @Override
          public Boolean call(String s) {
            return null != s;
          }
        })
        // 实现订阅
        .subscribe(
            // 创建观察者，作为事件传递的终点处理事件
            new Subscriber<String>() {
              @Override
              public void onCompleted() {
                System.out.println("结束观察2...\n");
              }

              @Override
              public void onError(Throwable e) {
                // 出现错误会调用这个方法
              }

              @Override
              public void onNext(String s) {
                // 处理事件
                System.out.println("handle this2---" + s);
              }
            });
    // 创建被观察者，产生事件
    // 设置事件传递过程中的过滤，合并，变换等加工操作。
    // 订阅一个观察者对象，实现事件最终的处理。
    // Tips: 当调用订阅操作（即调用Observable.subscribe()方法）的时候，被观察者才真正开始发出事件。

    // 1 、传递过程中的过滤，如上filter
    // 2 、传递过程中的转换，使用map操作来完成类型转换
    // 3 、传递过程中的转换，去除循环嵌套，FlatMap将每个Observable产生的事件里的信息再包装成新的Observable传递出来，
    // 3 、传递过程中的过滤，Sample，ThrottleFirst等.sample(200,TimeUnit.MILLISECONDS)//这个操作符简单理解就是每隔200ms发送里时间点最近那个事件，
    // 3 、传递过程中的缓存，buffer，window等，.buffer(100,TimeUnit.MILLISECONDS)//这个操作符简单理解就是把100毫秒内的事件打包成list发送

    Observable.interval(1, TimeUnit.MILLISECONDS)//
        .onBackpressureDrop()//
        .observeOn(Schedulers.newThread())//
        .subscribe(new Subscriber<Long>() {

          @Override
          public void onStart() {
            System.out.println("start");
            // request(1);
          }

          @Override
          public void onCompleted() {

          }

          @Override
          public void onError(Throwable e) {
            System.out.println(e.toString());
          }

          @Override
          public void onNext(Long aLong) {
            System.out.println("TAG---->" + aLong);
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });

    // System.in.read();

    // while(true) {}
    for (;;) {
    }

  }

}
