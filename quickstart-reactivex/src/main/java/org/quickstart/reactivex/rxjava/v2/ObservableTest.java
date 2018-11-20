/**
 * 项目名称：quickstart-reactivex 
 * 文件名：NormalTest.java
 * 版本信息：
 * 日期：2018年11月19日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.reactivex.rxjava.v2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * NormalTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年11月19日 下午6:12:29
 * @since 1.0
 */
public class ObservableTest {

    public static void main(String[] args) {

        Observable mObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onComplete();
            }
        });

        Observer mObserver = new Observer<Integer>() {
            // 这是新加入的方法，在订阅后发送数据之前，
            // 回首先调用这个方法，而Disposable可用于取消订阅
//            在Observer接口中，onSubscribe(Disposable d)方法传入的Disposable也是用于取消订阅，基本功能是差不多的，只不过命名不一致
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("onNext---" + value);

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("错误---》" + e.toString());

            }

            @Override
            public void onComplete() {
                System.out.println("流程结束");

            }
        };

        mObservable.subscribe(mObserver);

    }

}
