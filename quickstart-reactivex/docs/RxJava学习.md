RxJava是基于响应式编程思想，实现并扩展了观察者模式，可以进行异步操作的库


初学RxJava只要把握两点：观察者模式和异步,就基本可以熟练使用RxJava了。

RxJava1.0中：Observable(被观察者)订阅Observer（观察者）/Subscriber(观察者)

RxJava2.0中分开：
Observable(被观察者)/Observer（观察者）
Flowable(被观察者)/Subscriber(观察者)
RxJava2.X中，Observeable用于订阅Observer，是不支持背压的，而Flowable用于订阅Subscriber，是支持背压(Backpressure)的。

当然，除了上面这两种观察者，还有一类观察者
Single/SingleObserver
Completable/CompletableObserver
Maybe/MaybeObserver
其实这三者都差不多，Maybe/MaybeObserver可以说是前两者的复合体


在1.0中，关于背压最大的遗憾，就是集中在Observable这个类中，导致有的Observable支持背压，有的不支持。为了解决这种缺憾，新版本把支持背压和不支持背压的Observable区分开来。



//Observable接口
interface ObservableSource<T> {
    void subscribe(Observer<? super T> observer);
}
//Single接口
interface SingleSource<T> {
    void subscribe(SingleObserver<? super T> observer);
}
//Completable接口
interface CompletableSource {
    void subscribe(CompletableObserver observer);
}
//Maybe接口
interface MaybeSource<T> {
    void subscribe(MaybeObserver<? super T> observer);
}
//Flowable接口
public interface Publisher<T> {
    public void subscribe(Subscriber<? super T> s);
}
其实我们可以看到，每一种观察者都继承自各自的接口，这也就把他们能完全的区分开，各自独立（特别是Observable和Flowable）,保证了他们各自的拓展或者配套的操作符不会相互影响。



创建被观察者，产生事件
设置事件传递过程中的过滤，合并，变换等加工操作。【加工，过滤，转换，合并等等】
订阅一个观察者对象，实现事件最终的处理。
Tips: 当调用订阅操作（即调用Observable.subscribe()方法）的时候，被观察者才真正开始发出事件。


实际上线程调度只有subscribeOn（）和observeOn（）两个方法。对于初学者，只需要掌握两点：
subscribeOn（）它指示Observable在一个指定的调度器上创建（只作用于被观察者创建阶段）。只能指定一次，如果指定多次则以第一次为准
observeOn（）指定在事件传递（加工变换）和最终被处理（观察者）的发生在哪一个调度器。可指定多次，每次指定完都在下一步生效。


关于背压（Backpressure）
通过参考和对比大量的相关资料，我在这里先对背压（Backpressure）做一个明确的定义：背压是指在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略

简而言之，背压是流速控制的一种策略。

需要强调两点：
1、背压策略的一个前提是异步环境，也就是说，被观察者和观察者处在不同的线程环境中。
2、背压（Backpressure）并不是一个像flatMap一样可以在程序中直接使用的操作符，他只是一种控制事件流速的策略。

在RxJava的观察者模型中，被观察者是主动的推送数据给观察者，观察者是被动接收的。
而响应式拉取（reactive pull）则反过来，观察者主动从被观察者那里去拉取数据，而被观察者变成被动的等待通知再发送数据。


背压是一种策略，具体措施是下游观察者通知上游的被观察者发送事件
背压策略很好的解决了异步环境下被观察者和观察者速度不一致的问题
在RxJava1.X中，同样是Observable，有的不支持背压策略，导致某些情况下，显得特别麻烦，出了问题也很难排查，使得RxJava的学习曲线变得十份陡峭。


1.0和2.0的区别：

2.0被观察者不再接收null作为数据源了。

action相关
之前我在文章里介绍过关于Action这类接口，在1.0中，这类接口是从Action0，Action1...往后排（数字代表可接受的参数），现在做出了改动
Rx1.0-----------Rx2.0
Action0--------Action
Action1--------Consumer
Action2--------BiConsumer
后面的Action都去掉了，只保留了ActionN

Function相关
同上，也是命名方式的改变
上面那两个类，和RxJava1.0相比，他们都增加了throws Exception，也就是说，在这些方法做某些操作就不需要try-catch。

在RxJava1.0中，有的人会使用CompositeSubscription来收集Subscription，来统一取消订阅，现在在RxJava2.0中，由于subscribe()方法现在返回void，那怎么办呢？
其实在RxJava2.0中，Flowable提供了subscribeWith这个方法可以返回当前订阅的观察者，并且通过ResourceSubscriber DisposableSubscriber等观察者来提供 Disposable的接口。
所以，如果想要达成RxJava1.0的效果，现在应该是这样做：
CompositeDisposable composite = new CompositeDisposable();
composite.add(Flowable.range(1, 8).subscribeWith(subscriber));
这个subscriber 应该是 ResourceSubscriber 或者 DisposableSubscriber 的实例。



参考
https://juejin.im/post/582b2c818ac24700618ff8f5
https://juejin.im/post/580103f20e3dd90057fc3e6d
https://juejin.im/post/582d413c8ac24700619cceed
https://www.baeldung.com/rx-java


背压（Backpressure）
https://www.baeldung.com/rxjava-backpressure
https://zhuanlan.zhihu.com/p/24473022
https://juejin.im/entry/58e704cbac502e4957b230eb
https://www.jianshu.com/p/870fe00d42ab


源码解析
https://yutiantina.github.io/categories/%E6%BA%90%E7%A0%81%E8%A7%A3%E6%9E%90/page/2/



如果你想把自己的RxJava1.0的迁移到2.0的版本，可以使用这个库RxJava2Interop,它可以在Rxjava1.0和2.0之间相互转换，也就是说，不仅可以把1.0的代码迁移到2.0，你还可以把2.0的代码迁移到1.0,哈哈。
https://github.com/akarnokd/RxJava2Interop


demo示例
https://github.com/ladingwu/ApplicationDemo
https://github.com/amitshekhariitbhu/RxJava2-Android-Samples


RxJava操作符大全
https://www.jianshu.com/p/3fdd9ddb534b



RxJava解析和测试https://www.infoq.cn/article/rxjava-by-example/

https://www.infoq.cn/article/rxjava2-by-example/
https://www.infoq.cn/article/Testing-RxJava/



RxJava弹珠交互图
https://rxmarbles.com/
https://github.com/staltz/rxmarbles













