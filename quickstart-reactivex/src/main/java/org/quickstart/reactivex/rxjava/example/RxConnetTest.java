package org.quickstart.reactivex.rxjava.example;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;

public class RxConnetTest {

    private static Integer [] integer={1,2,3,4,5,6};

    private void normol() {
        Observable  observable= Observable.from(integer);
        Action1 a1=new Action1<Integer>(){
            @Override
            public void call(Integer o) {
                System.out.println("观察者A  收到:  "+o+"\n");
            }
        };
        Action1 a2=new Action1<Integer>(){
            @Override
            public void call(Integer o) {
                System.out.println("观察者B  收到:  "+o+"\n");
            }
        };

        observable.subscribe(a1);
        observable.subscribe(a2);

    }
    
    public static void main(String[] args) {

        ConnectableObservable  observable= Observable.from(integer)
                                                    .publish();//将一个Observable转换为一个可连接的Observable

        Action1 a1=new Action1<Integer>(){
            @Override
            public void call(Integer o) {
                System.out.println("观察者A  收到:  "+o+"\n");
            }
        };
        Action1 a2=new Action1<Integer>(){
            @Override
            public void call(Integer o) {
                System.out.println("观察者B  收到:  "+o+"\n");
            }
        };

        observable.subscribe(a1);
        observable.subscribe(a2);
        observable.connect();

    }
}
