1、锁的分类和锁升级：可升不可降
2、synchronized和lock的实现原理、区别
    synchronized的缺陷：不中断，不并发读，不知道有没有成功获取到锁
    Lock和synchronized对比：可重入，不中断，不公平，lock可中断、可公平，并发读，手动释放，是接口类
3、Condition的signal和Object的notify
    总的来说, Lock + Condition + await()/signal/signalAll ≈ Synchronized + Object.wait()/notify/signalAll
    Condition原理：线程放入等待链表,可以实现“选择性通知”，而notify由JVM随机选择的



sleep和wait区别：sleep不会释放对象锁，wait释放对象锁
线程的状态切换

Java中的读/写锁
https://ifeve.com/read-write-locks/
---------------------------------------------------------------------------------------------------------------------  
锁的分类和锁升级

锁的分类：
1、可重入锁
2.可中断锁
3、公平锁、非公平锁
4.读写锁
5、自旋锁、阻塞锁
6.乐观锁，悲观锁
7.偏向锁


锁的分类：
可重入锁：
可中断锁：synchronized就不是可中断锁，而Lock是可中断锁，可以中断获取锁的过程，
公平锁：synchronized就是非公平锁，它无法保证等待的线程获取锁的顺序，ReentrantLock和ReentrantReadWriteLock，它默认情况下是非公平锁，但是可以设置为公平锁。
读写锁：ReentrantReadWriteLock
自旋锁：一直自旋，过多导致CPU占用高，时间短
互斥锁：竞争激烈，时间长


前面提到了java的4种锁，他们分别是重量级锁、自旋锁、轻量级锁和偏向锁，
重量级锁是悲观锁的一种，
自旋锁、轻量级锁与偏向锁属于乐观锁


锁升级
https://blog.csdn.net/tongdanping/article/details/79647337
https://blog.csdn.net/zqz_zqz/article/details/70233767
https://blog.csdn.net/J080624/article/details/82463399

偏向锁：因此 流程是这样的 偏向锁->轻量级锁->重量级锁


锁的4中状态：无锁状态、偏向锁状态、轻量级锁状态、重量级锁状态（级别从低到高）

*注意：为了避免无用的自旋，轻量级锁一旦膨胀为重量级锁就不会再降级为轻量级锁了；偏向锁升级为轻量级锁也不能再降级为偏向锁。一句话就是锁可以升级不可以降级，但是偏向锁状态可以被重置为无锁状态。

![锁升级](../../quickstart-document/doc/interview/image/lockupgrade.png "ReferencePicture")


自旋锁和互斥锁的区别
https://blog.csdn.net/susidian/article/details/51068858


---------------------------------------------------------------------------------------------------------------------  
synchronized和lock的实现原理、区别

锁相关的
synchronized的缺陷
1、阻塞不可中断：需要有一种机制可以不让等待的线程一直无期限地等待下去（比如只等待一定的时间或者能够响应中断），通过Lock就可以办到。 
2、不能并发读：需要一种机制来使得多个线程都只是进行读操作时，线程之间不会发生冲突，通过Lock就可以办到。 
3、通过Lock可以知道线程有没有成功获取到锁。这个是synchronized无法办到的。

ReentrantLock，意思是“可重入锁”，ReentrantLock是唯一实现了Lock接口的类，
ReadWriteLock也是一个接口，在它里面只定义了两个方法：
一个用来获取读锁，一个用来获取写锁。也就是说将文件的读写操作分开，分成2个锁来分配给线程，从而使得多个线程可以同时进行读操作。

Lock提供了比synchronized更多的功能。但是要注意以下几点： 
1）Lock不是Java语言内置的，synchronized是Java语言的关键字，因此是内置特性。Lock是一个类，通过这个类可以实现同步访问； 
2）Lock和synchronized有一点非常大的不同，采用synchronized不需要用户去手动释放锁，当synchronized方法或者synchronized代码块执行完之后，系统会自动让线程释放对锁的占用；而Lock则必须要用户去手动释放锁，如果没有主动释放锁，就有可能导致出现死锁现象。

synchronized和ReentrantLock都是可重入锁
synchronized就不是可中断锁，而Lock是可中断锁。
synchronized就是非公平锁，它无法保证等待的线程获取锁的顺序。 而对于ReentrantLock和ReentrantReadWriteLock，它默认情况下是非公平锁，但是可以设置为公平锁。 

读写锁：ReadWriteLock就是读写锁，它是一个接口，ReentrantReadWriteLock实现了这个接口。 可以通过readLock()获取读锁，通过writeLock()获取写锁

可重入锁：
机制：每个锁都关联一个请求计数器和一个占有他的线程，当请求计数器为0时，这个锁可以被认为是unhled的，当一个线程请求一个unheld的锁时，JVM记录锁的拥有者，并把锁的请求计数加1，如果同一个线程再次请求这个锁时，请求计数器就会增加，当该线程退出syncronized块时，计数器减1，当计数器为0时，锁被释放。（这就保证了锁是可重入的，不会发生死锁的情况）。


sleep()是线程线程类（Thread）的方法，调用会暂停此线程指定的时间，但监控依然保持，不会释放对象锁，到时间自动恢复；wait()是Object的方法，调用会放弃对象锁，进入等待队列，待调用notify()/notifyAll()唤醒指定的线程或者所有线程，才会进入锁池，再次获得对象锁才会进入运行状态；


synchronized和lock的实现原理、区别
http://blog.csdn.net/u012403290/article/details/64910926?locationNum=11&fps=1
https://www.cnblogs.com/baizhanshi/p/6419268.html
http://blog.csdn.net/tingfeng96/article/details/52219649
http://blog.csdn.net/javazejian/article/details/72828483
http://blog.csdn.net/Luxia_24/article/details/52403033
https://yq.aliyun.com/articles/49051
在Java中，synchronized就是非公平锁，它无法保证等待的线程获取锁的顺序。
而对于ReentrantLock和ReentrantReadWriteLock，它默认情况下是非公平锁，但是可以设置为公平锁。
synchronized和Lock都是可重入锁，Lock默认的是非公平锁，可以设置为公平锁
synchronized是阻塞的，不可中断的，Lock是可中断的锁（超时中断）

也就是说Lock提供了比synchronized更多的功能。但是要注意以下几点：
　　1）Lock不是Java语言内置的，synchronized是Java语言的关键字，因此是内置特性。Lock是一个类，通过这个类可以实现同步访问；
　　2）Lock和synchronized有一点非常大的不同，采用synchronized不需要用户去手动释放锁，当synchronized方法或者synchronized代码块执行完之后，系统会自动让线程释放对锁的占用；而Lock则必须要用户去手动释放锁，如果没有主动释放锁，就有可能导致出现死锁现象。

Lock和synchronized有以下几点不同：
　　1）Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；
　　2）synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
　　3）Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
　　4）通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
　　5）Lock可以提高多个线程进行读操作的效率。
　　在性能上来说，如果竞争资源不激烈，两者的性能是差不多的，而当竞争资源非常激烈时（即有大量线程同时竞争），此时Lock的性能要远远优于synchronized。所以说，在具体使用时要根据适当情况选择。

Lock VS Synchronized
AbstractQueuedSynchronizer通过构造一个基于阻塞的CLH队列容纳所有的阻塞线程，而对该队列的操作均通过Lock-Free（CAS）操作，但对已经获得锁的线程而言，ReentrantLock实现了偏向锁的功能。

synchronized的底层也是一个基于CAS操作的等待队列，但JVM实现的更精细，把等待队列分为ContentionList和EntryList，目的是为了降低线程的出列速度；当然也实现了偏向锁，从数据结构来说二者设计没有本质区别。但synchronized还实现了自旋锁，并针对不同的系统和硬件体系进行了优化，而Lock则完全依靠系统阻塞挂起等待线程。

当然Lock比synchronized更适合在应用层扩展，可以继承AbstractQueuedSynchronizer定义各种实现，比如实现读写锁（ReadWriteLock），公平或不公平锁；同时，Lock对应的Condition也比wait/notify要方便的多、灵活的多。



总结来说，Lock和synchronized有以下几点不同： 
　　1）Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现； 
　　2）synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁； 
　　3）Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断； 
　　4）通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。 
　　5）Lock可以提高多个线程进行读操作的效率。 
　　在性能上来说，如果竞争资源不激烈，两者的性能是差不多的，而当竞争资源非常激烈时（即有大量线程同时竞争），此时Lock的性能要远远优于synchronized。所以说，在具体使用时要根据适当情况选择。 



理论上，当一个线程试图获取一个被锁定的互斥锁时，该操作会失败然后该线程会进入睡眠，这样就能马上让另一个线程运行。当持有互斥锁的线程释放该锁之后，进入睡眠状态的线程就会被唤醒。但是，当一个线程试图获取一个自旋锁而没有成功时，该线程会不断地重试，直到最终成功为止；因此该线程不会将运行权交到其他线程手中（当然，一旦当前线程的时间片超时，操作系统会强行切换到另一个线程）。
互斥锁的问题在于：让线程睡眠和唤醒线程都是极为耗时的操作，完成这些操作需要大量CPU指令，因此也就需要耗费不少时间。如果只是锁定互斥锁很短一段时间，那么让线程睡眠和唤醒线程所花的时间可能会超过线程实际上睡眠的时间，甚至有可能会超过线程在自旋锁上轮询锁浪费的时间（如果使用自旋锁）。另一方面，在自旋锁上进行轮询会浪费CPU时间，如果自旋锁被锁定较长的时间，可能会浪费大量的CPU时间，这时让线程睡眠可能是一个更好的选择。


---------------------------------------------------------------------------------------------------------------------  
Condition的signal和Object的notify


总的来说, Lock + Condition + await()/signal/signalAll ≈ Synchronized + Object.wait()/notify/notifyAll

Condition中的await()方法相当于Object的wait()方法，Condition中的signal()方法相当于Object的notify()方法，Condition中的signalAll()相当于Object的notifyAll()方法. 
不同的是, 同一个锁可以有多个 Condition, 提供多种情况的互不干扰的控制.

内部实现
ConditionObject 中使用Node类实现了一个链表, 用来缓存所有的等待线程.
每次执行await()等待时, 都会在addConditionWaiter()方法里使用当前await()所在的线程构建一个node对象, 并插入到等待链表中;
执行signal()时又会在transferForSignal()方法中从链表中移除顶部node, 并把该node内的thread从阻塞队列中转移到可运行队列中.



Condition实现等待与通知
关键字synchronized与wait()和notify()/notifyAll()方法相结合可以实现等待/通知模式。
ReentrantLock结合Condition可以实现前面介绍过的“选择性通知”,这个功能是非常重要的。 


关键字synchronized与wait()和notify()/notifyAll()方法相结合可以实现等待/通知模式。 
类ReentrantLock同样可以实现该功能，但是要借助于Condition对象。它具有更好的灵活性，比如可以实现多路通知功能，也就是在一个Lock对象里面可以创建多个Condition(对象监视器)实例，线程对象可以注册在指定Condition中，从而有选择性的进行线程通知，在调度线程上更加灵活 
使用notify和notifyAll方法进行通知时，被通知的线程是由JVM随机选择的，但是ReentrantLock结合Condition可以实现前面介绍过的“选择性通知”,这个功能是非常重要的。 
synchronized相当于整个Lock对象中只有单一的Condition对象，所有线程都注册在它一个对象上，线程开始notifyAll（）时，需要通知所有waiting的线程，没有选择权，会出现相当大的效率问题

关键字synchronized与wait()和notify()/notifyAll()方法相结合可以实现等待/通知模式。
ReentrantLock结合Condition可以实现前面介绍过的“选择性通知”,这个功能是非常重要的。 
https://blog.csdn.net/chenchaofuck1/article/details/51592429
https://blog.csdn.net/zhang199416/article/details/70771238



参考
https://tech.meituan.com/2018/11/15/java-lock.html


---------------------------------------------------------------------------------------------------------------------  

