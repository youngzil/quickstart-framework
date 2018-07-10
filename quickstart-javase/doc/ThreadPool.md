理解线程池的原理
http://blog.csdn.net/mine_song/article/details/70948223

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


ExecutorCompletionService来处理返回值，或者execute提交FutureTask任务
FutureTask实现RunableFuture，又是实现Runable和Future
Callable、Future、

Callable接口、Future接口、
FutureTask（ExecutorCompletionService）、
Executors、
ExecutorService、ThreadPoolExecutor、
ThreadFactory、


