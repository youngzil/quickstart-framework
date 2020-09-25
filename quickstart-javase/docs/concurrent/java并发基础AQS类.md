

---------------------------------------------------------------------------------------------------------------------
http://ifeve.com/abstractqueuedsynchronizer-use/        
    
    
AQS(AbstractQueuedSynchronizer)，AQS是JDK下提供的一套用于实现基于FIFO等待队列的阻塞锁和相关的同步器的一个同步框架。
这个抽象类被设计为作为一些可用原子int值来表示状态的同步器的基类。
如果你有看过类似 CountDownLatch 类的源码实现，会发现其内部有一个继承了 AbstractQueuedSynchronizer 的内部类 Sync。
可见 CountDownLatch 是基于AQS框架来实现的一个同步器.类似的同步器在JUC下还有不少。(eg. Semaphore)   

谈到并发，我们不得不说AQS(AbstractQueuedSynchronizer)，所谓的AQS即是抽象的队列式的同步器，内部定义了很多锁相关的方法，我们熟知的ReentrantLock、ReentrantReadWriteLock、CountDownLatch、Semaphore等都是基于AQS来实现的。

AQS 内部维护了一个 FIFO 队列来管理锁。线程首先会尝试获取锁，如果失败，则将当前线程以及等待状态等信息包成一个 Node 节点放入同步队列阻塞起来，当持有锁的线程释放锁时，就会唤醒队列中的后继线程。


AQS 的源码中方法很多，但主要做了三件事情：
1.管理同步状态；
2.维护同步队列；
3.阻塞和唤醒线程。

     
    
    
实现：一个int状态位和一个有序队列（链表队列）来配合完成        
    
abstract static class Sync extends AbstractQueuedSynchronizer        
三个重要属性的定义        
private transient volatile Node head;        
private transient volatile Node tail;        
private volatile int state;        
注释其实已经告诉我们了，Node类型的head和tail是一个FIFO的wait queue；一个int类型的状态位state        
AQS对外呈现（或者说声明）的主要行为就是由一个状态位和一个有序队列来配合完成。        
    
    
通过AbstractQueuedSynchronizer的子类Sync来实现，AbstractQueuedSynchronizer是模板方法，子类Sync通过override父类部分方法来实现对应功能        
工具类中自己定义的内部类Sync继承自AQS，通过override部分方法来做到以父类AQS为基础，提供受委托工具类的功能要求        

CLH：Craig、Landin and Hagersten 队列，是一个单向链表，AQS中的队列是CLH变体的虚拟双向队列（FIFO）





1、并发工具类的实现        
CyclicBarrier：wait()        
CountDownLatch：countDown()        
Semaphore(信号量)：acquire()、release()        
都要设置数量，就是state，通过增减state来实现线程的挂起和恢复        
    
在信号量Semaphore中使用AQS的子类Sync，初始化state表示许可数，在每一次请求acquire()一个许可都会导致计数器减少1，同样每次释放一个许可release()都会导致计数器增加1。一旦达到了0，新的许可请求线程将被挂起。        
    
    
    
2、ReentrantLock锁（公平锁/非公平锁）的实现：lock和unlock的数量一致，否则会一直占有锁，发生死锁，增加重入数也会检查是否超过最大值。        
ReentrantLock对外的主要方法是lock()【阻塞】，tryLock()【非阻塞】、unlock()、isLocked()、isFair()等方法        
    
lock方法有对于FairSync和NoFairSync有两种不同的实现：        
1、而公平锁FairSync不能这么做，总是调用acquire方法来和其他线程一样公平的尝试获取锁。        
2、对于非公平锁NoFairSync只要当前没有线程持有锁，就将锁给当前线程；        
比较公平锁机制和非公平锁机制的差别仅仅在于如果当前没有线程持有锁，是优先把锁分配给当前线程，还是优先分配给等待队列中队首的线程。        
    
公平锁FairSync        
通过state是否为0判断，当前是否有线程持有锁，如果没有则把锁分配给当前线程    
否则如果state不为0，说明当前有线程持有锁，则判断持有锁的线程是否就是当前线程，如果是增加state计数，表示持有锁的线程的重入次数增加    
    
非公平锁NoFairSync    
如果state为0表示没有线程持有锁，会检查当前线程是否是等待队列的第一个线程，如果是则分配锁给当前线程    
如果state不为0，说明当前有线程持有锁，则判断持有锁的线程释放就是当前线程，如果是增加state计数，表示持有锁的线程的重入次数增加    
    
    
 ReentrantLock中定义的同步器分为公平的同步器和非公平的同步器。在该同步器中state状态位表示当前持有锁的线程的重入次数。
 在获取锁时，通过覆盖AQS的tryAcquire(int arg)方法，如果没有线程持有则立即返回，并设置state为1；如果当前线程已经占有锁，则state加1；
 如果其他线程占有锁，则当前线程不可用。释放锁时，覆盖了AQS的tryRelease(int arg)，在该方法中主要作用是state状态位减少release个，表示释放锁，如果更新后的state为0，表示当前线程释放锁，如果不为0，表示持有锁的当前线程重入数减少。    
    
    
    
3、ReentrantReadWriteLock可重入读写锁：除了支持公平非公平的Sync外，还有两种不同的锁，ReadLock和WriteLock。    
    
即读和读之间是兼容的，写和任何操作都是排他的，允许多个读线程同时持有锁，但是只有一个写线程可以持有锁    
写线程获取写入锁后可以再次获取读取锁，但是读线程获取读取锁后却不能获取写入锁。    
    
    
 ReentrantReadWriteLock中提供了两个Lock：
 ReentrantReadWriteLock.ReadLock和ReentrantReadWriteLock.WriteLock。
 
 对外提供功能的是两个lock，但是内部封装的是一个同步器Sync，有公平和不公平两个版本。
 借用了AQS的state状态位来保存锁的计数信息。高16位表示共享锁的数量，低16位表示独占锁的重入次数。在AQS子类的对应try字体方法中实现对state的维护。    
 
 读写状态设计
 如果要在一个 int 类型变量上维护多个状态，那肯定就需要拆分了。我们知道 int 类型数据占32位，所以我们就有机会按位切割使用state了。我们将其切割成两部分：
1、高16位表示读
2、低16位表示写


读写锁的升级与降级：
1、读写锁的升级是不可以的（读锁、写锁，释放锁）
2、使用写锁可以降级（写锁、读锁、释放锁）：但是不降级，获取的只是最新的数据，可能部署自己修改后的数据

Reentrancy还允许通过获取写锁定，然后读取锁定然后释放写入锁定，从写入锁定降级到读取锁定。 然而，从读锁定写锁定升级是不可能的。



假如线程A修改完数据之后， 经过耗时操作后想要再使用数据时，希望使用的是自己修改后的数据，而不是其他线程修改后的数据，这样的话确实是需要锁降级；
如果只是希望最后使用数据的时候，拿到的是最新的数据，而不一定是自己刚修改过的数据，那么先释放写锁，再获取读锁，然后使用数据也无妨



参考
https://tech.meituan.com/2018/11/15/java-lock.html
https://zhuanlan.zhihu.com/p/38012123
https://www.cnblogs.com/duanxz/p/4088682.html
https://www.cnblogs.com/liuling/p/2013-8-21-03.html
https://www.apiref.com/java11-zh/java.base/java/util/concurrent/locks/ReentrantReadWriteLock.html




4、FutureTask    
    
state状态位来存储执行状态RUNNING、RUN、CANCELLED，当使用get()获取执行结果的时候，未完成就挂起，在父类AQS中获取共享锁的线程会阻塞。即实现“任务未完成调用get方法的线程会阻塞”这样的功能。    
    
在FutureTask实现了异步的执行和提交，作为可以被Executor提交的对象。通过Sync来维护任务的执行状态，从而保证只有工作线程任务执行完后，其他线程才能获取到执行结果。AQS的子类Sync在这里主要是借用state状态位来存储执行状态，来完成对对各种状态以及加锁、阻塞的实现。    
    


5、ThreadPoolExecutor  (关于线程池的理解，可以查看 为什么要使用线程池? )




参考
https://cloud.tencent.com/developer/article/1624354
https://github.com/doocs/source-code-hunter/blob/master/docs/JDK/concurrentCoding/%E8%AF%A6%E8%A7%A3AbstractQueuedSynchronizer.md




---------------------------------------------------------------------------------------------------------------------


Semaphore 信号量，可用于控制一定时间内，并发执行的线程数，基于 AQS 实现。可应用于网关限流、资源限制 (如 最大可发起连接数)。由于 release() 释放许可时，未对释放许可数做限制，所以可以通过该方法增加总的许可数量。

获取许可支持公平和非公平模式，默认非公平模式。公平模式无论是否有许可，都会先判断是否有线程在排队，如果有线程排队，则进入排队，否则尝试获取许可；非公平模式无论许可是否充足，直接尝试获取许可。







参考  
https://github.com/doocs/source-code-hunter/blob/master/docs/JDK/concurrentCoding/Semaphore.md  












---------------------------------------------------------------------------------------------------------------------

    
