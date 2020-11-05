- [Fork/Join框架介绍](#Fork/Join框架介绍)
- []()
- []()
- []()
- []()



---------------------------------------------------------------------------------------------------------------------

## Fork/Join框架介绍

1. 数据结构和核心参数
2. 任务提交
3. 任务执行

ForkJoinPool 内部的代码实现很复杂，同学们只需要理解它的内部思想即可。
本章重点：
- Fork/Join 任务运行机制
- ForkJoinPool 的 work-stealing 实现方式


TLDR; 如果觉得文章太长的话，以下就是结论：
- 1、ForkJoinPool 不是为了替代 ExecutorService，而是它的补充，在某些应用场景下性能比 ExecutorService 更好。（见 Java Tip: When to use ForkJoinPool vs ExecutorService ）
- 2、ForkJoinPool 主要用于实现“分而治之”的算法，特别是分治之后递归调用的函数，例如 quick sort 等。
- 3、ForkJoinPool 最适合的是计算密集型的任务，如果存在 I/O，线程间同步，sleep() 等会造成线程长时间阻塞的情况时，最好配合使用 ManagedBlocker。


分治法:
ForkJoinPool 并行的实现了分治算法（Divide-and-Conquer）
ForkJoinPool 的另一个特性是它使用了work-stealing（工作窃取）算法
在 ForkJoinPool 中，线程池中每个工作线程（ForkJoinWorkerThread）都对应一个任务队列（WorkQueue），工作线程优先处理来自自身队列的任务（LIFO或FIFO顺序，参数 mode 决定），然后以FIFO的顺序随机窃取其他队列中的任务。
Fork/Join 框架主要由 ForkJoinPool、ForkJoinWorkerThread 和 ForkJoinTask（包括RecursiveTask、RecursiveAction 和 CountedCompleter） 来实现，它们之间的联系如下：
ForkJoinPool 中使用 ForkJoinWorkerThread 来运行 ForkJoinTask 任务，ForkJoinPool 只接收 ForkJoinTask 任务（在实际使用中，也可以接收 Runnable/Callable 任务，但在真正运行时，也会把这些任务封装成 ForkJoinTask 类型的任务），
RecursiveTask 是 ForkJoinTask 的子类，是一个可以递归执行的 ForkJoinTask，
RecursiveAction 是一个无返回值的 RecursiveTask，
CountedCompleter 在任务完成执行后会触发执行一个自定义的钩子函数。
在实际运用中，我们一般都会继承 RecursiveTask 、RecursiveAction 或 CountedCompleter 来实现我们的业务需求，而不会直接继承 ForkJoinTask 类。



基本思想
把一个规模大的问题划分为规模较小的子问题，然后分而治之，最后合并子问题的解得到原问题的解。

步骤
- （1）分割原问题：
- （2）求解子问题：
- （3）合并子问题的解为原问题的解。
在分治法中，子问题一般是相互独立的，因此，经常通过递归调用算法来求解子问题。

典型应用场景
- （1）二分搜索
- （2）大整数乘法
- （3）Strassen矩阵乘法
- （4）棋盘覆盖
- （5）归并排序
- （6）快速排序
- （7）线性时间选择
- （8）汉诺塔


ForkJoinPool和ThreadPoolExecutor都是继承自AbstractExecutorService抽象类，所以它和ThreadPoolExecutor的使用几乎没有多少区别，除了任务变成了ForkJoinTask以外。
这里又运用到了一种很重要的设计原则——开闭原则——对修改关闭，对扩展开放。
可见整个线程池体系一开始的接口设计就很好，新增一个线程池类，不会对原有的代码造成干扰，还能利用原有的特性。

ForkJoinTask两个主要方法
- fork()方法类似于线程的Thread.start()方法，但是它不是真的启动一个线程，而是将任务放入到工作队列中。
- join()方法类似于线程的Thread.join()方法，但是它不是简单地阻塞线程，而是利用工作线程运行其它任务。当一个工作线程中调用了join()方法，它将处理其它任务，直到注意到目标子任务已经完成了。


三个子类
- RecursiveAction    无返回值任务。
- RecursiveTask    有返回值任务。
- CountedCompleter    无返回值任务，完成任务后可以触发回调。


ForkJoinPool内部原理
ForkJoinPool内部使用的是“工作窃取”算法实现的。
- （1）每个工作线程都有自己的工作队列WorkQueue；
- （2）这是一个双端队列，它是线程私有的；
- （3）ForkJoinTask中fork的子任务，将放入运行该任务的工作线程的队头，工作线程将以LIFO的顺序来处理工作队列中的任务；
- （4）为了最大化地利用CPU，空闲的线程将从其它线程的队列中“窃取”任务来执行；
- （5）从工作队列的尾部窃取任务，以减少竞争；
- （6）双端队列的操作：push()/pop()仅在其所有者工作线程中调用，poll()是由其它线程窃取任务时调用的；
- （7）当只剩下最后一个任务时，还是会存在竞争，是通过CAS来实现的；

ForkJoinPool最佳实践
- （1）最适合的是计算密集型任务，本文由公从号“彤哥读源码”原创；
- （2）在需要阻塞工作线程时，可以使用ManagedBlocker；
- （3）不应该在RecursiveTask的内部使用ForkJoinPool.invoke()/invokeAll()；

总结
- （1）ForkJoinPool特别适合于“分而治之”算法的实现；
- （2）ForkJoinPool和ThreadPoolExecutor是互补的，不是谁替代谁的关系，二者适用的场景不同；
- （3）ForkJoinTask有两个核心方法——fork()和join()，有三个重要子类——RecursiveAction、RecursiveTask和CountedCompleter；
- （4）ForkjoinPool内部基于“工作窃取”算法实现；
- （5）每个线程有自己的工作队列，它是一个双端队列，自己从队列头存取任务，其它线程从尾部窃取任务；
- （6）ForkJoinPool最适合于计算密集型任务，但也可以使用ManagedBlocker以便用于阻塞型任务；
- （7）RecursiveTask内部可以少调用一次fork()，利用当前线程处理，这是一种技巧；

彩蛋
ManagedBlocker怎么使用？
答：ManagedBlocker相当于明确告诉ForkJoinPool框架要阻塞了，ForkJoinPool就会启另一个线程来运行任务，以最大化地利用CPU。




工作窃取（work-stealing）算法是指某个线程从其他队列里窃取任务来执行。


第一步分割任务。首先我们需要有一个 fork 类来把大任务分割成子任务，有可能子任务还是很大，所以还需要不停的分割，直到分割出的子任务足够小。

第二步执行任务并合并结果。分割的子任务分别放在双端队列里，然后几个启动线程分别从双端队列里获取任务执行。子任务执行完的结果都统一放在一个队列里，启动一个线程从队列里拿数据，然后合并这些数据。


Fork/Join 使用两个类来完成以上两件事情：

- 1、ForkJoinTask：我们要使用 ForkJoin 框架，必须首先创建一个 ForkJoin 任务。
它提供在任务中执行 fork() 和 join() 操作的机制，通常情况下我们不需要直接继承 ForkJoinTask 类，而只需要继承它的子类，Fork/Join 框架提供了以下两个子类：
    RecursiveAction：用于没有返回结果的任务。
    RecursiveTask ：用于有返回结果的任务。
- 2、ForkJoinPool ：ForkJoinTask 需要通过 ForkJoinPool 来执行，任务分割出的子任务会添加到当前工作线程所维护的双端队列中，进入队列的头部。  
当一个工作线程的队列里暂时没有任务时，它会随机从其他工作线程的队列的尾部获取一个任务。


ForkJoinPool 由 ForkJoinTask 数组和 ForkJoinWorkerThread 数组组成，
ForkJoinTask 数组负责存放程序提交给 ForkJoinPool 的任务，
而 ForkJoinWorkerThread 数组负责执行这些任务。

ForkJoinTask 的 fork 方法实现原理。当我们调用 ForkJoinTask 的 fork 方法时，程序会调用 ForkJoinWorkerThread 的 pushTask 方法异步的执行这个任务，然后立即返回结果。

pushTask 方法把当前任务存放在 ForkJoinTask 数组 queue 里。然后再调用 ForkJoinPool 的 signalWork() 方法唤醒或创建一个工作线程来执行任务。

ForkJoinTask 的 join 方法实现原理。Join 方法的主要作用是阻塞当前线程并等待获取结果。


Fork/Join 框架的异常处理
ForkJoinTask 在执行的时候可能会抛出异常，但是我们没办法在主线程里直接捕获异常，
所以 ForkJoinTask 提供了 isCompletedAbnormally() 方法来检查任务是否已经抛出异常或已经被取消了，并且可以通过 ForkJoinTask 的 getException 方法获取异常。使用如下代码：
if(task.isCompletedAbnormally())
{
    System.out.println(task.getException());
}
getException 方法返回 Throwable 对象，
如果任务被取消了则返回 CancellationException。如果任务没有完成或者没有抛出异常则返回 null。


使用在 ForkJoinPool 中我们可以自定义四个参数：
- 1、parallelism：并行度，默认为CPU数，最小为1
- 2、factory：工作线程工厂；
- 3、handler：处理工作线程运行任务时的异常情况类，默认为null；
- 4、asyncMode：是否为异步模式，默认为 false。如果为true，表示子任务的执行遵循 FIFO 顺序并且任务不能被合并（join），这种模式适用于工作线程只运行事件类型的异步任务。


ForkJoinPool 中的任务执行分两种：
- 1、直接通过 FJP 提交的外部任务（external/submissions task），存放在 workQueues 的偶数槽位；
- 2、通过内部 fork 分割的子任务（Worker task），存放在 workQueues 的奇数槽位。


1. 外部任务（external/submissions task）提交
向 ForkJoinPool 提交任务有三种方式：
invoke()会等待任务计算完毕并返回计算结果；
execute()是直接向池提交一个任务来异步执行，无返回结果；
submit()也是异步执行，但是会返回提交的任务，在适当的时候可通过task.get()获取执行结果。

2. 子任务（Worker task）提交
子任务的提交相对比较简单，由任务的fork()方法完成。通过上面的流程图可以看到任务被分割（fork）之后调用了ForkJoinPool.WorkQueue.push()方法直接把任务放到队列中等待被执行。

说明：如果当前线程是 Worker 线程，说明当前任务是fork分割的子任务，通过ForkJoinPool.workQueue.push()方法直接把任务放到自己的等待队列中；否则调用ForkJoinPool.externalPush()提交到一个随机的等待队列中（外部任务）。



参考
https://www.jianshu.com/p/32a15ef2f1bf
https://www.jianshu.com/p/6a14d0b54b8d
https://blog.csdn.net/f641385712/article/details/83749798
https://www.infoq.cn/article/fork-join-introduction/
https://www.ibm.com/developerworks/cn/java/j-jtp11137.html
https://blog.csdn.net/TimHeath/article/details/71307834
https://blog.csdn.net/yinwenjie/article/details/71524140
https://www.jianshu.com/p/6b9347a22fd1
https://juejin.im/post/5dc5a148f265da4d4f65c191
https://cloud.tencent.com/developer/article/1409923
https://blog.csdn.net/u011001723/article/details/52794455
https://my.oschina.net/xinxingegeya/blog/3007257
https://segmentfault.com/a/1190000008470012
http://blog.dyngr.com/blog/2016/09/15/java-forkjoinpool-internals/
https://www.javaworld.com/article/2078440/java-tip-when-to-use-forkjoinpool-vs-executorservice.html?page=2




---------------------------------------------------------------------------------------------------------------------

