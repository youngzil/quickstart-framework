1、基本的4个概念
2、线程控制：指定线程运行：生产和消费，4类线程
3、事件变换
4、背压（Backpressure）
5、
6、




---------------------------------------------------------------------------------------------------------------------
基本的4个概念

RxJava 的观察者模式
RxJava 有四个基本概念：
1、Observable (可观察者，即被观察者)、 
2、Observer (观察者)、ActionX类、抽象实现子类Subscriber，工具类 Subscribers
3、subscribe (订阅)、
4、事件。

Observable 和 Observer 通过 subscribe() 方法实现订阅关系，从而 Observable 可以在需要的时候发出事件来通知 Observer。



RxJava 的事件回调方法：Observer类
onNext()
onCompleted(): 事件队列完结。RxJava 不仅把每个事件单独处理，还会把它们看做一个队列。RxJava 规定，当不会再有新的 onNext() 发出时，需要触发 onCompleted() 方法作为标志。
onError(): 事件队列异常。在事件处理过程中出异常时，onError() 会被触发，同时队列自动终止，不允许再有事件发出。

在一个正确运行的事件序列中, onCompleted() 和 onError() 有且只有一个，并且是事件序列中的最后一个。需要注意的是，onCompleted() 和 onError() 二者也是互斥的，即在队列中调用了其中一个，就不应该再调用另一个。



抽象实现子类Subscriber：
1、onStart(): 这是 Subscriber 增加的方法。它会在 subscribe 刚开始，而事件还未发送之前被调用，可以用于做一些准备工作
2、unsubscribe() 来解除引用关系



Subscriber 的onStart()和Observable.doOnSubscribe()：在 subscribe() 调用后而且在事件发送前执行
1、onStart() ：只能执行在 subscribe() 被调用时的线程
2、Observable.doOnSubscribe()：它可以指定线程。默认情况下doOnSubscribe() 执行在 subscribe() 发生的线程；如果在 doOnSubscribe() 之后有 subscribeOn() 的话，它将执行在离它最近的 subscribeOn() 所指定的线程。
Observable.create(onSubscribe)
    .subscribeOn(Schedulers.io())
    .doOnSubscribe(new Action0() {
        @Override
        public void call() {
            progressBar.setVisibility(View.VISIBLE); // 需要在主线程执行
        }
    })
    .subscribeOn(AndroidSchedulers.mainThread()) // 指定主线程
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(subscriber);




---------------------------------------------------------------------------------------------------------------------
线程控制：指定线程运行：生产和消费，4类线程


在不指定线程的情况下， RxJava 遵循的是线程不变的原则，即：在哪个线程调用 subscribe()，就在哪个线程生产事件；在哪个线程生产事件，就在哪个线程消费事件。如果需要切换线程，就需要用到 Scheduler （调度器）。

在RxJava 中，Scheduler ——调度器，相当于线程控制器，RxJava 通过它来指定每一段代码应该运行在什么样的线程。RxJava 已经内置了几个 Scheduler ，它们已经适合大多数的使用场景：

1、Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
2、Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
3、Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
4、Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
5、Android 还有一个专用的 AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。


使用
有了这几个 Scheduler ，就可以使用 subscribeOn() 和 observeOn() 两个方法来对线程进行控制了。
1、subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
2、observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。


 subscribeOn() 的位置放在哪里都可以，但它是只能调用一次的，作用的是生产线程
 observeOn() 的多次调用，程序实现了线程的多次切换，作用的是后面的操作
  
 如下面：
 Observable.just(1, 2, 3, 4) // IO 线程，由 subscribeOn() 指定
     .subscribeOn(Schedulers.io())
     .observeOn(Schedulers.newThread())
     .map(mapOperator) // 新线程，由 observeOn() 指定
     .observeOn(Schedulers.io())
     .map(mapOperator2) // IO 线程，由 observeOn() 指定
     .observeOn(AndroidSchedulers.mainThread) 
     .subscribe(subscriber);  // Android 主线程，由 observeOn() 指定
 
 
 
 
 
---------------------------------------------------------------------------------------------------------------------
事件变换

生产事件---》转换----》订阅


转换方法：
map() ：一对一转换
flatMap() 中返回的是个 Observable 对象
compose() ：使用 compose() 方法，Observable 可以利用传入的 Transformer 对象的 call 方法直接对自身进行处理，
merge:合并多个Observable



flatMap() 的原理是这样的：
1、使用传入的事件对象创建一个 Observable 对象；
2、并不发送这个 Observable, 而是将它激活，于是它开始发送事件；
3、每一个创建出来的 Observable 发送的事件，都被汇入同一个 Observable ，而这个 Observable 负责将这些事件统一交给 Subscriber 的回调方法。



---------------------------------------------------------------------------------------------------------------------
背压（Backpressure）

背压是流速控制的一种策略

需要强调两点：
1、背压策略的一个前提是异步环境，也就是说，被观察者和观察者处在不同的线程环境中。
2、背压（Backpressure）并不是一个像flatMap一样可以在程序中直接使用的操作符，他只是一种控制事件流速的策略。



Hot and Cold Observables：

Cold Observables：指的是那些在订阅之后才开始发送事件的Observable（每个Subscriber都能接收到完整的事件）。
Hot Observables:指的是那些在创建了Observable之后，（不管是否订阅）就开始发送事件的Observable

需要说明的时，Hot Observables 和cold Observables并不是严格的概念区分，它只是对于两类Observable形象的描述
其实也有创建了Observable之后调用诸如publish()方法就可以开始发送事件的,这里咱们暂且忽略。


我们一般使用的都是Cold Observable，除非特殊需求，才会使用Hot Observable,在这里，Hot Observable这一类是不支持背压的，而是Cold Observable这一类中也有一部分并不支持背压（比如interval，timer等操作符创建的Observable）。



背压策略解决：
1、响应式拉取（reactive pull）
2、过滤（抛弃）：就是虽然生产者产生事件的速度很快，但是把大部分的事件都直接过滤（浪费）掉，从而间接的降低事件发送的速度。相关类似的操作符：Sample，ThrottleFirst....
  sample：在一段时间内,只处理最后一个数据
  throttleFirst：在一段时间内,只处理第一个数据
  debounce：发送一个数据,开始计时,到了规定时间内,若没有再发送数据,则开始处理数据,反之重新开始计时。
3、缓存：就是虽然被观察者发送事件速度很快，观察者处理不过来，但是可以选择先缓存一部分，然后慢慢读。相关类似的操作符：buffer，window...
  buffer：将多个事件打包放入一个List中，再一起发射。
  window：将多个事件打包放入一个Observable中，再一起发射。
4、对于不支持背压的Observable除了使用上述两类生硬的操作符之外，还有更好的选择：onBackpressurebuffer，onBackpressureDrop。
onBackpressurebuffer：把observable发送出来的事件做缓存，当request方法被调用的时候，给下层流发送一个item(如果给这个缓存区设置了大小，那么超过了这个大小就会抛出异常)。
onBackpressureDrop：将observable发送的事件抛弃掉，直到subscriber再次调用request（n）方法的时候，就发送给它这之后的n个事件。

onBackpressureDrop：将observable发送的事件抛弃掉，直到subscriber再次调用request(n)方法的时候，就发送给它这之后的n个事件。

onBackpressurebuffer：把observable发送出来的事件做缓存，当request(n)方法被调用的时候，给下层流发送一个item(如果给这个缓存区设置了大小，那么超过了这个大小就会抛出异常)。



---------------------------------------------------------------------------------------------------------------------





