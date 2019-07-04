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
public class RxJavaBackPressure2 {

  public static void main(String[] args) throws IOException {

    /*
     * Observable.interval(1, TimeUnit.MILLISECONDS)// .onBackpressureDrop()// .observeOn(Schedulers.newThread())// .subscribe(new Subscriber<Long>() {
     * 
     * @Override public void onStart() { System.out.println("onBackpressureDrop onStart"); // request(1); }
     * 
     * @Override public void onCompleted() { System.out.println("onBackpressureDrop onCompleted"); }
     * 
     * @Override public void onError(Throwable e) { System.out.println("onBackpressureDrop onError"); e.printStackTrace(); }
     * 
     * @Override public void onNext(Long aLong) { System.out.println("onBackpressureDrop TAG---->" + aLong); try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); } } });
     */

    // 被观察者每过1ms发射一个事件
    /*
     * Observable.interval(1, TimeUnit.MILLISECONDS)// .observeOn(Schedulers.newThread())// .subscribe((aLong) -> { // 观察者每过800ms处理一个事件 try { Thread.sleep(800); } catch (InterruptedException e) {
     * e.printStackTrace(); } System.out.println("rx_test,back_pressure：" + aLong); });
     */

    /*
     * Observable.interval(1, TimeUnit.MILLISECONDS)// .onBackpressureDrop()// .observeOn(Schedulers.newThread())// .subscribe(new Subscriber<Long>() {
     * 
     * @Override public void onStart() { super.onStart(); System.out.println("rx_test" + "controlBySpecialOperator：" + "onStart"); request(1); }
     * 
     * @Override public void onCompleted() { System.out.println("rx_test" + "controlBySpecialOperator：" + "onCompleted"); }
     * 
     * @Override public void onError(Throwable e) { System.out.println("rx_test" + "controlBySpecialOperator：" + "onError"); }
     * 
     * @Override public void onNext(Long aLong) { System.out.println("rx_test" + "controlBySpecialOperator：onNext：" + aLong); try { Thread.sleep(500); request(1); } catch (InterruptedException e) {
     * e.printStackTrace(); } } });
     */

    /*
     * Observable.interval(1, TimeUnit.MILLISECONDS)// .onBackpressureBuffer(1000)// .observeOn(Schedulers.newThread())// .subscribe(i -> { System.out.println(i); try { Thread.sleep(100); } catch
     * (Exception e) { } }, System.out::println);
     */

    Observable.interval(1, TimeUnit.MILLISECONDS)//
        .onBackpressureDrop()//
        .observeOn(Schedulers.newThread())//
        .subscribe(//
            i -> {
              System.out.println(i);
              try {
                Thread.sleep(100);
              } catch (Exception e) {
              }
            }, System.out::println);

    System.in.read();

  }

}
