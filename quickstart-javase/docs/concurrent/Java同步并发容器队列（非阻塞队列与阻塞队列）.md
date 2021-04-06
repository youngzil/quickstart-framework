Java同步并发容器队列（非阻塞队列与阻塞队列）



在并发编程中，我们可能经常需要用到线程安全的队列，Java为此提供了两种模式的队列：阻塞队列和非阻塞队列。

注：阻塞队列和非阻塞队列如何实现线程安全？

- 阻塞队列可以用一个锁（入队和出队共享一把锁）或者两个锁（入队使用一把锁，出队使用一把锁）来实现线程安全，JDK中典型的实现是BlockingQueue；
- 非阻塞队列可以用循环CAS的方式来保证数据的一致性，来达到线程安全的目的。




BlockingQueue，顾名思义即是阻塞队列，意指再读取和插入操作情况下可能(注意是可能)会出现阻塞。


在并发编程中，有时候需要使用线程安全的队列。如果要实现一个线程安全的队列有两种方式：一种是使用阻塞算法，另一种是使用非阻塞算法。

1. 使用阻塞算法的队列可以用一个锁（入队和出队用同一把锁）或两个锁（入队和出队用不同的锁）等方式来实现。

2. 非阻塞的实现方式则可以使用循环CAS的方式来实现。





Java包中有一些应用比较广泛的特殊队列：【线程安全的队列】
1. 一种是以ConcurrentLinkedQueue为代表的非阻塞队列； 
2. 另一种是以BlockingQueue接口为代表的阻塞队列。
   
通过这两种队列，我们保证了多线程操作数据的安全性。





Java中的阻塞队列BlockingQueue

什么是阻塞队列

阻塞队列（BlockingQueue）是一个支持两个附加操作的队列。这两个附加的操作支持阻塞的插入和移除方法, 意指再读取和插入操作情况下可能(注意是可能)会出现阻塞。


Java中的阻塞列队介绍
- ArrayBlockingQueue：基于数组的有界阻塞队列，支持配置公平性策略。
- LinkedBlockingQueue：基于链表的无界(默认Integer.MAX_VALUE)阻塞队列，Executors中newFixedThreadPool()和newSingleThreadExecutor()使用的工作队列，所以不推荐使用Executors。
- LinkedBlockingDeque：基于链表的无界(默认Integer.MAX_VALUE)双向阻塞队列
- LinkedTransferQueue：基于链表的无界阻塞队列，该队列提供transfer(e)方法,如果有消费者正在等待则直接把元素给消费者，否者将元素放在队列的tail节点并阻塞到该元素被消费。
- PriorityBlockingQueue：支持优先级排序的无界阻塞队列，默认情况下采用自然顺序升序排序，也可以通过类重写compareTo()方法来指定元素排序规则，或者初始化队列时指定构造参数Comparator来排序。
- DelayQueue：使用PriorityQueue实现的无界延时阻塞队列。
- SynchronousQueue：不存储元素的阻塞队列，每一个put操作必须阻塞到一个take操作发生，否则不能继续添加元素。支持配置公平性策略。





ConcurrentLinkedQueue（非阻塞）

我们一起来研究一下如何使用非阻塞的方式来实现线程安全队列ConcurrentLinkedQueue是一个基于链接节点的无界线程安全队列，它采用先进先出的规则对节点进行排序，当我们添加一个元素的时候，它会添加到队列的尾部；当我们获取一个元素时，它会返回队列头部的元素。

ConcurrentLinkedQueue是一个基于链接节点的无界线程安全队列，它采用先进先出的规则对节点进行排序，当我们添加一个元素的时候，它会添加到队列的尾部；当我们获取一个元素时，它会返回队列头部的元素。


基于锁的算法会带来一些活跃度失败的风险。如果线程在持有锁的时候因为阻塞I/O、页面错误、或其他原因发生延迟，很可能所有的线程都不能工作了。一个线程的失败或挂起不应该影响其他线程的失败或挂起，这样的算法称为非阻塞算法；如果算法的每一个步骤中都有一些线程能够继续执行，那么这样的算法称为锁自由（lock-free）算法。在线程间使用CAS进行协调，这样的算法如果能构建正确的话，它既是非阻塞的，又是锁自由的。java中提供了基于CAS非阻塞算法实现的队列，比较有代表性的有ConcurrentLinkedQueue和LinkedTransferQueue，它们的性能一般比阻塞队列的好。





原子性与可见性分析之synchronized；volatile

原子性

原子是世界上的最小单位，具有不可分割性。比如 a=0；（a非long和double类型） 这个操作是不可分割的，那么我们说这个操作时原子操作。再比如：a++； 这个操作实际是a = a + 1；是可分割的，所以他不是一个原子操作。非原子操作都会存在线程安全问题，需要我们使用同步技术（sychronized）来让它变成一个原子操作。

可见性

可见性，是指线程之间的可见性，一个线程修改的状态对另一个线程是可见的。也就是一个线程修改的结果。另一个线程马上就能看到。比如：用volatile修饰的变量，就会具有可见性。volatile修饰的变量不允许线程内部缓存和重排序，即直接修改内存。所以对其他线程是可见的。


volatile 本质是在告诉jvm当前变量在寄存器中的值是不确定的,需要从主存中读取,

synchronized 则是锁定当前变量,只有当前线程可以访问该变量,其他线程被阻塞住.




[Java中的阻塞队列](https://www.infoq.cn/article/java-blocking-queue)  
[阻塞队列--CPU飙升排查案例](https://juejin.cn/post/6844903892799455245)  
[Java同步并发容器队列（非阻塞队列与阻塞队列）BlockingQueue家族](https://www.huaweicloud.com/articles/a94fde2a430c9180506dfbeb1c2c8fcb.html)  
[java并发之ConcurrentLinkedQueue](https://www.jianshu.com/p/24516e7853d1)  
[JAVA中的阻塞队列和非阻塞队列-简介](https://blog.csdn.net/truelove12358/article/details/106424619)  
[java阻塞队列与非阻塞队列](https://blog.csdn.net/danengbinggan33/article/details/73105838)  
[Java集合--非阻塞队列（ConcurrentLinkedQueue基础）](https://www.jianshu.com/p/799e7156867d)  
[ConcurrentLinkedQueue使用方法](https://blog.csdn.net/liyantianmin/article/details/50585641)  

