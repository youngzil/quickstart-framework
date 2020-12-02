- [Java并发包concurrent](#Java并发包concurrent)
- [Java并发工具类](#Java并发工具类)
- [Unsafe类的CAS操作](#Unsafe类的CAS操作)
- [并发编程中的三个概念：原子性，可见性，有序性](#并发编程中的三个概念：原子性，可见性，有序性)
- [Java线程状态转换和线程并发](#Java线程状态转换和线程并发)
- [并发同步关键字：ThreadLocal、Volatile、synchronized、Atomic](#并发同步关键字：ThreadLocal、Volatile、synchronized、Atomic)
- [JUC包中包含的工具](#JUC包中包含的工具)
- [Java并发队列](#Java并发队列)
- [Java中interrupt的使用](#Java中interrupt的使用)
- [volatile关键字的两层语义](#Volatile关键字的两层语义)
- [Java并发基础AQS类](Java并发基础AQS类.md)
- [Synchronized、ReentrantLock和volatile、CAS的区别](#Synchronized、ReentrantLock和volatile、CAS的区别)


---------------------------------------------------------------------------------------------------------------------
## Java并发包concurrent

package java.util.concurrent
- 1、Atomic原子类型：Long、Integer、Boolean、Refrence等
- 2、并发锁Lock锁：ReentrantLock、ReentrantReadWriteLock等
- 3、线程池：Callable接口、Future接口、FutureTask（ExecutorCompletionService）、Executors、ExecutorService、ThreadPoolExecutor、ThreadFactory、
- 4、并发集合：ConcurrentHashMap、ConcurrentSkipListSet、CopyOnWriteArrayList和CopyOnWriteArraySet等
- 5、并发工具类：CountDownLatch、CyclicBarrier、Semaphore、Exchanger、ForkJoinPool等

从JDK1.5开始Java并发包里提供了两个使用CopyOnWrite机制实现的并发容器,它们是CopyOnWriteArrayList和CopyOnWriteArraySet。




## JUC包中包含的工具

如果按照用途与特性进行粗略的划分，JUC 包中包含的工具大体可以分为 6 类：
- 1、执行者与线程池
- 2、并发队列
- 3、同步工具
- 4、并发集合
- 5、锁
- 6、原子变量



## Java并发队列
Java并发队列按照实现方式来进行划分可以分为2种：
- 1、阻塞队列
- 2、非阻塞队列

如果你已经看完并发系列锁的实现，你已经能够知道他们实现的区别：
    前者就是基于锁实现的，后者则是基于 CAS 非阻塞算法实现的


参考  
[图文讲解爪哇并发编程](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=Mzg3NjIxMjA1Ng==&action=getalbum&album_id=1339969782724214785&subscene=159&subscene=&scenenote=https%3A%2F%2Fmp.weixin.qq.com%2Fs%2FJvdPWIydXnc60esyG4gRyA#wechat_redirect)




## Java并发工具类
并发开始、并发完成、并发控制、线程数据交换

- 并发开始：同步屏障CyclicBarrier  
- 并发结束：等待多线程完成的同步栅栏CountDownLatch  
- 并发控制：控制并发线程数的信号量Semaphore  
- 并发交换：两个线程进行数据交换的Exchanger

CyclicBarrier 与 CountDownLatch 分别对应是等待一个线程还是一组线程完成，  
CyclicBarrier 是一组线程之间相互等待，  
而CountDownLatch 只是一个线程去等待其余线程完成的任务场景  



java.util.concurrent包：
1. 原子类 
2. 锁相关的 
3. 多线程相关的 
4. 线程安全的集合，关于线程安全的集合，参考 [Java 集合框架分析:线程安全的集合](https://blog.csdn.net/youyou1543724847/article/details/52734876)  
5. ThreadLocal 
6. 并发编程（如volatile,原子类，不变类）

1.原子类 [java中的原子操作类AtomicInteger及其实现原理](https://blog.csdn.net/reggergdsg/article/details/51835184)  
有一个atomic子包，其中有几个以Atomic打头的类，例如AtomicInteger和AtomicLong。它们利用了现代处理器的特性，可以用非阻塞的方式完成原子操作。  
get,set方法因为不依赖于当前值，所以直接可以进行操作（有value的volatile保证可见性），对于依赖当前值的操作，则通过unsafe来进行操作


---------------------------------------------------------------------------------------------------------------------
## Unsafe类的CAS操作



CAS（Compare-and-Swap），即比较并替换，是一种实现并发算法时常用到的技术，Java并发包中的很多类都使用了CAS技术。

[Unsafe与CAS](http://www.cnblogs.com/xrq730/p/4976007.html)  
Unsafe类的CAS操作可能是用的最多的，它为Java的锁机制提供了一种新的解决办法，比如AtomicInteger等类都是通过该方法来实现的。compareAndSwap方法是原子的，可以避免繁重的锁机制，提高代码效率。这是一种乐观锁，通常认为在大部分情况下不出现竞态条件，如果操作失败，会不断重试直到成功。

getAndAddInt方法解析：拿到内存位置的最新值v，使用CAS尝试修将内存位置的值修改为目标值v+delta，如果修改失败，则获取该内存位置的新值v，然后继续尝试，直至修改成功。


CAS是什么？
CAS是英文单词CompareAndSwap的缩写，中文意思是：比较并替换。CAS需要有3个操作数：内存地址V，旧的预期值A，即将要更新的目标值B。

CAS指令执行时，当且仅当内存地址V的值与预期值A相等时，将内存地址V的值修改为B，否则就什么都不做。整个比较并替换的操作是一个原子操作。



源码分析：  

上面源码分析时，提到最后调用了compareAndSwapInt方法，接着继续深入探讨该方法，该方法在Unsafe中对应的源码如下。

可以看到调用了“Atomic::cmpxchg”方法，“Atomic::cmpxchg”方法在linux_x86和windows_x86的实现如下。

Atomic::cmpxchg方法解析：

在执行Linux内核中，提供了比较并交换的函数cmpxchg之前，先执行LOCK_IF_MP(mp)

LOCK_IF_MP(mp)会根据mp的值来决定是否为cmpxchg指令添加lock前缀。

如果通过mp判断当前系统是多处理器（即mp值为1），则为cmpxchg指令添加lock前缀。  
否则，不加lock前缀。

这是一种优化手段，认为单处理器的环境没有必要添加lock前缀，只有在多核情况下才会添加lock前缀，因为lock会导致性能下降。cmpxchg是汇编指令，作用是比较并交换操作数。



CAS：比如java中的AtomicInteger的addAndGet方法，会一直做do-while循环，直到操作成功获取并且增加

CAS，Compare and Swap即比较并交换，设计并发算法时常用到的一种技术，java.util.concurrent包全完建立在CAS之上，没有CAS也就没有此包，可见CAS的重要性。

当前的处理器基本都支持CAS，只不过不同的厂家的实现不一样罢了。CAS有三个操作数：内存值V、旧的预期值A、要修改的值B，当且仅当预期值A和内存值V相同时，将内存值修改为B并返回true，否则什么都不做并返回false。

CAS的缺点:不能解决ABA问题，如果需要解决ABA问题，使用传统的互斥同步可能回避原子类更加高效。

这个漏洞称为CAS操作的"ABA"问题。java.util.concurrent包为了解决这个问题，提供了一个带有标记的原子引用类"AtomicStampedReference"，它可以通过控制变量值的版本来保证CAS的正确性。不过目前来说这个类比较"鸡肋"，大部分情况下ABA问题并不会影响程序并发的正确性，因此，在使用CAS前要考虑清楚“ABA”问题是否会影响程序并发的正确性，如果需要解决ABA问题，使用传统的互斥同步可能会比原子类更加高效。



CAS的缺点：
CAS虽然很高效的解决了原子操作问题，但是CAS仍然存在三大问题。
1. 循环时间长开销很大。
2. 只能保证一个共享变量的原子操作。
3. ABA问题。


如何保障线程安全:
1. 使用synchronized关键字，线程内使用同步代码块，由JVM自身的机制来保障线程的安全性。
2. 高并发场景下，使用Lock锁要比使用 synchronized 关键字，在性能上得到极大的提高。因为 Lock 底层是通过 AQS + CAS 机制来实现的。
3. 使用Atomic原子类


参考  
[面试必问的CAS，你懂了吗？](https://zhuanlan.zhihu.com/p/34556594)  
[一文彻底搞懂CAS实现原理](https://zhuanlan.zhihu.com/p/94762520)  
[CAS（Compare And Swap）操作的底层原理以及应用详解](https://blog.nowcoder.net/n/3f413b4af088415baafc159591a1a411)  
[CAS原理深度解析](https://juejin.cn/post/6844903797408399374)  
[原子类的实现（CAS算法）](https://www.cnblogs.com/aspirant/p/7080628.html)  
[Java中的CAS操作和实现原理](https://blog.csdn.net/CringKong/article/details/80533917)  
[深入浅出 CAS](https://blog.biezhi.me/2019/01/head-first-cas.html)  
[Java Concurrent CAS使用&原理](https://cloud.tencent.com/developer/article/1476510)  
[Java CAS详解](https://www.jianshu.com/p/eac466494477)  
[JAVA 中的 CAS](https://www.xilidou.com/2018/02/01/java-cas/)  





[聊聊并发-Java中的Copy-On-Write容器](http://ifeve.com/java-copy-on-write/)  
从JDK1.5开始Java并发包里提供了两个使用CopyOnWrite机制实现的并发容器,它们是CopyOnWriteArrayList和CopyOnWriteArraySet。

CopyOnWrite容器即写时复制的容器。通俗的理解是当我们往一个容器添加元素的时候，不直接往当前容器添加，而是先将当前容器进行Copy，复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器。

这样做的好处是我们可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。

CopyOnWrite并发容器用于读多写少的并发场景。  

CopyOnWrite容器有很多优点，但是同时也存在两个问题，即内存占用问题和数据一致性问题。所以在开发的时候需要注意一下。



J.U.C原子工具类AtomicXXX中，set和lazySet的区别  
[JUC中Atomic class之lazySet的一点疑惑](http://ifeve.com/juc-atomic-class-lazyset-que/)  
[J.U.C原子工具类AtomicXXX中，set和lazySet的区别](https://blog.csdn.net/aitangyong/article/details/41577503)  

set()和volatile具有一样的效果(能够保证内存可见性，能够避免指令重排序)，
但是使用lazySet不能保证其他线程能立刻看到修改后的值(有可能发生指令重排序)。
简单点理解：lazySet比set()具有性能优势，但是使用场景很有限。在网上没有找到lazySet和set的性能数据对比，而且CPU的速度很快的，应用的瓶颈往往不在CPU，而是在IO、网络、数据库等。对于并发程序要优先保证正确性，然后出现性能瓶颈的时候再去解决。因为定位并发导致的问题，往往要比定位性能问题困难很多。




---------------------------------------------------------------------------------------------------------------------
## 并发编程中的三个概念：原子性，可见性，有序性

http://www.cnblogs.com/dolphin0520/p/3920373.html
http://blog.csdn.net/u012465296/article/details/53020676

指令重排序不会影响单个线程的执行，但是会影响到线程并发执行的正确性。
　　也就是说，要想并发程序正确地执行，必须要保证原子性、可见性以及有序性。只要有一个没有被保证，就有可能会导致程序运行不正确。

主存（物理内存）和高速缓存：当程序在运行过程中，会将运算需要的数据从主存复制一份到CPU的高速缓存当中，那么CPU进行计算时就可以直接从它的高速缓存读取数据和向其中写入数据，当运算结束之后，再将高速缓存中的数据刷新到主存当中。
如果一个变量在多个CPU中都存在缓存（一般在多线程编程时才会出现），那么就可能存在缓存不一致的问题。
　　为了解决缓存不一致性问题，通常来说有以下2种解决方法：
　　1）通过在总线加LOCK#锁的方式
　　2）通过缓存一致性协议
　　这2种方式都是硬件层面上提供的方式。
在早期的CPU当中，是通过在总线上加LOCK#锁的形式来解决缓存不一致的问题。由于在锁住总线期间，其他CPU无法访问内存，导致效率低下。
所以就出现了缓存一致性协议。最出名的就是Intel 的MESI协议，MESI协议保证了每个缓存中使用的共享变量的副本是一致的。它核心的思想是：当CPU写数据时，如果发现操作的变量是共享变量，即在其他CPU中也存在该变量的副本，会发出信号通知其他CPU将该变量的缓存行置为无效状态，因此当其他CPU需要读取这个变量时，发现自己缓存中缓存该变量的缓存行是无效的，那么它就会从内存重新读取。

## Volatile关键字的两层语义
　　一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰之后，那么就具备了两层语义：
　　1）可见性：保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
　　2）禁止进行指令重排序。

cpu缓存是集成于cpu中的双极性的高速存储阵列（比内存要快很多），作用是用来加速cpu对高频数据的访问来提高系统性能。
系统缓存一般就是内存，这个作用同cpu缓存很像，是系统对高频是用到的程序预留的空间，避免重复申请空间而浪费时间。



---------------------------------------------------------------------------------------------------------------------

## Java线程状态转换和线程并发

Java线程的5种状态及切换(透彻讲解)
https://www.cnblogs.com/nwnu-daizh/p/8036156.html
http://blog.csdn.net/pange1991/article/details/53860651


新建-->就绪-->运行-->死亡（start(),获取cpu时间片,run/main结束）
运行-->阻塞-->就绪-->运行（sleep、t2.join、等用户输入，3中阻塞条件结束，获取cpu时间片）
运行-->等待队列-->锁池队列-->就绪（wait()+notify/notifyAll、synchronized(obj) ）
运行-->就绪（yield()、时间片用完）

![线程状态转换](../images/threadstatuschange.png "ReferencePicture")

在调用sleep()方法的过程中，线程不会释放对象锁。
而当调用wait()方法的时候，线程会放弃对象锁，让出cpu该其他线程，进入等待此对象的等待锁定池，只有针对此对象调用notify()方法后本线程才进入对象锁定池准备


---------------------------------------------------------------------------------------------------------------------
## 并发同步关键字：ThreadLocal、Volatile、synchronized、Atomic

https://blog.csdn.net/u010687392/article/details/50549236
https://www.cnblogs.com/Mainz/p/3556430.html

- ThreadLocal：线程隔离共享
- Volatile：可见性和有序性，不保证原子性，非CAS、读多写少，也就是简单存取，没有其他操作，因为没有原子性
- Atomic类型：保证原子性和可见性，CAS，写多读多，可以保证原子性，自旋锁
- synchronized：严格锁定同步，效率低




---------------------------------------------------------------------------------------------------------------------
## Java中interrupt的使用

没有任何语言方面的需求一个被中断的线程应该终止。中断一个线程只是为了引起该线程的注意，被中断线程可以决定如何应对中断。



参考
https://www.jianshu.com/p/eb399cc69944




---------------------------------------------------------------------------------------------------------------------
## Synchronized、ReentrantLock和volatile、CAS的区别

synchronized，保证了线程的同步进行。synchronized可以用于函数，也可以用于代码段，synchronized为非公平锁，通过锁实现了代码中的并行计算。

synchronized和ReentrantLock比较：
1. 可重入性：都是可重入锁
2. 锁的实现：Synchronized是依赖于JVM实现的，而ReenTrantLock是JDK实现的
3. 性能的区别：  
   在Synchronized优化以前，synchronized的性能是比ReenTrantLock差很多的，但是自从Synchronized引入了偏向锁，轻量级锁（自旋锁）后，两者的性能就差不多了，在两种方法都可用的情况下，官方甚至建议使用synchronized，其实synchronized的优化我感觉就借鉴了ReenTrantLock中的CAS技术。都是试图在用户态就把加锁问题解决，避免进入内核态的线程阻塞。
4. 功能区别：  
   便利性：很明显Synchronized的使用比较方便简洁，并且由编译器去保证锁的加锁和释放，而ReenTrantLock需要手工声明来加锁和释放锁，为了避免忘记手工释放锁造成死锁，所以最好在finally中声明释放锁。
5. 锁的细粒度和灵活度：很明显ReenTrantLock优于Synchronized


ReenTrantLock独有的能力：
1. ReenTrantLock可以指定是公平锁还是非公平锁。而synchronized只能是非公平锁。所谓的公平锁就是先等待的线程先获得锁。
2. ReenTrantLock提供了一个Condition（条件）类，用来实现分组唤醒需要唤醒的线程们，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程。
3. ReenTrantLock提供了一种能够中断等待锁的线程的机制，通过lock.lockInterruptibly()来实现这个机制。

什么情况下使用ReenTrantLock：  
答案是，如果你需要实现ReenTrantLock的三个独有功能时。


volatile：没有原子性，只有可见性和有序性
不要将volatile用在getAndOperate场合，仅仅set或者get的场景是适合volatile的
原理：内存屏障（Memory Barrier）


CAS的实现AtomicXXX具有原子性和可见性
其实AtomicLong的源码里也用到了volatile，里面的value值就是volatile修饰的，但只是用来读取或写入
原理：使用操作系统的比较并交换的函数cmpxchg，并且在多核情况下才会添加lock前缀，单核不需要加


参考  
[synchronized 和 volatile 、ReentrantLock 、CAS 的区别](https://blog.csdn.net/songzi1228/article/details/99975018)  
[volatile、atomic、reentrantLock、synchronized区别详解](https://blog.csdn.net/wangqiubo2010/article/details/79588527)  
[为什么volatile不能保证原子性而Atomic可以？](https://www.cnblogs.com/mainz/p/3556430.html)  


---------------------------------------------------------------------------------------------------------------------





