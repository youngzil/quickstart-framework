package org.quickstart.reactivex.rxjava.v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-03 18:22
 */
public class ObservableMapTest {

  public static void main(String[] args) throws IOException {

    // map:map操作符可以将Observable里元素转换成另一种元素.例如, 我们可以将刚刚创建数字一一映射成字符串.
    Observable.create(new Observable.OnSubscribe<String>() {
      @Override
      public void call(Subscriber<? super String> subscriber) {
        subscriber.onNext("hello");
      }
    }).map(new Func1<String, String>() {
      @Override
      public String call(String s) {
        return s + "word";
      }
    }).subscribe(new Subscriber<String>() {
      @Override
      public void onCompleted() {

      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onNext(String s) {
        System.out.println("rx=" + s);
      }
    });

    Integer[] arr = {1, 2, 3};
    Observable.from(arr).map(new Func1<Integer, String>() {
      @Override
      public String call(Integer item) {
        return "number is: " + item; // 将每个元素映射成一句话
      }
    }).subscribe(new Action1<String>() {
      @Override
      public void call(String item) {
        System.out.println(item); // will print: the number is x
      }
    });

    // flatMap创建了一个外层Observable代替其内部的子Observable发射其元素, 这个操作我们称为拉平, 这个外层Observable最终将会返回给调用者.
    Observable.from(arr)//
        .flatMap(new Func1<Integer, Observable<Integer>>() {
          @Override
          public Observable<Integer> call(Integer number) {
            // 调用另外方法创建,创建一个Observable, 会发射出number个元素, 从0开始
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < number; ++i) {
              list.add(i);
            }
            return Observable.from(list);

          }
        })//
        .subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer item) {
            System.out.println("item=" + item + ",");
          }
        });

    // merge很简单, 就是将多个Observable合成一个, 要求这些Observable的泛型相同，合并多个Observable

    Integer[] arr1 = {1, 2, 3};
    Observable<Integer> observable1 = Observable.from(arr1);
    Integer[] arr2 = {4, 5, 6};
    Observable<Integer> observable2 = Observable.from(arr2);
    Integer[] arr3 = {7, 8, 9};
    Observable<Integer> observable3 = Observable.from(arr3);

    Observable.merge(observable1, observable2, observable3)//
        .subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer item) {
            System.out.println(item + ",");
          }
        });

    System.out.println("=========================================");

    // filter 过滤输入条件
    Observable.interval(1, TimeUnit.SECONDS).take(10)//
        .filter(new Func1<Long, Boolean>() {
          @Override
          public Boolean call(Long aLong) {
            System.out.println("aLong=" + aLong);
            return aLong > 5;
          }
        })//
        .subscribe(new Action1<Long>() {
          @Override
          public void call(Long aLong) {
            System.out.println("------->call()" + aLong);
          }
        });

    System.in.read();

  }

}
