/**
 * 项目名称：quickstart-reactivex 
 * 文件名：FlowableTest.java
 * 版本信息：
 * 日期：2018年11月19日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.reactivex.rxjava.v2;

import java.util.concurrent.TimeUnit;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * FlowableTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年11月19日 下午7:50:14
 * @since 1.0
 */
public class FlowableTest {

    // Flowable是支持背压的，也就是说，一般而言，上游的被观察者会响应下游观察者的数据请求，下游调用request(n)来告诉上游发送多少个数据。这样避免了大量数据堆积在调用链上，使内存一直处于较低水平。

    public static void main(String[] args) {

        Flowable.range(0, 10).subscribe(new Subscriber<Integer>() {
            Subscription sub;

            // 当订阅后，会首先调用这个方法，其实就相当于onStart()，
            // 传入的Subscription s参数可以用于请求数据或者取消订阅
            // onSubscribe(Subscription s)传入的参数s就肩负着取消订阅的功能，当然，他也可以用于请求上游的数据。
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onsubscribe start");
                sub = s;
                sub.request(1);
                // 一定会执行完onSubscribe后面的代码，才会执行到onNext
                // 网上说执行完sub.request(1);不会等后面的代码执行完就会执行到onNext方法
                // TIPS: 尽可能确保在request（）之前已经完成了所有的初始化工作，否则就有空指针的风险。
                try {
                    TimeUnit.SECONDS.sleep(10L);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("onsubscribe end");

            }

            @Override
            public void onNext(Integer o) {
                System.out.println("onNext--->" + o);
                sub.request(1);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

        // Flowable虽然可以通过create()来创建，但是你必须指定背压的策略，以保证你创建的Flowable是支持背压的（这个在1.0的时候就很难保证，可以说RxJava2.0收紧了create()的权限）。
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onComplete();
            }
        }
        // 需要指定背压策略
                , BackpressureStrategy.BUFFER);

        
        Flowable.just(1, 2, 3)
        .doOnCancel(() -> System.out.println("Cancelled!"))
        .take(2)
        .subscribe(System.out::println);

    }
}
