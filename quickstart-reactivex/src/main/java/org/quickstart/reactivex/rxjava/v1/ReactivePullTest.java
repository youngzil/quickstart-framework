/**
 * 项目名称：quickstart-reactivex 
 * 文件名：ReactivePullTest.java
 * 版本信息：
 * 日期：2018年11月19日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.reactivex.rxjava.v1;

import rx.Observable;
import rx.Subscriber;

/**
 * ReactivePullTest 
 *  
 * @author：youngzil@163.com
 * @2018年11月19日 下午2:26:35 
 * @since 1.0
 */
public class ReactivePullTest {
    
    public static void main(String[] args) {
      //被观察者将产生100000个事件
        Observable observable=Observable.range(1,100000);
        
        class MySubscriber extends Subscriber<T> {
            @Override
            public void onStart() {
            //一定要在onStart中通知被观察者先发送一个事件
              request(1);
            }
         
            @Override
            public void onCompleted() {
//                ...
            }
         
            @Override
            public void onError(Throwable e) {
//                ...
            }
         
            @Override
            public void onNext(T n) {
//                ...
//                ...
                //处理完毕之后，在通知被观察者发送下一个事件
                request(1);
            }
        }

        observable.observeOn(Schedulers.newThread())
                    .subscribe(MySubscriber);
    }

}
