/**
 * 项目名称：quickstart-reactivex 
 * 文件名：AbstractObserver.java
 * 版本信息：
 * 日期：2018年11月20日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.reactivex.rxjava.v2.samples.ui.operators;

import org.quickstart.reactivex.rxjava.v2.samples.utils.AppConstant;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * AbstractObserver 
 *  
 * @author：youngzil@163.com
 * @2018年11月20日 上午10:01:14 
 * @since 1.0
 */
public abstract class AbstractObserver {
    
    protected Observer<Integer> getFirstObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(" First onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                System.out.println(" First onNext : value : " + value);
                System.out.println(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(" First onError : " + e.getMessage());
                System.out.println(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onComplete() {
                System.out.println(" First onComplete");
                System.out.println(AppConstant.LINE_SEPARATOR);
            }
        };
    }

    protected Observer<Integer> getSecondObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(" Second onSubscribe : isDisposed :" + d.isDisposed());
                System.out.println(" Second onSubscribe : " + d.isDisposed());
                System.out.println(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onNext(Integer value) {
                System.out.println(" Second onNext : value : " + value);
                System.out.println(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(" Second onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println(" Second onComplete");
            }
        };
    }

}
