- [AQS定义和AQS在几个同步工具类中的使用](#AQS定义和AQS在几个同步工具类中的使用)
    - [AQS主要方法](#AQS主要方法)
    - [AQS实现](#AQS实现)
    - [1、并发工具类的实现](#1、并发工具类的实现)
    - [2、ReentrantLock锁（公平锁/非公平锁）的实现(独享锁、排它锁)](#2、ReentrantLock锁（公平锁/非公平锁）的实现)
    - [3、ReentrantReadWriteLock可重入读写锁（共享锁）](#3、ReentrantReadWriteLock可重入读写锁)
- [信号量Semaphore](#信号量Semaphore)
- [同步栅栏CountDownLatch](#同步栅栏CountDownLatch)
- [同步屏障CyclicBarrier](#同步屏障CyclicBarrier)

---------------------------------------------------------------------------------------------------------------------
## AQS定义和AQS在几个同步工具类中的使用

[源码剖析AQS在几个同步工具类中的使用](http://ifeve.com/abstractqueuedsynchronizer-use/)

AQS(AbstractQueuedSynchronizer)，AQS是JDK下提供的一套用于实现基于FIFO等待队列的阻塞锁和相关的同步器的一个同步框架。  
这个抽象类被设计为作为一些可用原子int值来表示状态的同步器的基类。  
如果你有看过类似 CountDownLatch 类的源码实现，会发现其内部有一个继承了 AbstractQueuedSynchronizer 的内部类 Sync。  
可见 CountDownLatch 是基于AQS框架来实现的一个同步器.类似的同步器在JUC下还有不少。(eg. Semaphore)   

谈到并发，我们不得不说AQS(AbstractQueuedSynchronizer)，所谓的AQS即是抽象的队列式的同步器，内部定义了很多锁相关的方法，我们熟知的ReentrantLock、ReentrantReadWriteLock、CountDownLatch、Semaphore等都是基于AQS来实现的。

AQS 内部维护了一个 FIFO 队列来管理锁。线程首先会尝试获取锁，如果失败，则将当前线程以及等待状态等信息包成一个 Node 节点放入同步队列阻塞起来，当持有锁的线程释放锁时，就会唤醒队列中的后继线程。



### AQS主要方法
AQS的源码中方法很多，但主要做了三件事情：
1.管理同步状态；
2.维护同步队列；
3.阻塞和唤醒线程。

Java并发工具类的三板斧： 状态，队列，CAS




## AQS实现

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





## 1、并发工具类的实现
CyclicBarrier（并发屏障）：wait()   
CountDownLatch（并发栅栏）：countDown()   
Semaphore(信号量)：acquire()、release()  
都要设置数量，就是state，通过增减state来实现线程的挂起和恢复        

在信号量Semaphore中使用AQS的子类Sync，初始化state表示许可数，在每一次请求acquire()一个许可都会导致计数器减少1，同样每次释放一个许可release()都会导致计数器增加1。一旦达到了0，新的许可请求线程将被挂起。 




### 2、ReentrantLock锁（公平锁/非公平锁）的实现

ReentrantLock主要利用CAS+AQS队列来实现。它支持公平锁和非公平锁，两者的实现类似。

ReentrantLock的基本实现可以概括为：先通过CAS尝试获取锁。如果此时已经有线程占据了锁，那就加入AQS队列并且被挂起。当锁被释放之后，排在CLH队列队首的线程会被唤醒，然后CAS再次尝试获取锁。在这个时候，如果：
- 非公平锁：如果同时还有另一个线程进来尝试获取，那么有可能会让这个线程抢先获取；
- 公平锁：如果同时还有另一个线程进来尝试获取，当它发现自己不是在队首的话，就会排到队尾，由队首的线程获取到锁。

如果state为0表示没有线程持有锁,并且会记录当前持有锁定线程
- 非公平锁：当前前程获取锁 或 其他线程也在竞争（可能是队列中等待的，或者刚刚申请的获取锁）
- 公平锁：先判断自己不是在队首的话，就会排到队尾，由队首的线程获取到锁。

如果state != 0,检查当前持有锁的线程跟当前线程比较
- 是当前线程，就state+1,表示持有锁的线程的重入次数增加
- 不是当前线程，就加入队列




lock和unlock的数量一致，否则会一直占有锁，发生死锁，增加重入数也会检查是否超过最大值。        
ReentrantLock对外的主要方法是lock()【阻塞】，tryLock()【非阻塞】、unlock()、isLocked()、isFair()等方法        
    
lock方法有对于FairSync和NoFairSync有两种不同的实现：        
1. 而公平锁FairSync不能这么做，总是调用acquire方法来和其他线程一样公平的尝试获取锁。        
2. 对于非公平锁NoFairSync只要当前没有线程持有锁，就将锁给当前线程；        
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

[ReentrantLock原理](https://blog.csdn.net/fuyuwei2015/article/details/83719444)  
[Lock,ReentrantLock的工作原理及使用方式](https://blog.csdn.net/liuyueyi25/article/details/103369794)  




### 3、ReentrantReadWriteLock可重入读写锁

除了支持公平非公平的Sync外，还有两种不同的锁，ReadLock和WriteLock。    

ReentrantReadWriteLock中提供了两个Lock：  
ReentrantReadWriteLock.ReadLock和ReentrantReadWriteLock.WriteLock。
    
即读和读之间是兼容的，写和任何操作都是排他的，允许多个读线程同时持有锁，但是只有一个写线程可以持有写锁    
写线程获取写入锁后可以再次获取读取锁，但是读线程获取读取锁后却不能获取写入锁。    


对外提供功能的是两个lock，但是内部封装的是一个同步器Sync，有公平和不公平两个版本。  
借用了AQS的state状态位来保存锁的计数信息。高16位表示共享锁的数量，低16位表示独占锁的重入次数。在AQS子类的对应try字体方法中实现对state的维护。    
 
读写状态设计
如果要在一个 int 类型变量上维护多个状态，那肯定就需要拆分了。我们知道 int 类型数据占32位，所以我们就有机会按位切割使用state了。我们将其切割成两部分：
1. 高16位表示读锁状态（读锁个数）
2. 低16位表示写锁状态（写锁个数）


1. 获取写锁的时候，只要有锁，就不能获取，只有等待其他读线程都释放了读锁，写锁才能被当前线程获取，
2. 而写锁一旦被获取，则其他读写线程的后续访问均被阻塞。



读写锁的升级与降级：
1、读写锁的升级是不可以的（读锁、写锁，释放锁）
2、使用写锁可以降级（写锁、读锁、释放锁）：但是不降级，获取的只是最新的数据，可能部署自己修改后的数据

Reentrancy还允许通过获取写锁定，然后读取锁定然后释放写入锁定，从写入锁定降级到读取锁定。 然而，从读锁定写锁定升级是不可能的。



假如线程A修改完数据之后， 经过耗时操作后想要再使用数据时，希望使用的是自己修改后的数据，而不是其他线程修改后的数据，这样的话确实是需要锁降级；
如果只是希望最后使用数据的时候，拿到的是最新的数据，而不一定是自己刚修改过的数据，那么先释放写锁，再获取读锁，然后使用数据也无妨



参考  
[不可不说的Java“锁”事](https://tech.meituan.com/2018/11/15/java-lock.html)
https://zhuanlan.zhihu.com/p/38012123
https://www.cnblogs.com/duanxz/p/4088682.html
https://www.cnblogs.com/liuling/p/2013-8-21-03.html
https://www.apiref.com/java11-zh/java.base/java/util/concurrent/locks/ReentrantReadWriteLock.html



参考
https://cloud.tencent.com/developer/article/1624354
https://github.com/doocs/source-code-hunter/blob/master/docs/JDK/concurrentCoding/%E8%AF%A6%E8%A7%A3AbstractQueuedSynchronizer.md




---------------------------------------------------------------------------------------------------------------------
## 信号量Semaphore

Semaphore 信号量，可用于控制一定时间内，并发执行的线程数，基于 AQS 实现。可应用于网关限流、资源限制 (如 最大可发起连接数)。由于 release() 释放许可时，未对释放许可数做限制，所以可以通过该方法增加总的许可数量。

获取许可支持公平和非公平模式，默认非公平模式。  
NonfairSync和Sync【和ReentrantLock中的不一样，是重写的】  
- 公平模式无论是否有许可，都会先判断是否有线程在排队，如果有线程排队，则进入排队，否则尝试获取许可；
- 非公平模式无论许可是否充足，直接尝试获取许可。



参考  
https://github.com/doocs/source-code-hunter/blob/master/docs/JDK/concurrentCoding/Semaphore.md  



---------------------------------------------------------------------------------------------------------------------
## 同步栅栏CountDownLatch

里面有个Sync继承了AbstractQueuedSynchronizer

初始化的时候设置了state

调用countDown()的时候就会减少state，当state减少到0的时候，就唤醒所有的线程


---------------------------------------------------------------------------------------------------------------------
## 同步屏障CyclicBarrier


里面使用的是ReentrantLock和Condition


CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。

它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。

CyclicBarrier默认的构造方法是CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞。


CyclicBarrier是一个同步屏障

CyclicBarrier让一个线程达到屏障时被阻塞，直到最后一个线程达到屏障时，屏障才会开门，所有被屏障拦截的线程才会继续执行

CyclicBarrier(int parties, Runnable barrierAction)构造函数，用于在所有线程都到达屏障后优先执行barrierAction的run()方法



### CyclicBarrier使用场景
可以用于多线程计算以后，最后使用合并计算结果的场景；



### 实现原理

原理：
- CyclicBarrier中有一个计数器，每当一个线程调用await()方法时计数器就会减1
- 计数器不等于0时，会通过ReentrantLock重入所的condition的await()方法将线程阻塞
- 直到计数器等于0时，会检测是否有barrierAction，如果有则执行barrierAction的run方法，然后唤醒signalAll()所有阻塞线程
- 如果没有barrierAction则直接通过signalAll()唤醒所有阻塞线程


参考  
CyclicBarrierTest0.java  
[同步屏障CyclicBarrier的实现原理](https://www.jianshu.com/p/543d438c67e9)  
[并发工具类（二）同步屏障CyclicBarrier](http://ifeve.com/concurrency-cyclicbarrier/)  
[Java并发（十三）：并发工具类——同步屏障CyclicBarrier](https://www.cnblogs.com/hexinwei1/p/9982420.html)  
[Java并发包5--同步工具CountDownLatch、CyclicBarrier、Semaphore的实现原理解析](https://bbs.huaweicloud.com/blogs/169246)  


---------------------------------------------------------------------------------------------------------------------

