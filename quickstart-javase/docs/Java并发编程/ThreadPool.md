ThreadPoolExecutor或者Executors工具类（4种）来创建
参数：7个
拒绝策略
创建线程流程和销毁线程流程
JDK 为我们内置了五种常见线程池的实现


线程池有哪几种工作队列
怎么理解无界队列和有界队列

线程创建工作流程
什么时候销毁线程

线程池其他方法：
beforeExecute、afterExecute、




普通线程池：线程创建流程corePoolSize ---> queue ---> maximumPoolSize ,从queue中获取task时候，设置超时时间，并且等待keepAliveTime获取不到task就销毁thread
定时任务线程池：线程创建流程一样，使用延时阻塞队列DelayedWorkQueue，线程不停地的等待task

普通线程池和定时任务线程池唯一区别：使用的BlockingQueue不一样


源码：getTask()中workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS)，超时获取不到就销毁thread




提交任务：execute() 和 submit()
execute()：FutureTask（ExecutorCompletionService）
submit()：Callable接口、Future接口

RejectedExecutionHandler策略
其他方法：beforeExecute、afterExecute、

ThreadLocal使用：以线程为单位进行隔离，因为WeakReference不会导致内存泄漏，线程复用的时候没有remove可能会导致后面的任务取到前面任务存进去的值，导致程序出错




线程池：
ExecutorService
ThreadPoolExecutor或者Executors工具类来创建
public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) 
RejectedExecutionHandler:
ThreadPoolExecutor.AbortPolicy()，抛出java.util.concurrent.RejectedExecutionException异常 
ThreadPoolExecutor.CallerRunsPolicy，它直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务。
ThreadPoolExecutor.DiscardOldestPolicy();，先poll掉workQueue中的一个任务，然后调用线程池的execute方法执行当前task
ThreadPoolExecutor.DiscardPolicy，拒绝策略方法为空，就是不做任何处理，默认情况下它将丢弃被拒绝的任务。

线程池其他方法：
beforeExecute、afterExecute、




线程池：
ExecutorService
ThreadPoolExecutor或者Executors工具类来创建

public ThreadPoolExecutor(int corePoolSize,    //核心线程的数量
                          int maximumPoolSize,    //最大线程数量
                          long keepAliveTime,    //超出核心线程数量以外的线程空余存活时间
                          TimeUnit unit,    //存活时间的单位
                          BlockingQueue<Runnable> workQueue,    //保存待执行任务的队列
                          ThreadFactory threadFactory,    //创建新线程使用的工厂
                          RejectedExecutionHandler handler // 当任务无法执行时的处理器
                          ) {...}


JDK 为我们内置了五种常见线程池的实现，均可以使用 Executors 工厂类创建。
1.newFixedThreadPool
2.newSingleThreadExecutor
3.newCachedThreadPool
4.newScheduledThreadPool


ExecutorService 提供了两种提交任务的方法：
execute()：提交不需要返回值的任务
submit()：提交需要返回值的任务


ExecutorCompletionService来submit处理任务获取返回值，或者execute提交FutureTask任务
FutureTask实现RunableFuture，又是实现Runable和Future
Callable、Future、

Callable接口、Future接口、
FutureTask（ExecutorCompletionService）、
Executors、
ExecutorService、ThreadPoolExecutor、
ThreadFactory、

ThreadLocal
public class ExecutorCompletionService<V> implements CompletionService<V> {

有人说ThreadLocal使用的线程是在线程池中重复使用的时候，会导致内存泄露，其实不会，因为key使用的是WeakReference，
但是不手动remove掉对象，线程重复使用的时候，可能后面的任务会取到前面任务存进去的值，导致程序出错


ThreadLocal内存泄漏的根源是：由于ThreadLocalMap的生命周期跟Thread一样长，如果没有手动删除对应key就会导致内存泄漏，而不是因为弱引用。
可是ThreadLocal并不会产生内存泄露，因为ThreadLocalMap在选择key的时候，并不是直接选择ThreadLocal实例，而是ThreadLocal实例的弱引用。

https://droidyue.com/blog/2016/03/13/learning-threadlocal-in-java/
https://juejin.im/post/5ac2eb52518825555e5e06ee




RejectedExecutionHandler:
ThreadPoolExecutor.AbortPolicy()，默认的，抛出java.util.concurrent.RejectedExecutionException异常 
ThreadPoolExecutor.CallerRunsPolicy，它直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务。
ThreadPoolExecutor.DiscardOldestPolicy();，先poll掉workQueue中的一个任务，然后调用线程池的execute方法执行当前task
ThreadPoolExecutor.DiscardPolicy，拒绝策略方法为空，就是不做任何处理，默认情况下它将丢弃被拒绝的任务。

线程池其他方法：
beforeExecute、afterExecute、





线程池有哪几种工作队列
1、ArrayBlockingQueue （有界队列）：是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序。
2、LinkedBlockingQueue （无界队列）：一个基于链表结构的阻塞队列，此队列按FIFO （先进先出） 排序元素，吞吐量通常要高于ArrayBlockingQueue。静态工厂方法Executors.newFixedThreadPool()使用了这个队列。
3、SynchronousQueue（同步队列）: 一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue，静态工厂方法Executors.newCachedThreadPool使用了这个队列。
4、DelayQueue（延迟队列）：一个任务定时周期的延迟执行的队列。根据指定的执行时间从小到大排序，否则根据插入到队列的先后排序。
5、PriorityBlockingQueue（优先级队列）: 一个具有优先级得无限阻塞队列。



ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。
LinkedBlockingQueue ：一个由链表结构组成的有界阻塞队列。
PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。
DelayQueue：一个使用优先级队列实现的无界阻塞队列。
SynchronousQueue：一个不存储元素的阻塞队列。
LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。



怎么理解无界队列和有界队列
有界队列即长度有限，满了以后ArrayBlockingQueue会插入阻塞。
无界队列就是里面能放无数的东西而不会因为队列长度限制被阻塞，但是可能会出现OOM异常。



参考
https://www.zybuluo.com/mikumikulch/note/713546
https://www.zybuluo.com/mikumikulch/note/712598
https://www.jianshu.com/p/9710b899e749


参考
https://www.zybuluo.com/mikumikulch/note/712598


理解线程池的原理
http://blog.csdn.net/mine_song/article/details/70948223
http://www.cnblogs.com/tiancai/p/9407048.html


