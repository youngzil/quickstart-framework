/**
 * 项目名称：quickstart-reactivex 
 * 文件名：MaybeObserverTest.java
 * 版本信息：
 * 日期：2018年11月19日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.reactivex.rxjava.v2;

import java.io.IOException;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.Single;
import rx.SingleSubscriber;

/**
 * MaybeObserverTest
 * 
 * @author：youngzil@163.com
 * @2018年11月19日 下午8:01:14
 * @since 1.0
 */
public class MaybeObserverTest {

    public static void main(String[] args) throws IOException {

        // 这种观察者模式并不用于发送大量数据，而是发送单个数据，也就是说，当你只想要某个事件的结果（true or false)的时候，你可以用这种观察者模式
        // 判断是否登陆
        Maybe.just(Boolean.TRUE)
                // 可能涉及到IO操作，放在子线程
                .subscribeOn(Schedulers.newThread())
                // 取回结果传到主线程
                // .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                }

                    @Override
                    public void onSuccess(Boolean value) {

                        System.out.println("data=" + value);

                        if (value) {
                            // ...
                        } else {
                            // ...
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                }

                    @Override
                    public void onComplete() {

                }
                });

        // 没有这一行，会主线程结束还会输出
        System.in.read();

    }

}
