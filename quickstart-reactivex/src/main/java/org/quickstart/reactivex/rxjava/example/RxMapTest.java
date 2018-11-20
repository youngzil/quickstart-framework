package org.quickstart.reactivex.rxjava.example;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class RxMapTest {

    private static Integer [] number={1,2,3,4,5,6};

    private void start() {
        Observable.from(number)           //之前提到的创建Observable方法
                  .map(new Func1<Integer, Boolean>() {

                      @Override
                      public Boolean call(Integer integer) {
                          System.out.println("\n\n map()  Integer--->Boolean");
                          return (integer<3);
                      }
                  })
                  .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        System.out.println("\n观察到输出结果：\n");
                        System.out.println(aBoolean.toString());
                    }
                });
    }

}
