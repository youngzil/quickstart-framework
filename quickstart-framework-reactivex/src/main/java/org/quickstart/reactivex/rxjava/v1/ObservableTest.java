/**
 * 项目名称：quickstart-reactivex 
 * 文件名：ObservableTest.java
 * 版本信息：
 * 日期：2018年5月20日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.reactivex.rxjava.v1;

import rx.Observable;
import rx.Subscriber;

/**
 * ObservableTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年5月20日 下午1:14:06
 * @since 1.0
 */
public class ObservableTest {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }

        });
    }

}
