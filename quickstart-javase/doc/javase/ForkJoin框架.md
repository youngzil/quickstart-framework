Fork/Join 框架介绍




---------------------------------------------------------------------------------------------------------------------
Fork/Join 框架介绍


工作窃取（work-stealing）算法是指某个线程从其他队列里窃取任务来执行。


第一步分割任务。首先我们需要有一个 fork 类来把大任务分割成子任务，有可能子任务还是很大，所以还需要不停的分割，直到分割出的子任务足够小。

第二步执行任务并合并结果。分割的子任务分别放在双端队列里，然后几个启动线程分别从双端队列里获取任务执行。子任务执行完的结果都统一放在一个队列里，启动一个线程从队列里拿数据，然后合并这些数据。



Fork/Join 使用两个类来完成以上两件事情：

1、ForkJoinTask：我们要使用 ForkJoin 框架，必须首先创建一个 ForkJoin 任务。它提供在任务中执行 fork() 和 join() 操作的机制，通常情况下我们不需要直接继承 ForkJoinTask 类，而只需要继承它的子类，Fork/Join 框架提供了以下两个子类：
    RecursiveAction：用于没有返回结果的任务。
    RecursiveTask ：用于有返回结果的任务。
2、ForkJoinPool ：ForkJoinTask 需要通过 ForkJoinPool 来执行，任务分割出的子任务会添加到当前工作线程所维护的双端队列中，进入队列的头部。当一个工作线程的队列里暂时没有任务时，它会随机从其他工作线程的队列的尾部获取一个任务。


ForkJoinPool 由 ForkJoinTask 数组和 ForkJoinWorkerThread 数组组成，ForkJoinTask 数组负责存放程序提交给 ForkJoinPool 的任务，而 ForkJoinWorkerThread 数组负责执行这些任务。

ForkJoinTask 的 fork 方法实现原理。当我们调用 ForkJoinTask 的 fork 方法时，程序会调用 ForkJoinWorkerThread 的 pushTask 方法异步的执行这个任务，然后立即返回结果。

pushTask 方法把当前任务存放在 ForkJoinTask 数组 queue 里。然后再调用 ForkJoinPool 的 signalWork() 方法唤醒或创建一个工作线程来执行任务。

ForkJoinTask 的 join 方法实现原理。Join 方法的主要作用是阻塞当前线程并等待获取结果。



Fork/Join 框架的异常处理
ForkJoinTask 在执行的时候可能会抛出异常，但是我们没办法在主线程里直接捕获异常，所以 ForkJoinTask 提供了 isCompletedAbnormally() 方法来检查任务是否已经抛出异常或已经被取消了，并且可以通过 ForkJoinTask 的 getException 方法获取异常。使用如下代码：
if(task.isCompletedAbnormally())
{
    System.out.println(task.getException());
}
getException 方法返回 Throwable 对象，如果任务被取消了则返回 CancellationException。如果任务没有完成或者没有抛出异常则返回 null。



参考
https://www.infoq.cn/article/fork-join-introduction/
https://www.ibm.com/developerworks/cn/java/j-jtp11137.html
https://blog.csdn.net/TimHeath/article/details/71307834
https://blog.csdn.net/yinwenjie/article/details/71524140
https://www.jianshu.com/p/6b9347a22fd1

---------------------------------------------------------------------------------------------------------------------


---------------------------------------------------------------------------------------------------------------------

