java并发包concurrent和并发工具类


java并发包concurrent：
1、Atomic原子类型：Long、Integer、Boolean、Refrence等
2、并发锁Lock锁：ReentrantLock、ReentrantReadWriteLock等
3、线程池：Callable接口、Future接口、FutureTask（ExecutorCompletionService）、Executors、ExecutorService、ThreadPoolExecutor、ThreadFactory、
4、并发集合：ConcurrentHashMap、ConcurrentSkipListSet、CopyOnWriteArrayList和CopyOnWriteArraySet等
5、并发工具类：CountDownLatch、CyclicBarrier、Semaphore、Exchanger、ForkJoinPool等
Java并发工具类：
并发开始：同步屏障CyclicBarrier
并发结束：等待多线程完成的CountDownLatch
并发控制：控制并发线程数的信号量Semaphore
并发交换：两个线程进行数据交换的Exchanger
6、JUC 包中包含的工具
7、Java中interrupt的使用




并发类，如ThreadLocal 、volatile
并发编程中的三个概念：原子性，可见性，有序性，
和volatile关键字的两层语义
java线程状态转换和线程并发
Unsafe类的CAS操作
java并发基础AQS类.md



---------------------------------------------------------------------------------------------------------------------
package java.util.concurrent
1、Atomic原子类型：Long、Integer、Boolean、Refrence等
2、并发锁Lock锁：ReentrantLock、ReentrantReadWriteLock等
3、线程池：Callable接口、Future接口、FutureTask（ExecutorCompletionService）、Executors、ExecutorService、ThreadPoolExecutor、ThreadFactory、
4、并发集合：ConcurrentHashMap、ConcurrentSkipListSet、CopyOnWriteArrayList和CopyOnWriteArraySet等
5、并发工具类：CountDownLatch、CyclicBarrier、Semaphore、Exchanger、ForkJoinPool等

从JDK1.5开始Java并发包里提供了两个使用CopyOnWrite机制实现的并发容器,它们是CopyOnWriteArrayList和CopyOnWriteArraySet。


Java并发工具类：
并发开始、并发完成、、并发控制、线程数据交换

并发开始：同步屏障CyclicBarrier
并发结束：等待多线程完成的CountDownLatch
并发控制：控制并发线程数的信号量Semaphore
并发交换：两个线程进行数据交换的Exchanger

CyclicBarrier 与 CountDownLatch 分别对应是等待一个线程还是一组线程完成，
CyclicBarrier 是一组线程之间相互等待，
而CountDownLatch 只是一个线程去等待其余线程完成的任务场景



java.util.concurrent包：
http://blog.csdn.net/youyou1543724847/article/details/52735510

1.原子类 
2.锁相关的 
3.多线程相关的 
4.线程安全的集合，关于线程安全的集合，参考 http://blog.csdn.net/youyou1543724847/article/details/52734876 
5.ThreadLocal 
6.并发编程（如volatile,原子类，不变类）

1.原子类 http://blog.csdn.net/reggergdsg/article/details/51835184
有一个atomic子包，其中有几个以Atomic打头的类，例如AtomicInteger和AtomicLong。它们利用了现代处理器的特性，可以用非阻塞的方式完成原子操作。
get,set方法因为不依赖于当前值，所以直接可以进行操作（有value的volatile保证可见性），对于依赖当前值的操作，则通过unsafe来进行操作


---------------------------------------------------------------------------------------------------------------------
Unsafe类的CAS操作


http://www.cnblogs.com/xrq730/p/4976007.html
Unsafe类的CAS操作可能是用的最多的，它为Java的锁机制提供了一种新的解决办法，比如AtomicInteger等类都是通过该方法来实现的。compareAndSwap方法是原子的，可以避免繁重的锁机制，提高代码效率。这是一种乐观锁，通常认为在大部分情况下不出现竞态条件，如果操作失败，会不断重试直到成功。

CAS：比如java中的AtomicInteger的addAndGet方法，会一直做do-while循环，直到操作成功获取并且增加
CAS，Compare and Swap即比较并交换，设计并发算法时常用到的一种技术，java.util.concurrent包全完建立在CAS之上，没有CAS也就没有此包，可见CAS的重要性。
当前的处理器基本都支持CAS，只不过不同的厂家的实现不一样罢了。CAS有三个操作数：内存值V、旧的预期值A、要修改的值B，当且仅当预期值A和内存值V相同时，将内存值修改为B并返回true，否则什么都不做并返回false。

CAS的缺点:不能解决ABA问题，如果需要解决ABA问题，使用传统的互斥同步可能回避原子类更加高效。
这个漏洞称为CAS操作的"ABA"问题。java.util.concurrent包为了解决这个问题，提供了一个带有标记的原子引用类"AtomicStampedReference"，它可以通过控制变量值的版本来保证CAS的正确性。不过目前来说这个类比较"鸡肋"，大部分情况下ABA问题并不会影响程序并发的正确性，如果需要解决ABA问题，使用传统的互斥同步可能回避原子类更加高效。


http://ifeve.com/java-copy-on-write/http://ifeve.com/java-copy-on-write/
从JDK1.5开始Java并发包里提供了两个使用CopyOnWrite机制实现的并发容器,它们是CopyOnWriteArrayList和CopyOnWriteArraySet。
CopyOnWrite容器即写时复制的容器。通俗的理解是当我们往一个容器添加元素的时候，不直接往当前容器添加，而是先将当前容器进行Copy，复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器。这样做的好处是我们可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
CopyOnWrite并发容器用于读多写少的并发场景。
CopyOnWrite容器有很多优点，但是同时也存在两个问题，即内存占用问题和数据一致性问题。所以在开发的时候需要注意一下。



J.U.C原子工具类AtomicXXX中，set和lazySet的区别
http://ifeve.com/juc-atomic-class-lazyset-que/
http://www.dengshenyu.com/java/2017/04/12/java-concurrency.html

set()和volatile具有一样的效果(能够保证内存可见性，能够避免指令重排序)，
但是使用lazySet不能保证其他线程能立刻看到修改后的值(有可能发生指令重排序)。
简单点理解：lazySet比set()具有性能优势，但是使用场景很有限。在网上没有找到lazySet和set的性能数据对比，而且CPU的速度很快的，应用的瓶颈往往不在CPU，而是在IO、网络、数据库等。对于并发程序要优先保证正确性，然后出现性能瓶颈的时候再去解决。因为定位并发导致的问题，往往要比定位性能问题困难很多。


————————————————
版权声明：本文为CSDN博主「aitangyong」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/aitangyong/article/details/41577503


---------------------------------------------------------------------------------------------------------------------


http://www.cnblogs.com/dolphin0520/p/3920373.html
http://blog.csdn.net/u012465296/article/details/53020676
并发编程中的三个概念：原子性，可见性，有序性
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

volatile关键字的两层语义
　　一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰之后，那么就具备了两层语义：
　　1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
　　2）禁止进行指令重排序。

cpu缓存是集成于cpu中的双极性的高速存储阵列（比内存要快很多），作用是用来加速cpu对高频数据的访问来提高系统性能。
系统缓存一般就是内存，这个作用同cpu缓存很像，是系统对高频是用到的程序预留的空间，避免重复申请空间而浪费时间。



---------------------------------------------------------------------------------------------------------------------

java线程状态转换和线程并发

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
并发同步关键字：ThreadLocal、Volatile、synchronized、Atomic

https://blog.csdn.net/u010687392/article/details/50549236
https://www.cnblogs.com/Mainz/p/3556430.html

ThreadLocal：线程隔离共享
Volatile：可见性和有序性，不保证原子性，非CAS、读多写少，也就是简单存取，没有其他操作，因为没有原子性
Atomic类型：保证原子性和可见性，CAS，写多读多，可以保证原子性，自旋锁
synchronized：严格锁定同步，效率低



---------------------------------------------------------------------------------------------------------------------
JUC 包中包含的工具


如果按照用途与特性进行粗略的划分，JUC 包中包含的工具大体可以分为 6 类：
1、执行者与线程池
2、并发队列
3、同步工具
4、并发集合
5、锁
6、原子变量





并发队列

Java 并发队列按照实现方式来进行划分可以分为 2 种：
1、阻塞队列
2、非阻塞队列

如果你已经看完并发系列锁的实现，你已经能够知道他们实现的区别：
    前者就是基于锁实现的，后者则是基于 CAS 非阻塞算法实现的



参考
https://mp.weixin.qq.com/mp/appmsgalbum?__biz=Mzg3NjIxMjA1Ng==&action=getalbum&album_id=1339969782724214785&subscene=159&subscene=&scenenote=https%3A%2F%2Fmp.weixin.qq.com%2Fs%2FJvdPWIydXnc60esyG4gRyA#wechat_redirect





---------------------------------------------------------------------------------------------------------------------
Java中interrupt的使用

没有任何语言方面的需求一个被中断的线程应该终止。中断一个线程只是为了引起该线程的注意，被中断线程可以决定如何应对中断。



参考
https://www.jianshu.com/p/eb399cc69944




---------------------------------------------------------------------------------------------------------------------





