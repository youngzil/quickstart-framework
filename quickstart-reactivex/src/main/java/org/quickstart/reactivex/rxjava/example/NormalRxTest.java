package org.quickstart.reactivex.rxjava.example;

import rx.Observable;
import rx.Subscriber;

/*
    同步情况下了解Rxjava的运行
 */
public class NormalRxTest {

    static String str ="一二三四五\n 上山打老虎\n 老虎一发威\n 武松就发怵\n";
    
    public static void main(String[] args) {
        //创建被观察者
        Observable observable=createObservable();
        //创建观察者
        Subscriber subscriber=createSubscriber();

        System.out.println("开始订阅，准备观察...\n");
        //事实上，observable不止可以订阅subscriber，也可以订阅ActionX()
        observable.subscribe(subscriber);

        //就像现在这样
//        observable.subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                //Action1也就意味着，只能传入一个参数 ----> String s,同理Action0，Action2....,
//                //在这个call方法中传入了onNext()的参数，相当于代替了onNext方法，但是就不能监听onComplete,onError方法了
//                mText.append("执行观察者中的onNext()...\n");
//                mText.append(s+"...\n");
//            }
//        });
    }

    private static Subscriber createSubscriber() {
        //创建观察者
        Subscriber subscriber=new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("执行观察者中的onCompleted()...\n");
                System.out.println("订阅完毕，结束观察...\n");
            }

            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(String s) {
                System.out.println("执行观察者中的onNext()...\n");
                System.out.println(s+"...\n");
            }

        };
        return  subscriber;
    }
    

    private static Observable createObservable(){
        //创建被观察者，这是最正常的创建方法
        Observable observable=Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("一二三四五");
                subscriber.onNext("上山打老虎");
                subscriber.onNext("老虎一发威");
                subscriber.onNext("武松就发怵");
                subscriber.onCompleted();

            }
        });
        //想要图方便，可以这样创建
        //from(T[])
//        String [] kk={"一二三四五","上山打老虎","老虎一发威","武松就发怵"};
//        Observable observable=Observable.from(kk);

        //或者这样
        //just(T...)
//        Observable observable=Observable.just("一二三四五","上山打老虎","老虎一发威","武松就发怵");

        return observable;
    }
}
