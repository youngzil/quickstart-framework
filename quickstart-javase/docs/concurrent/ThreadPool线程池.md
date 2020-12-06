- [线程池的分类和创建线程流程](#线程池的分类和创建线程流程)
- [线程池创建和7个参数](#线程池创建和7个参数)
- [RejectedExecutionHandler拒绝策略](#RejectedExecutionHandler拒绝策略)
- [线程池中ThreadLocal使用](#线程池中ThreadLocal使用)
- [线程池有哪几种工作队列](#线程池有哪几种工作队列)
- [怎么理解无界队列和有界队列](#怎么理解无界队列和有界队列)
- [线程池源码解读](#线程池源码解读)
- [CompletionService解读](#CompletionService解读)




创建线程流程和销毁线程流程
什么时候创建线程
什么时候销毁线程


JDK为我们内置了4种常见线程池的实现


线程池其他方法：
beforeExecute、afterExecute、




---------------------------------------------------------------------------------------------------------------------
## 线程池的分类和创建线程流程


普通线程池：线程创建流程corePoolSize ---> queue ---> maximumPoolSize ,从queue中获取task时候，设置超时时间，并且等待keepAliveTime获取不到task就销毁thread
定时任务线程池：线程创建流程一样，使用延时阻塞队列DelayedWorkQueue，线程不停地的等待task

普通线程池和定时任务线程池唯一区别：使用的BlockingQueue不一样


源码：getTask()中workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS)，超时获取不到就销毁thread





提交任务：execute() 和 submit()
execute()：只能执行Runnable，返回的Void
submit()：执行Runnable和Callable接口，返回的是Future接口
Future/FutureTask：表示异步计算的结果，可以对于具体的Runnable或者Callable任务进行查询是否完成，查询是否取消，获取执行结果，取消任务等操作。【直接包装一个线程并启动也是可以的，不仅仅是在线程池中】

Runnable 你太清楚了，它既可以用在 Thread 类中，也可以用在 ExecutorService 类中配合线程池的使用；
Callable 只能在 ExecutorService 中使用

Runnable 接口中的 run 方法签名上没有 throws ，自然也就没办法向上传播受检异常；
而 Callable 的 call() 方法签名却有 throws，所以它可以处理受检异常；




ExecutorService（ThreadPoolExecutor）和
CompletionService（ExecutorCompletionService）

感觉是ExecutorService需要自己保存submit之后的返回值Future，并且自己去轮序是否完成，并且无法按照先完成先获取，因为不知道哪个先完成，只能轮训去查询，【感觉是主动查询，没有完成后回调的意思】
CompletionService直接调用(take/poll)获取即可，自动是按照先完成先获取的顺序来获取的【不是主动查询，完成后回调的感觉】

ExecutorService：顺序轮序查询是否完成，中间有耗时的任务，就会导致后面已经完成的结果也无法及时处理
CompletionService：先完成的必定先被取出。这样就减少了不必要的等待时间。




## 线程池创建和7个参数

ThreadPoolExecutor或者Executors工具类（4种）来创建

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


JDK为我们内置了4种常见线程池的实现，均可以使用 Executors 工厂类创建。
1. newFixedThreadPool
2. newSingleThreadExecutor
3. newCachedThreadPool
4. newScheduledThreadPool




## RejectedExecutionHandler拒绝策略

RejectedExecutionHandler:
- ThreadPoolExecutor.AbortPolicy()，默认的，抛出java.util.concurrent.RejectedExecutionException异常 
- ThreadPoolExecutor.CallerRunsPolicy，它直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务。
- ThreadPoolExecutor.DiscardOldestPolicy();，先poll掉workQueue中的一个任务，然后调用线程池的execute方法执行当前task
- ThreadPoolExecutor.DiscardPolicy，拒绝策略方法为空，就是不做任何处理，默认情况下它将丢弃被拒绝的任务。

线程池其他方法：  
beforeExecute、afterExecute、



ExecutorService 提供了两种提交任务的方法：
- execute()：提交不需要返回值的任务
- submit()：提交需要返回值的任务


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




## 线程池中ThreadLocal使用
ThreadLocal使用：以线程为单位进行隔离，因为WeakReference不会导致内存泄漏，线程复用的时候没有remove可能会导致后面的任务取到前面任务存进去的值，导致程序出错

有人说ThreadLocal使用的线程是在线程池中重复使用的时候，会导致内存泄露，其实不会，因为key使用的是WeakReference，
但是不手动remove掉对象，线程重复使用的时候，可能后面的任务会取到前面任务存进去的值，导致程序出错


内存泄露
模拟内存泄漏借助了ThreadLocal对象来完成，ThreadLocal是一个线程私有变量，可以绑定到线程上，在整个线程的生命周期都会存在，但是由于ThreadLocal的特殊性，ThreadLocal是基于ThreadLocalMap实现的，ThreadLocalMap的Entry继承WeakReference，而Entry的Key是WeakReference的封装，换句话说Key就是弱引用，弱引用在下次GC之后就会被回收，
如果ThreadLocal在set之后不进行后续的操作，因为GC会把Key清除掉，但是Value由于线程还在存活，所以Value一直不会被回收，最后就会发生内存泄漏。


ThreadLocalMap 是通过 WeakReference 包装了 TreadLocal ，取的是 TreadLocal 的弱引用 对象
那么在 GC 的时候就会造成 TreadLocal 肯定就会被回收掉。 Entry 对象的 key 就为 null 了，然后 value 却是强引用 无法回收。
如果这个方法又长时间不结束的话，就有会这么一条 强引用的 GCROOT 引用链 的存在：
Thread Ref -> Thread -> ThreaLocalMap -> Entry -> value ； value 就一直不会被回收， 因为它的 另一半 key 已经不存在了，所以它也不会被调用。 这就造成了内存泄漏。



ThreadLocal内存泄漏的根源是：由于ThreadLocalMap的生命周期跟Thread一样长，如果没有手动删除对应key就会导致内存泄漏，而不是因为弱引用。
可是ThreadLocal并不会产生内存泄露，因为ThreadLocalMap在选择key的时候，并不是直接选择ThreadLocal实例，而是ThreadLocal实例的弱引用。

https://droidyue.com/blog/2016/03/13/learning-threadlocal-in-java/
https://juejin.im/post/5ac2eb52518825555e5e06ee



---------------------------------------------------------------------------------------------------------------------

## 线程池有哪几种工作队列

- 1、ArrayBlockingQueue （有界队列）：是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序。
- 2、LinkedBlockingQueue （无界队列）：一个基于链表结构的阻塞队列，此队列按FIFO （先进先出） 排序元素，吞吐量通常要高于ArrayBlockingQueue。静态工厂方法Executors.newFixedThreadPool()使用了这个队列。
- 3、SynchronousQueue（同步队列）: 一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue，静态工厂方法Executors.newCachedThreadPool使用了这个队列。
- 4、DelayQueue（延迟队列）：一个任务定时周期的延迟执行的队列。根据指定的执行时间从小到大排序，否则根据插入到队列的先后排序。
- 5、PriorityBlockingQueue（优先级队列）: 一个具有优先级得无限阻塞队列。



ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。
LinkedBlockingQueue ：一个由链表结构组成的有界阻塞队列。该队列的默认和最大长度为 Integer.MAX_VALUE 
PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。
DelayQueue：一个使用优先级队列实现的无界阻塞队列。
SynchronousQueue：一个不存储元素的阻塞队列。
LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。


链表形式的队列，其存取效率要比数组形式的队列高。但是在一些并发程序中，数组形式的队列由于具有一定的可预测性，因此可以在某些场景中获得更高的效率



## 怎么理解无界队列和有界队列
- 有界队列即长度有限，满了以后ArrayBlockingQueue会插入阻塞。
- 无界队列就是里面能放无数的东西而不会因为队列长度限制被阻塞，但是可能会出现OOM异常。



队列方法：存取、检查
- 抛出异常：add、remove、element
- 非阻塞返回特殊值：offer、poll、peek
- 一直阻塞直到有数据：put、take




参考
https://www.zybuluo.com/mikumikulch/note/713546
https://www.zybuluo.com/mikumikulch/note/712598
https://www.jianshu.com/p/9710b899e749


参考
https://www.zybuluo.com/mikumikulch/note/712598


理解线程池的原理
http://blog.csdn.net/mine_song/article/details/70948223
http://www.cnblogs.com/tiancai/p/9407048.html



---------------------------------------------------------------------------------------------------------------------
## 线程池源码解读

ThreadPoolExecutor---》AbstractExecutorService---》ExecutorService接口---》Executor接口

Executor接口：
void execute(Runnable command);

ExecutorService接口：
submit：3种
invokeAll：2种
invokeAny：2种
void shutdown();
List<Runnable> shutdownNow();
boolean isShutdown();
boolean isTerminated();

private static final int COUNT_BITS = Integer.SIZE - 3;
private static final int CAPACITY = (1 << COUNT_BITS) - 1;

// runState is stored in the high-order bits
private static final int RUNNING = -1 << COUNT_BITS;
private static final int SHUTDOWN = 0 << COUNT_BITS;
private static final int STOP = 1 << COUNT_BITS;
private static final int TIDYING = 2 << COUNT_BITS;
private static final int TERMINATED = 3 << COUNT_BITS;


- 1）RUNNING
此时可以接收任务，并执行
- 2）SHUTDOWN
此时线程池不接收新的任务，但是会等待所有任务执行完毕；当调用shutdown()，则状态会从running变为shutdown
- 3）STOP
此时线程池不接收新的任务，并且尝试中断正在执行的任务；当调用shutdownNow()，则状态会从running/shutdown变为stop
- 4）TIDYING
所有任务都已经终止，任务数为0，则状态为变成tidying，并且会回调terminated()方法；当队列和pool都为空，则会变为tidying
- 5）TERMINATED
terminated()方法执行完毕，状态即为terminated；可以调用awaitTermination()，则当状态变为terminated之前会一直阻塞


ThreadPoolExecutor有一个AtomicInteger变量，叫ctl（control的简写），一共32位，高3位存线程池状态runState（一共5种状态：Running，Shutdown，Stop，Tidying，Terminate），低29位存当前有效线程数workerCount

AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
int COUNT_BITS = Integer.SIZE - 3; //COUNT_BITS=32
int CAPACITY   = (1 << COUNT_BITS) - 1; //高3位000，低29位全为1
int RUNNING    = -1 << COUNT_BITS; //高3位111，低29位全为0
int SHUTDOWN   =  0 << COUNT_BITS; //高3位000，低29位全为0
int STOP       =  1 << COUNT_BITS; //高3位001，低29位全为0
int STOPPING =  2 << COUNT_BITS;   //高3位010，低29位全为0
int TERMINATED =  3 << COUNT_BITS; //高3位011，低29位全为0



参考  
https://zhuanlan.zhihu.com/p/33935753  
https://blog.csdn.net/programmer_at/article/details/79799267  
http://ifeve.com/java-threadpoolexecutor/  





---------------------------------------------------------------------------------------------------------------------

## CompletionService解读


ExecutorCompletionService 是 CompletionService 唯一实现类

如果你使用过消息队列，你应该秒懂我要说什么了，CompletionService 实现原理很简单
就是一个将异步任务的生产和任务完成结果的消费解耦的服务
也就是说，CompletionService实现了生产者提交任务和消费者获取结果的解耦，生产者和消费者都不用关心任务的完成顺序，由CompletionService来保证，消费者一定是按照任务完成的先后顺序来获取执行结果。
先完成的必定先被取出。这样就减少了不必要的等待时间。



日常处理中，我们通过会在任务提交到线程池后将返回的任务Future放到一个集合中，然后遍历集合通过Future的get()相关方法获取执行结果。
但如果在遍历过程中get()方法阻塞，即使位于集合后面的Future已经完成，遍历集合的线程也要继续等待，会影响执行结果的处理效率。
好的方案是，在任务执行结束后，返回值能够立即被获取，避免被未完成任务所影响。


ExecutorCompletionService类
该类实现了CompletionService，维护一个阻塞队列（默认为LinkedBlockingQueue类型）保存已经完成的任务Future。
某个任务执行结束后时会将任务的Future添加到该阻塞队列，阻塞队列按任务的完成顺序保存了任务的Future以便获取。



CompletionService  是一个接口，它简单的只有 5 个方法：
Future<V> submit(Callable<V> task);
Future<V> submit(Runnable task, V result);
Future<V> take() throws InterruptedException;
Future<V> poll();
Future<V> poll(long timeout, TimeUnit unit) throws InterruptedException;


按大类划分上面5个方法，其实就是两个功能
- 1、提交异步任务 （submit）
- 2、从队列中拿取并移除第一个元素 (take/poll)

Take: 如果队列为空，那么调用 take() 方法的线程会被阻塞
Poll: 如果队列为空，那么调用 poll() 方法的线程会返回 null
Poll-timeout: 以超时的方式获取并移除阻塞队列中的第一个元素，如果超时时间到，队列还是空，那么该方法会返回 null





参考  
https://www.cnblogs.com/aifuli/articles/8399252.html  
https://www.jianshu.com/p/cfda708a3478  
https://blog.csdn.net/ZYC88888/article/details/87706725
https://juejin.im/entry/6844903861153431566
https://blog.coderap.com/article/257
https://my.oschina.net/dyyweb/blog/660798
https://segmentfault.com/a/1190000019956568



---------------------------------------------------------------------------------------------------------------------
- [4、FutureTask](#4、FutureTask)
- [5、ThreadPoolExecutor](#5、ThreadPoolExecutor)

### 4、FutureTask
    
state状态位来存储执行状态RUNNING、RUN、CANCELLED，当使用get()获取执行结果的时候，未完成就挂起，在父类AQS中获取共享锁的线程会阻塞。即实现“任务未完成调用get方法的线程会阻塞”这样的功能。    
    
在FutureTask实现了异步的执行和提交，作为可以被Executor提交的对象。通过Sync来维护任务的执行状态，从而保证只有工作线程任务执行完后，其他线程才能获取到执行结果。AQS的子类Sync在这里主要是借用state状态位来存储执行状态，来完成对对各种状态以及加锁、阻塞的实现。    
    


### 5、ThreadPoolExecutor

(关于线程池的理解，可以查看 为什么要使用线程池? )


