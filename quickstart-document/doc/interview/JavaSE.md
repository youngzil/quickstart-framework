参考项目
java示例quickstart-example
java基础quickstart-javase

1、Java基础：概述、语言基础、OO、Exceptio处理、Array、常用Class、集合、IO与文件、多线程、并发、反射

2、Java的简单类型及其封装器类

3、Object类的方法，和Thread类方法的区别

4、String StringBuffer,StringBuilder原理：底层的数据结构是 字符数组 char[]

5、sleep、wait  notyfi都干啥的，sycnized怎么用的，concurrent包下面的锁用过哪些，怎么实现的

6、io  jvm  多线程1

7、图的遍历，深度广度啊

8、内存溢出，内存泄露，，怎么调优，类加载

9、字节流和字符流
  BIO和NIO
  集合
  多线程：
  并发工具类
  SPI
  JDK、JDK7、JDK8
  java agent
  零拷贝：减少内核态和用户态时的数据重复拷贝，java.nio.channel.FileChannel的transferTo()，transferFrom()方法
  内存映射文件：mmap()方法
  java热部署、模块化：jarslink
  泛型：泛型上限通配符extends与泛型下限通配符super
  反射初始化
  环形缓冲区的实现原理（ring buffer）quickstart-disruptor
  java示例：quickstart-example
  java基础quickstart-javase
  java锁
  jvm学习.md


10、BloomFilter 与 CuckooFilter

11、计算机内存模型和CPU缓存一致性协议MESI
   缓存伪共享
   零拷贝：transTo

12、CPU缓存分类：CPU三级缓存：CPU、L1、L2、L3、主内存
   缓存一致性协议：MESI
   伪共享(False Sharing)：缓存行进行字节填充，保证头尾加载到不同的缓存行，避免出入对互斥，不能并发，成为伪共享。jdk8中Contended注解
   缓存系统中是以缓存行（cache line）为单位存储的：现在64byte，早期32byte，范围32-128byte
   缓存段竞争：导致总线风暴
   volatile字段：使用内存屏障，保证可见性和有序性，不保证原子性

13、BIO是面向流、阻塞IO，顺序读
   NIO面向缓冲、非阻塞IO、选择器Selector，可以使用position等跳跃读
   
   Channels：4种
   Buffers：1+2+3+1
   Selectors：4个事件
   Buffer分配：3种
   方法：常用的读写切换、定位等
   buffer读写方法
   Buffer的capacity,position和limit
   
   直接内存：不受young gc的影响，只有full gc的时候回收，当众多的DirectByteBuffer对象从新生代被送入老年代后触发了 full gc才会会释放回收，MappedByteBuffer在处理大文件时的确性能很高，但也存在一些问题，如内存占用、文件关闭不确定，被其打开的文件只有在垃圾回收的才会被关闭，而且这个时间点是不确定的。
   
   
   
   网络：
   SocketChannel:创建连接，读写数据，从channel到buffer，从buffer到channel
   ServerSocketChannel:监听连接，默认是阻塞模式，可以设置为非阻塞模式（while循环）
   
   
   零拷贝( zero-copy )
   文件IO：通过mmap实现的零拷贝I/O
   网络IO：FileChannel.transferTo 和 FileChannel.transferFrom方法


14、
   ThreadPoolExecutor或者Executors工具类（4种）来创建
   参数：7个
   创建线程流程和销毁线程流程
   
   提交任务：execute() 和 submit()
   execute()：FutureTask（ExecutorCompletionService）
   submit()：Callable接口、Future接口
   
   RejectedExecutionHandler策略
   其他方法：beforeExecute、afterExecute、
   
   ThreadLocal使用：以线程为单位进行隔离，因为WeakReference不会导致内存泄漏，线程复用的时候没有remove可能会导致后面的任务取到前面任务存进去的值，导致程序出错


15、synchronized的缺陷：不中断，不并发读，不知道有没有成功获取到锁
   Lock和synchronized对比：可重入，不中断，不公平，lock可中断、可公平，并发读，手动释放，是接口类
   
   锁的分类和升级：可升不可降
   sleep和wait区别：sleep不会释放对象锁，wait释放对象锁
   线程的状态切换
   
   总的来说, Lock + Condition + await()/signal/signalAll ≈ Synchronized + Object.wait()/notify/signalAll
   Condition原理：线程放入等待链表,可以实现“选择性通知”，而notify由JVM随机选择的

16、分布式锁实现.md：数据库、redis、zookeeper

17、加解密.md：加解密的分类和实现

18、单例模式有三种实现方式

19、图片加载缓慢优化.md：

20、泛型通配符extends与super的区别.md

21、负载均衡.md:负载均衡算法

22、

23、

24、

25、








本身都是通过字符数组来存储，对象内部定义字符数组
String：new是放在堆区，+或者substring都是通过改变字符数组生成新的字符数组来实现
一个是非同步的StringBuilder，一个是同步的StringBuffer（synchronized在方法上），都是字符数组，
append时先扩容，把字符数组拷贝到一个新的大的字符数组，再进行拼接，还是拼接拷贝到一个新的字符数组，






---------------------------------------------------------------------------------------------------------------------

Java NIO：
BIO是面向流、阻塞IO
NIO面向缓冲、非阻塞IO、选择器Selector

标准的IO基于字节流和字符流进行操作的
NIO是基于通道（Channel）和缓冲区（Buffer）进行操作，数据总是从通道读取到缓冲区中，或者从缓冲区写入到通道中。    


文件IO、网络IO、

Linux文件IO和文件标准IO
http://blog.csdn.net/yzhang6_10/article/details/53142733
https://www.cnblogs.com/xiaojiang1025/p/5933755.html
对UNIX系统来说，可用的文件I/O函数主要有：打开文件、读文件、写文件等。涉及到的函数主要有：open、read、write、lseek、close，其中不带缓冲指每个read和write都调用内核中的一个系统调用。涉及多个进程间共享资源时，原子操作就非常重要。

文件描述符（fd，File descriptor）：对内核而言，所有打开的文件都通过文件描述符引用。文件描述符是一个非负整数。
在符合POSIX.1的应用程序中，幻数0、1、2虽然已被标准化，但应当把它们替换成富豪常量STDIN_FILENO、STDOUT_FILENO和STDERR_FILENO以提高可读性。
标准I/O预定义3个流，他们可以自动地为进程所使用：标准输入/标准输出/标准错误输出。

函数lseek：每个打开文件都有一个与其相关联的“当前文件偏移量”。它通常是一个非负整数，用以度量从文件开始处计算的字节数。
文件偏移量可以大于文件的当前长度，在这种情况下，对该文件的下一次写加长该文件，并在文件中构成一个空洞，这一点是允许的。位于文件中但没有写过的字节都被读为0。
文件中的空洞并不要求在磁盘上占用存储区。具体处理方式与文件系统的实现有关，当定位到超过文件尾端之后写时，对于新写的数据需要分配磁盘块，但是对于原文件尾端和新开始写位置之间的部分则不需要分配磁盘块。


I/O的效率
大多数文件系统为改善性能都采用某种预读（read ahead）技术。当检测到正进行顺序读取时，系统就试图读入比应用所要求的更多数据，并假想应用很快就会读这些数据。
操作系统试图用告诉缓存技术将相关文件放置在主存中，所以如若重复度量程序性能，那么后续运行该程序所得到的计时很可能好于第一次。原因是，第一次运行使得文件进入系统高速缓存，后续各次运行一般从系统高速缓存访问文件，无需读、写磁盘。
Cache的预读与换出，缺页时换出

两个进程同时对同一文件进行追加写操作，可能因为“先定位到文件尾端，然后写”单只出现某一进程的写被覆盖，从而出现错误，其解决办法是使这两个操作对于其他进程而言成为一个原子操作。任何要求多于一个函数调用的操作都不是原子操作，因为在两个函数调用之间，内核有可能会临时挂起进程。
原子操作要放在一起对其他进程而言是原子的，因为在两个函数调用之间，内核有可能会临时挂起进程。
多个进程对同一文件进行追加写操作和创建同一个文件时，为防止出错，需满足原子操作。

函数sync、fsync和fdatasync
传统UNIX系统实现在内核中设有缓冲区高速缓存或页面高速缓存，大多数磁盘I/O都通过缓冲区进行。当我们向文件写入数据时，内核通过先将数据复制到缓冲区中，然后排入队列，晚些时候在写入磁盘。这种方式被称为延迟写。
通常内核需要重用缓冲区来存放其他磁盘块数据时，它会把所有延迟写数据块写入磁盘。为保证磁盘上世纪文件系统与缓冲区中内容一致，UNIX系统提供了sync、fsync和fdatasync三个函数。
sync只是将所有修改过的块缓冲区排入写队列，然后就返回，它并不等待实际写磁盘操作结束。
**fsync函数只对由文件描述符fd指定的一个文件起作用，并且等待写磁盘操作结束才返回。**fsync可用于数据库这样的应用程序，这种应用程序需要确保修改过的块立即写到磁盘上。
fdatasync函数类似于fsync，但它只影响文件的数据部分。而除数据外，fsync换回同步更新文件的属性。

标准I/O与文件I/O：
http://blog.csdn.net/myintelex/article/details/53888324
I/O模型	文件I/O	标准I/O
缓冲方式	非缓冲I/O	缓冲I/O
操作对象	文件描述符	流(FILE )
打开	open()	fopen()/freopen()/fdopen()
读	read()	fread()/fgetc()/fgets()…
写	write()	fwrite()/fputc()/fputs()…
定位	lseek()	fseek()/ftell()/rewind()/fsetpos()/fgetpos()
关闭	close()	fclose()

网络IO：
http://blog.csdn.net/qq_30154277/article/details/51981821
网络HttpURLConnection 类
java的BIO类InputStreamReader、等


Linux 网络 I/O 模型简介：
http://blog.csdn.net/anxpp/article/details/51503329
 Linux 的内核将所有外部设备都看做一个文件来操作（一切皆文件），对一个文件的读写操作会调用内核提供的系统命令，返回一个file descriptor（fd，文件描述符）。而对一个socket的读写也会有响应的描述符，称为socket fd（socket文件描述符），描述符就是一个数字，指向内核中的一个结构体（文件路径，数据区等一些属性）。

    根据UNIX网络编程对I/O模型的分类，UNIX提供了5种I/O模型。
1、阻塞I/O模型
2、非阻塞I/O模型
3、I/O复用模型
Linux提供select/poll，进程通过将一个或多个fd传递给select或poll系统调用，阻塞在select操作上，这样，select/poll可以帮我们侦测多个fd是否处于就绪状态。
    select/poll是顺序扫描fd是否就绪，而且支持的fd数量有限，因此它的使用受到了一些制约。
    Linux还提供一个epoll系统调用，epoll使用基于事件驱动方式代替顺序扫描，因此性能更高。当有fd就绪时，立即回调函数rollback。数量没有限制
4、信号驱动I/O模型
5、异步I/O
告知内核启动某个操作，并让内核在整个操作完成后（包括数据的复制）通知进程。
    信号驱动I/O模型通知的是何时可以开始一个I/O操作，异步I/O模型有内核通知I/O操作何时已经完成。


I/O多路复用技术：
I/O编程中，需要处理多个客户端接入请求时，可以利用多线程或者I/O多路复用技术进行处理。
    正如前面的简介，I/O多路复用技术通过把多个I/O的阻塞复用到同一个select的阻塞上，从而使得系统在单线程的情况下可以同时处理多个客户端请求。
    与传统的多线程模型相比，I/O多路复用的最大优势就是系统开销小，系统不需要创建新的额外线程，也不需要维护这些线程的运行，降低了系统的维护工作量，节省了系统资源。

    主要的应用场景：
    服务器需要同时处理多个处于监听状态或多个连接状态的套接字。
    服务器需要同时处理多种网络协议的套接字。

支持I/O多路复用的系统调用主要有select、pselect、poll、epoll。

    而当前推荐使用的是epoll，优势如下：
    支持一个进程打开的socket fd不受限制。
    I/O效率不会随着fd数目的增加而线性下将。
    使用mmap加速内核与用户空间的数据传递。
    epoll拥有更加简单的API。

I/O模型：同步I/O和异步I/O，阻塞I/O和非阻塞I/O
http://blog.csdn.net/shenlei19911210/article/details/49305413
blocking IO的特点就是在IO执行的两个阶段（等待数据准备就绪和拷贝数据两个阶段）都被block了。
在非阻塞式IO中，用户进程其实是需要不断的主动询问kernel数据准备好了没有。
在多路复用模型中，对于每一个socket，一般都设置成为non-blocking，但是，如上图所示，整个用户的process其实是一直被block的。只不过process是被select这个函数block，而不是被socket IO给block。因此select()与非阻塞IO类似。
异步IO是真正非阻塞的，它不会对请求进程产生任何的阻塞，因此对高并发的网络服务器实现至关重要。

异步IO和非阻塞IO的区别！！！
异步IO就是把IO操作提交给系统，让系统帮我们完成相关操作，操作完成后系统在以某种方式通知我们操作已经完成。非阻塞IO就是我们要通过某种不定时方式向系统询问我们能够开始执行某个IO操作，当得到许可指令后，具体的操作还是需要我们自己动手来完成的！


---------------------------------------------------------------------------------------------------------------------
一致性哈希算法(consistent hashing)
http://blog.csdn.net/cywosp/article/details/23397179/
https://blog.csdn.net/bntX2jSQfEHy7/article/details/79549368
https://blog.csdn.net/thinkmo/article/details/26833565
一致性hash算法
1、使用的MurMurHash算法，是非加密HASH算法，性能很高， 比传统的CRC32,MD5，SHA-1（这两个算法都是加密HASH算法，复杂度本身就很高，带来的性能上的损害也不可避免） 等HASH算法要快很多，而且据说这个算法的碰撞率很低. http://murmurhash.googlepages.com/
2、使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别 

一致性hash算法提出了在动态变化的Cache环境中，判定哈希算法好坏的四个定义：
1、平衡性(Balance)：平衡性是指哈希的结果能够尽可能分布到所有的缓冲中去，这样可以使得所有的缓冲空间都得到利用。很多哈希算法都能够满足这一条件。
2、单调性(Monotonicity)：单调性是指如果已经有一些内容通过哈希分派到了相应的缓冲中，又有新的缓冲加入到系统中。哈希的结果应能够保证原有已分配的内容可以被映射到原有的或者新的缓冲中去，而不会被映射到旧的缓冲集合中的其他缓冲区。 
3、分散性(Spread)：在分布式环境中，终端有可能看不到所有的缓冲，而是只能看到其中的一部分。当终端希望通过哈希过程将内容映射到缓冲上时，由于不同终端所见的缓冲范围有可能不同，从而导致哈希的结果不一致，最终的结果是相同的内容被不同的终端映射到不同的缓冲区中。这种情况显然是应该避免的，因为它导致相同内容被存储到不同缓冲中去，降低了系统存储的效率。分散性的定义就是上述情况发生的严重程度。好的哈希算法应能够尽量避免不一致的情况发生，也就是尽量降低分散性。 
4、负载(Load)：负载问题实际上是从另一个角度看待分散性问题。既然不同的终端可能将相同的内容映射到不同的缓冲区中，那么对于一个特定的缓冲区而言，也可能被不同的用户映射为不同 的内容。与分散性一样，这种情况也是应当避免的，因此好的哈希算法应能够尽量降低缓冲的负荷。

把机器和对象使用相同的hash均衡分散到hash环上，对象顺时针放在距离自己最近的机器节点上
平衡性：使用虚拟节点来保证，新增或者删除机器节点，对象平衡分散被影响，并且之后的对象依然平衡分散
单调性：新增或者删除机器节点，只是影响相邻的部分对象，但按照顺时候移动，可能出现不平衡问题（使用虚节点）
分散性：节点hash后分散
---------------------------------------------------------------------------------------------------------------------
Object类的方法
getClass
hashcode
equal
clone()
toString
wait
notify
notifyAll
finalize()


Thread类：start、run、sleep、stop
Object类：wait、notify、notifyAll，这三个方法都依赖锁对象，所对象可以是任意对象，所以定义在Object中

start()和run()的区别
start():启动线程并调用线程中的run()方法。
run():执行该线程对象要执行的任务

sleep()和wait()的区别
sleep():不释放锁对象，释放CPU使用权在休眠的时间内，不能唤醒。
wait():释放锁对象，释放CPU释放权，在等待时间内能被唤醒。

---------------------------------------------------------------------------------------------------------------------
一个英文字母(不分大小写)或者符号占一个字节的空间，一个中文汉字或者符号占两个字节的空间． 
字节(Byte):通常将可表示常用英文字符8位二进制称为一字节。 1字节(Byte） = 8位(bit) 
---------------------------------------------------------------------------------------------------------------------
java常见异常
http://blog.csdn.net/qq635785620/article/details/7781026
NullPointerException - 空指针引用异常
ClassCastException - 类型强制转换异常。
IllegalArgumentException - 传递非法参数异常。
ArithmeticException - 算术运算异常
ArrayStoreException - 向数组中存放与声明类型不兼容对象异常
IndexOutOfBoundsException - 下标越界异常
NegativeArraySizeException - 创建一个大小为负数的数组错误异常
NumberFormatException - 数字格式异常
SecurityException - 安全异常
UnsupportedOperationException - 不支持的操作异常

RuntimeException
当应用程序试图在需要对象的地方使用 null 时，抛出NullPointerException异常
当试图将对象强制转换为不是实例的子类时，抛出该异常（ClassCastException)
指示索引或者为负，或者超出字符串的大小，抛出StringIndexOutOfBoundsException异常
---------------------------------------------------------------------------------------------------------------------
Comparable与Comparator的区别
http://www.importnew.com/17434.html
Comparable：bean实现该接口，//Collections.sort(list)（Arrays.sort）; 两种方式都可以，此种方式源码中就是调用的list.sort(null)
Comparator：bean不需要实现该接口，直接当做实现传递给Collections.sort(list)（Arrays.sort）
Collections.sort(list, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getSalary() - o2.getSalary();
            }
        });

一、Comparator 
强行对某个对象collection进行整体排序的比较函数，可以将Comparator传递给Collections.sort或Arrays.sort。 
接口方法： int compare(Object o1, Object o2);  

二、Comparable 
强行对实现它的每个类的对象进行整体排序，实现此接口的对象列表（和数组）可以通过Collections.sort或Arrays.sort进行自动排序。 
接口方法： int compareTo(Object o);  
Comparable的用法
一般来说，Comparable是为了对某个类的集合进行排序，所以此时一般都是这个需要排序的类本身去实现Comparable接口。换句话说，如果某个类实现了Comparable接口，那么这个类的数组或者说List就可以进行排序了。

一个类实现了Camparable接口则表明这个类的对象之间是可以相互比较的，这个类对象组成的集合就可以直接使用sort方法排序。 
Comparator可以看成一种算法的实现，将算法和数据分离，Comparator也可以在下面两种环境下使用： 
1、类的设计师没有考虑到比较问题而没有实现Comparable，可以通过Comparator来实现排序而不必改变对象本身 
2、可以使用多种排序标准，比如升序、降序等 

Comparable & Comparator 都是用来实现集合中元素的比较、排序的，只是 Comparable 是在集合内部定义的方法实现的排序，
Comparator 是在集合外部实现的排序，所以，如想实现排序，就需要在集合外定义 Comparator 接口的方法或在集合内实现 Comparable 接口的方法。
---------------------------------------------------------------------------------------------------------------------
对Runtime的了解:Runtime类
Runtime:运行时，是一个封装了JVM的类。每一个JAVA程序实际上都是启动了一个JVM进程，每一个JVM进程都对应一个Runtime实例，此实例是由JVM为其实例化的。所以我们不能实例化一个Runtime对象，应用程序也不能创建自己的 Runtime 类实例，但可以通过 getRuntime 方法获取当前Runtime运行时对象的引用。一旦得到了一个当前的Runtime对象的引用，就可以调用Runtime对象的方法去控制Java虚拟机的状态和行为。
查看官方文档可以看到，Runtime类中没有构造方法，本类的构造方法被私有化了， 所以才会有getRuntime方法返回本来的实例化对象，这与单例设计模式不谋而合
public static Runtime getRuntime()
注意：Runtime类本身就是单例设计模式的一种应用，因为整个JVM中只存在一个Runtime类的对象，可以使用Runtime类取得JVM的系统信息，或者使用gc()方法释放掉垃圾空间，还可以运行本机的程序run.exec("notepad.exe")exec()方法的返回值是Process类，Process类也有一些方法可以使用，比如结束一个进程，通过destroy()结束
---------------------------------------------------------------------------------------------------------------------
什么是线程死锁？如何避免线程死锁？如何加一个线程死锁检查机制？
http://blog.csdn.net/ls5718/article/details/51896159
死锁检测：http://blog.csdn.net/littleschemer/article/details/47449911
https://www.cnblogs.com/lovedesy123/p/7752077.html
死锁的定义
多线程以及多进程改善了系统资源的利用率并提高了系统 的处理能力。然而，并发执行也带来了新的问题——死锁。所谓死锁是指多个线程因竞争资源而造成的一种僵局（互相等待），若无外力作用，这些进程都将无法向前推进。

互斥锁(mutex)Mutex可以分为递归锁(recursive mutex)和非递归锁(non-recursive mutex)。可递归锁也可称为可重入锁(reentrant mutex)，
非递归锁又叫不可重入锁(non-reentrant mutex)。
  二者唯一的区别是，同一个线程可以多次获取同一个递归锁，不会产生死锁。而如果一个线程多次获取同一个非递归锁，则会产生死锁。

死锁产生的原因
产生死锁必须同时满足以下四个条件，只要其中任一条件不成立，死锁就不会发生。
1、互斥条件：进程要求对所分配的资源（如打印机）进行排他性控制，即在一段时间内某 资源仅为一个进程所占有。此时若有其他进程请求该资源，则请求进程只能等待。
2、不剥夺条件：进程所获得的资源在未使用完毕之前，不能被其他进程强行夺走，即只能 由获得该资源的进程自己来释放（只能是主动释放)。
3、请求和保持条件：进程已经保持了至少一个资源，但又提出了新的资源请求，而该资源 已被其他进程占有，此时请求进程被阻塞，但对自己已获得的资源保持不放。
4、循环等待条件：存在一种进程资源的循环等待链，链中每一个进程已获得的资源同时被 链中下一个进程所请求。即存在一个处于等待状态的进程集合{Pl, P2, ..., pn}，其中Pi等 待的资源被P(i+1)占有（i=0, 1, ..., n-1)，Pn等待的资源被P0占有，如图2-15所示。

如何避免死锁
在有些情况下死锁是可以避免的。三种用于避免死锁的技术：
1、加锁顺序（线程按照一定的顺序加锁）
2、加锁时限（线程尝试获取锁的时候加上一定的时限，超过时限则放弃对该锁的请求，并释放自己占有的锁）
	超时和重试机制是为了避免在同一时间出现的竞争，但是当线程很多时，其中两个或多个线程的超时时间一样或者接近的可能性就会很大，因此就算出现竞争而导致超时后，由于超时时间一样，它们又会同时开始重试，导致新一轮的竞争，带来了新的问题
3、死锁检测
一个简单的死锁检测算法
每个进程、每个资源制定唯一编号
设定一张资源分配表，记录各进程与占用资源之间的关系
设置一张进程等待表，记录各进程与要申请资源之间的关系

死锁恢复
打破死锁有两种方法：
（1）简单地终止一个或多个进程以打破循环等待。——进程终止
（2）从一个或多个死锁进程那里抢占一个或多个资源。——资源抢占
进程终止有以下两中方法：
（i）终止所有死锁进程。
（II)一次只终止一个进程直到取消死锁循环为止。
如果选择资源抢占，那么将必须考虑三个问题：
（i）选择一个牺牲品
（II）回滚
（III）饥饿（在代价因素中加上回滚次数，回滚的越多则越不可能继续被作为牺牲品）


jstack可以看到Found one Java-level deadlock
pstack： 功能是打印输出此进程的堆栈信息。可以输出所有线程的调用关系栈
jstack：jstack是java虚拟机自带的一种堆栈跟踪工具，所以仅适用于java程序，功能跟pstack一样，但是更强大，可以提示哪个地方可能死锁了。
pstack和jstack判断死锁，都需要多执行几次命令，观察每次的输出结果，才能推测是否死锁了。
---------------------------------------------------------------------------------------------------------------------
java进程间通信(IPC interProcess communication)：
除了上面提到的Socket之外，当然首选的IPC可以使用Rmi，或者Corba也可以。另外Java nio的MappedByteBuffer也可以通过内存映射文件来实现进程间通信(共享内存)。
远程过程调用（Remote Procedure Call, RPC）
远程方法调用（Remote Method Invocation, RMI）

在java中主要的通信方式有以下几种： 
1、socket通信 
2、RMI（远程方法调用） 
3、消息队列（第三方框架Kafka，ActiveMQ等） 
4、JMX（java management extensions）java扩展管理 
5、文件共享，文件锁（一个进程向文件中写文件，一个负责读文件） 
6、信号 
7、信号量
---------------------------------------------------------------------------------------------------------------------
Throwable、Error、Exception、RuntimeException 区别和联系各是什么
http://blog.csdn.net/liuj2511981/article/details/8524418
http://blog.csdn.net/kingzone_2008/article/details/8535287
http://blog.csdn.net/kwu_ganymede/article/details/51382461
1.Throwable类是 Java 语言中所有错误或异常的超类。它的两个子类是Error和Exception；
2.Error是Throwable 的子类，用于指示合理的应用程序不应该试图捕获的严重问题。
3.Exception类及其子类是 Throwable 的一种形式，它指出了合理的应用程序想要捕获的条件
4.RuntimeException类是Exception类的子类。RuntimeException是那些可能在 Java 虚拟机正常运行期间抛出的异常的超类。可能在执行方法期间抛出但未被捕获的RuntimeException 的任何子类都无需在 throws 子句中进行声明。它是Exception的子类。

Exception：
1．可以是可被控制(checked) 或不可控制的(unchecked) 
2．表示一个由程序员导致的错误 
3．应该在应用程序级被处理

Error：
1．总是不可控制的(unchecked) 
2．经常用来表示系统错误或低层资源的错误 
3．如何可能的话，应该在系统级被捕捉

Java 中定义了两类异常： 
　　1) Checked exception: 这类异常都是Exception的子类 。异常的向上抛出机制进行处理，假如子类可能产生A异常，那么在父类中也必须throws A异常。可能导致的问题：代码效率低，耦合度过高。
　　2) Unchecked exception: 这类异常都是RuntimeException的子类，虽然RuntimeException同样也是Exception的子类，但是它们是非凡的，它们不能通过client code来试图解决，所以称为Unchecked exception 。
您应该知道的是Java 提供了两种Exception 的模式，一种是执行的时候所产生的Exception (Runtime Exception)，另外一种则是受控制的Exception (Checked Exception)。
　　所有的Checked Exception 均从java.lang.Exception 继承而来，而Runtime Exception 则继承java.lang.RuntimeException 或java.lang.Error (实际上java.lang.RuntimeException 的上一层也是java.lang.Exception)。
 
 因此从程序的运作机制上看，Runtime Exception与Checked Exception 不一样，然而从逻辑上看，Runtime Exception 与Checked Exception 在使用的目的上也不一样。
　　一般而言，Checked Exception 表示这个Exception 必须要被处理，也就是说程序设计者应该已经知道可能会收到某个Exception(因为要try catch住) ，所以程序设计者应该能针对这些不同的Checked Exception 做出不同的处理。
　　而Runtime Exception 通常会暗示着程序上的错误，这种错误会导致程序设计者无法处理，而造成程序无法继续执行下去。
---------------------------------------------------------------------------------------------------------------------
分布式锁实现：
https://www.cnblogs.com/yuyutianxia/p/7149363.html
http://blog.csdn.net/x_i_y_u_e/article/details/50864205
http://www.importnew.com/27477.html?utm_source=tuicool&utm_medium=referral

首先，为了确保分布式锁可用，我们至少要确保锁的实现同时满足以下四个条件：
1、互斥性。在任意时刻，只有一个客户端能持有锁。
2、不会发生死锁。即使有一个客户端在持有锁的期间崩溃而没有主动解锁，也能保证后续其他客户端能加锁。
3、具有容错性。只要大部分的Redis节点正常运行，客户端就可以加锁和解锁。
4、解铃还须系铃人。加锁和解锁必须是同一个客户端，客户端自己不能把别人加的锁给解了。

针对分布式锁的实现目前有多种方案：
1、基于数据库实现分布式锁：获取锁插入一条记录，释放锁就删除记录
2、基于缓存（redis，memcached）实现分布式锁
3、基于Zookeeper实现分布式锁

数据库实现缺点：数据库单点问题
这把锁没有失效时间，一旦解锁操作失败，就会导致锁记录一直在数据库中，其他线程无法再获得到锁。
这把锁只能是非阻塞的，因为数据的insert操作，一旦插入失败就会直接报错。没有获得锁的线程并不会进入排队队列，要想再次获得锁就要再次触发获得锁操作。
这把锁是非重入的，同一个线程在没有释放锁之前无法再次获得该锁。因为数据中数据已经存在了。

数据库是单点？搞两个数据库，数据之前双向同步。一旦挂掉快速切换到备库上。
没有失效时间？只要做一个定时任务，每隔一定时间把数据库中的超时数据清理一遍。
非阻塞的？搞一个while循环，直到insert成功再返回成功。
非重入的？在数据库表中加个字段，记录当前获得锁的机器的主机信息和线程信息，那么下次再获取锁的时候先查询数据库，如果当前机器的主机信息和线程信息在数据库可以查到的话，直接把锁分配给他就可以了。


基于缓存：
redis的setnx方法等。并且，这些缓存服务也都提供了对数据的过期自动删除的支持，可以直接设置超时时间来控制锁的释放。
使用缓存实现分布式锁的优点: 性能好，实现起来较为方便。
使用缓存实现分布式锁的缺点: 通过超时时间来控制锁的失效时间并不是十分的靠谱。


基于ZK的方式：
基于zookeeper临时有序节点可以实现的分布式锁。大致思想即为：每个客户端对某个方法加锁时，在zookeeper上的与该方法对应的指定节点的目录下，生成一个唯一的
瞬时有序节点。 判断是否获取锁的方式很简单，只需要判断有序节点中序号最小的一个。 当释放锁的时候，只需将这个瞬时节点删除即可。同时，其可以避免服务宕机导
致的锁无法释放，而产生的死锁问题。

锁无法释放？使用Zookeeper可以有效的解决锁无法释放的问题，因为在创建锁的时候，客户端会在ZK中创建一个临时节点，一旦客户端获取到锁之后突然挂掉（
Session连接断开），那么这个临时节点就会自动删除掉。其他客户端就可以再次获得锁。

非阻塞锁？使用Zookeeper可以实现阻塞的锁，客户端可以通过在ZK中创建顺序节点，并且在节点上绑定监听器，一旦节点有变化，Zookeeper会通知客户端，客户端可以检查自己创建的节点是不是当前所有节点中序号最小的，如果是，那么自己就获取到锁，便可以执行业务逻辑了。

不可重入？使用Zookeeper也可以有效的解决不可重入的问题，客户端在创建节点的时候，把当前客户端的主机信息和线程信息直接写入到节点中，下次想要获取锁的
时候和当前最小的节点中的数据比对一下就可以了。如果和自己的信息一样，那么自己直接获取到锁，如果不一样就再创建一个临时的顺序节点，参与排队。

单点问题？使用Zookeeper可以有效的解决单点问题，ZK是集群部署的，只要集群中有半数以上的机器存活，就可以对外提供服务。
---------------------------------------------------------------------------------------------------------------------

package java.util.concurrent
1、Atomic原子类型：Long、Integer、Boolean、Refrence等
2、Lock锁：ReentrantLock、ReentrantReadWriteLock等
3、线程池：Callable接口、Future接口、FutureTask（ExecutorCompletionService）、Executors、ExecutorService、ThreadPoolExecutor、ThreadFactory、
4、集合：ConcurrentHashMap、ConcurrentSkipListSet、CopyOnWriteArrayList等
5、工具类：CountDownLatch、CyclicBarrier、Semaphore、Exchanger、ForkJoinPool等

并发开始、并发完成、、并发控制、线程数据交换
同步屏障CyclicBarrier
等待多线程完成的CountDownLatch
控制并发线程数的Semaphore
两个线程进行数据交换的Exchanger

Java并发工具类：
并发开始：同步屏障CyclicBarrier
并发结束：等待多线程完成的CountDownLatch
并发控制：控制并发线程数的Semaphore
并发交换：两个线程进行数据交换的Exchanger

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

http://www.cnblogs.com/xrq730/p/4976007.html
Unsafe类的CAS操作可能是用的最多的，它为Java的锁机制提供了一种新的解决办法，比如AtomicInteger等类都是通过该方法来实现的。compareAndSwap方法是原子的，可以避免繁重的锁机制，提高代码效率。这是一种乐观锁，通常认为在大部分情况下不出现竞态条件，如果操作失败，会不断重试直到成功。

CAS：比如java中的AtomicInteger的addAndGet方法，会一直做do-while循环，直到操作成功获取并且增加
CAS，Compare and Swap即比较并交换，设计并发算法时常用到的一种技术，java.util.concurrent包全完建立在CAS之上，没有CAS也就没有此包，可见CAS的重要性。
当前的处理器基本都支持CAS，只不过不同的厂家的实现不一样罢了。CAS有三个操作数：内存值V、旧的预期值A、要修改的值B，当且仅当预期值A和内存值V相同时，将内存值修改为B并返回true，否则什么都不做并返回false。

CAS的缺点:不能解决ABA问题，如果需要解决ABA问题，使用传统的互斥同步可能回避原子类更加高效。
这个漏洞称为CAS操作的"ABA"问题。java.util.concurrent包为了解决这个问题，提供了一个带有标记的原子引用类"AtomicStampedReference"，它可以通过控制变量值的版本来保证CAS的正确性。不过目前来说这个类比较"鸡肋"，大部分情况下ABA问题并不会影响程序并发的正确性，如果需要解决ABA问题，使用传统的互斥同步可能回避原子类更加高效。
---------------------------------------------------------------------------------------------------------------------
线程池：
ExecutorService
ThreadPoolExecutor或者Executors工具类来创建
public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) 
RejectedExecutionHandler:
ThreadPoolExecutor.AbortPolicy()，抛出java.util.concurrent.RejectedExecutionException异常 
ThreadPoolExecutor.CallerRunsPolicy，它直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务。
ThreadPoolExecutor.DiscardOldestPolicy();，先poll掉workQueue中的一个任务，然后调用线程池的execute方法执行当前task
ThreadPoolExecutor.DiscardPolicy，拒绝策略方法为空，就是不做任何处理，默认情况下它将丢弃被拒绝的任务。

线程池其他方法：
beforeExecute、afterExecute、



---------------------------------------------------------------------------------------------------------------------
如何实现分布式缓存
https://www.cnblogs.com/yangxiaolan/p/5786123.html
http://blog.csdn.net/singit/article/details/54917884
http://blog.csdn.net/qq_33647837/article/details/69375147
关于缓存雪崩和缓存穿透等问题：http://blog.csdn.net/csdn265/article/details/56012271

缓存穿透是指查询一个一定不存在的数据，由于缓存是不命中时被动写的，并且出于容错考虑，如果从存储层查不到数据则不写入缓存，这将导致这个不存在的数据每次请求都要到存储层去查询，失去了缓存的意义。在流量大时，可能DB就挂掉了，要是有人利用不存在的key频繁攻击我们的应用，这就是漏洞。

解决方案：有很多种方法可以有效地解决缓存穿透问题，最常见的则是采用布隆过滤器，将所有可能存在的数据哈希到一个足够大的bitmap中，一个一定不存在的数据会被 这个bitmap拦截掉，从而避免了对底层存储系统的查询压力。另外也有一个更为简单粗暴的方法（我们采用的就是这种），如果一个查询返回的数据为空（不管是数 据不存在，还是系统故障），我们仍然把这个空结果进行缓存，但它的过期时间会很短，最长不超过五分钟。


布隆过滤器的原理和实现：可以判断一个元素一定不在集合中，不能判断一个元素是否一定在集合中
https://github.com/cpselvis/zhihu-crawler/wiki/%E5%B8%83%E9%9A%86%E8%BF%87%E6%BB%A4%E5%99%A8%E7%9A%84%E5%8E%9F%E7%90%86%E5%92%8C%E5%AE%9E%E7%8E%B0
布隆过滤器（Bloom Filter）的核心实现是一个超大的位数组和几个哈希函数。假设位数组的长度为m，哈希函数的个数为k
首先将位数组进行初始化，将里面每个位都设置位0。对于集合里面的每一个元素，将元素依次通过3个哈希函数进行映射，每次映射都会产生一个哈希值，这个值对应位数组上面的一个点，然后将位数组对应的位置标记为1。
查询W元素是否存在集合中的时候，同样的方法将W通过哈希映射到位数组上的3个点。
如果3个点的其中有一个点不为1，则可以判断该元素一定不存在集合中。反之，如果3个点都为1，则该元素可能存在集合中。注意：此处不能判断该元素是否一定存在集合中，可能存在一定的误判率。

1、布隆过滤器添加元素：
将要添加的元素给k个哈希函数
得到对应于位数组上的k个位置
将这k个位置设为1

2、布隆过滤器查询元素：
将要查询的元素给k个哈希函数
得到对应于位数组上的k个位置
如果k个位置有一个为0，则肯定不在集合中
如果k个位置全部为1，则可能在集合中


解决缓存击穿问题：缓存预热、定时更新，直接保存key值为空，BloomFilter过滤，请求队列控制并发，

缓存雪崩
缓存雪崩是由于原有缓存失效(过期)，新缓存未到期间。所有请求都去查询数据库，而对数据库CPU和内存造成巨大压力，严重的会造成数据库宕机。从而形成一系列连锁反应，造成整个系统崩溃。
(1) 碰到这种情况，一般并发量不是特别多的时候，使用最多的解决方案是加锁排队。
（2）缓存标记：记录缓存数据是否过期，如果过期会触发通知另外的线程在后台去更新实际key的缓存
缓存穿透
　　缓存穿透是指用户查询数据，在数据库没有，自然在缓存中也不会有。这样就导致用户查询的时候，在缓存中找不到，每次都要去数据库再查询一遍，然后返回空。这样请求就绕过缓存直接查数据库，这也是经常提的缓存命中率问题。
（1）解决的办法就是：如果查询数据库也为空，直接设置一个默认值存放到缓存，这样第二次到缓冲中获取就有值了，而不会继续访问数据库，这种办法最简单粗暴。
（2）缓存预热
　　缓存预热就是系统上线后，将相关的缓存数据直接加载到缓存系统。这样避免，用户请求的时候，再去加载相关的数据。
解决思路：
a直接写个缓存刷新页面，上线时手工操作下。
b数据量不大，可以在WEB系统启动的时候加载。
c定时刷新缓存，
（3）缓存更新
缓存淘汰的策略有两种：
　　a定时去清理过期的缓存。
　　b当有用户请求过来时，再判断这个请求所用到的缓存是否过期，过期的话就去底层系统得到新数据并更新缓存。 

大家可以根据自己的应用场景来权衡。1. 预估失效时间 2. 版本号（必须单调递增，时间戳是最好的选择）3. 提供手动清理缓存的接口。



---------------------------------------------------------------------------------------------------------------------

volatile原理：http://blog.csdn.net/opensure/article/details/46669337
Java中Volatile底层原理与应用：内存屏障
http://blog.csdn.net/u012465296/article/details/53020676
volatile是一种“轻量级的锁”，它能保证锁的可见性，但不能保证锁的原子性。 

http://ifeve.com/memory-barriers-or-fences/
内存屏障或内存栅栏，也就是让一个CPU处理单元中的内存状态对其它处理单元可见的一项技术。
Java内存模型中volatile变量在写操作之后会插入一个store屏障，在读操作之前会插入一个load屏障。

缓存一致性：
http://www.infoq.com/cn/articles/cache-coherency-primer
http://www.infoq.com/cn/articles/atomic-operations-and-contention
MESI－CPU缓存一致性协议：http://blog.csdn.net/realxie/article/details/7317630
volatile使用场景：Double-Check Locking，缓存对象的map等（不保证原子性，使用在并发环境下的原子操作情况下）
一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰之后，那么就具备了两层语义：
1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
2）禁止进行指令重排序。
3）并发编程中的三个概念：原子性，可见性，有序性，volatile只保证可见性和有序性，不保证原子性
1、CPU的三级缓存，volatile的缓存失效是指的cpu的二级缓存，CPU的三级缓存：一级在CPU内部，容量小，速度快，二级是在CPU和内存之间的，当线程需要读取数据时候，先读取缓存，不存在才读取内存数据，三级缓存是对二级缓存的补充。
2、普通变量与volatile变量的区别是volatile的特殊规则保证了新值能立即同步到主内存，以及每次使用前可以立即从内存刷新，即一个线程修改了某个变量的值，其它线程读取的话肯定能看到新的值；
3、Lock前缀指令会引起处理器缓存回写到内存。一个处理器的缓存回写到内存会导致其他处理器的缓存无效。
1）将当前处理器缓存行的数据会写回到系统内存。
它会锁定这块内存区域的缓存并回写到内存，并使用缓存一致性机制来确保修改的原子性，此操作被称为“缓存锁定”，缓存一致性机制会阻止同时修改被两个以上处理器缓存的内存区域数据。
2）这个写回内存的操作会引起在其他CPU里缓存了该内存地址的数据无效。
关键点：其实相当于线程 像缓存行写数据的时候，会锁住缓存行，是其他线程不能读，写完后失效缓存行，其他线程便可以从内存读到共享变量的最新值了；
4、如果对声明了Volatile变量进行写操作，JVM就会向处理器发送一条Lock前缀的指令，将这个变量所在缓存行的数据写回到系统内存。但是就算写回到内存，如果其他处理器缓存的值还是旧的，再执行计算操作就会有问题，所以在多处理器下，为了保证各个处理器的缓存是一致的，就会实现缓存一致性协议，每个处理器通过嗅探在总线上传播的数据来检查自己缓存的值是不是过期了，当处理器发现自己缓存行对应的内存地址被修改，就会将当前处理器的缓存行设置成无效状态，当处理器要对这个数据进行修改操作的时候，会强制重新从系统内存里把数据读到处理器缓存里。

当CPU看到一条读内存的指令时，它会把内存地址传递给一级数据缓存，一级数据缓存会检查它是否有这个内存地址对应的缓存段。如果没有，它会把整个缓存段从内存（或者从更高一级的缓存，如果有的话）中加载进来。是的，一次加载整个缓存段，这是基于这样一个假设：内存访问倾向于本地化（localized），如果我们当前需要某个地址的数据，那么很可能我们马上要访问它的邻近地址。一旦缓存段被加载到缓存中，读指令就可以正常进行读取。

如果我们只处理读操作，那么事情会很简单，因为所有级别的缓存都遵守以下规律，我称之为：
基本定律：在任意时刻，任意级别缓存中的缓存段的内容，等同于它对应的内存中的内容。
一旦我们允许写操作，事情就变得复杂一点了。这里有两种基本的写模式：直写（write-through）和回写（write-back）。

直接模式更简单，但是回写模式有它的优势：它能过滤掉对同一地址的反复写操作，并且，如果大多数缓存段都在回写模式下工作，那么系统经常可以一下子写一大片内存，而不是分成小块来写，前者的效率更高。

不同的级别缓存可能使用不同的写策略，所以缓存行大小也可能不同。

MESI定律：在所有的脏缓存段（M状态）被回写后，任意缓存级别的所有缓存段中的内容，和它们对应的内存中的内容一致。此外，在任意时刻，当某个位置的内存被一个处理器加载入独占缓存段时（E状态），那它就不会再出现在其他任何处理器的缓存中。
MESI协议（译者注：MESI是Modified、Exclusive、Shared、Invalid的首字母缩写，代表四种缓存状态
失效（Invalid）缓存段、共享（Shared）缓存段、独占（Exclusive）缓存段、已修改（Modified）缓存段，属于脏段
只有E、M状态下的缓存才能写，也就是在写之前，先申请独占，使其他的CPU的同段缓存失效

1、缓存行：所谓缓存行就是缓存中可以分配的最小存储单位。一般是64字节，老的是32字节如P6系列和奔腾处理器，它们的L1和L2高速缓存行是32个字节宽
缓存是分“段”（line）的，一个段对应一块存储空间，大小是32（较早的ARM、90年代/2000年代早期的x86和PowerPC）、64（较新的ARM和x86）或128（较新的Power ISA机器）字节。
我们会看到有CPU的一级缓存是32字节，而二级缓存却有128字节。
2、缓存伪共享：因为对于英特尔酷睿i7，酷睿， Atom和NetBurst， Core Solo和Pentium M处理器的L1，L2或L3缓存的高速缓存行是64个字节宽，不支持部分填充缓存行，导致一个缓存行加载多个共享变量，一个处理器锁定时，锁定该缓存行时候，多锁定了对象，导致其他处理器处理不了，变为锁的互斥性，导致不能并发，成为伪共享
如JDK7的并发包里新增一个队列集合类LinkedTransferQueue（它使用一个内部类类型来定义队列的头队列（Head）和尾节点（tail）），JDK加载时候会把头尾分别进行字节填充，保证头尾加载到不同的缓存行，避免出入对互斥，不能并发，成为伪共享。
Java8中用sun.misc.Contended避免伪共享(false sharing)：
https://blog.csdn.net/aigoogle/article/details/41518369
1、long padding来避免伪共享 
2、jdk8新特性，Contended注解避免false sharing：需要在jvm启动时设置-XX:-RestrictContended 
3、CPU处理器的缓存三级：L1、L2、L3，最早CPU和L1通信，L1和内存通信，现在CPU--》L1--》L2--》内存
L1　Cache(一级缓存)是CPU第一层高速缓存，分为数据缓存和指令缓存。内置的L1高速缓存的容量和结构对CPU的性能影响较大，不过高速缓冲存储器均由静态RAM组成，结构较复杂，容量小
L2 Cache 都在CPU中
L3　Cache(三级缓存)，分为两种，早期的是外置，现在的都是内置的。而它的实际作用即是，L3缓存的应用可以进一步降低内存延迟，同时提升大数据量计算时处理器的性能。降低内存延迟和提升大数据量计算能力对游戏都很有帮助。
4、缓存一致性协议
MESI定律：在所有的脏缓存段（M状态）被回写后，任意缓存级别的所有缓存段中的内容，和它们对应的内存中的内容一致。此外，在任意时刻，当某个位置的内存被一个处理器加载入独占缓存段时（E状态），那它就不会再出现在其他任何处理器的缓存中。
MESI协议（译者注：MESI是Modified、Exclusive、Shared、Invalid的首字母缩写，代表四种缓存状态
失效（Invalid）缓存段、共享（Shared）缓存段、独占（Exclusive）缓存段、已修改（Modified）缓存段，属于脏段
只有E、M状态下的缓存才能写，也就是在写之前，先申请独占，使其他的CPU的同段缓存失效

缓存段竞争：要产生缓存段竞争，我们需要多个处理器频繁访问同一缓存段，并且其中部分的访问是写操作。
如果两个或多个处理器频繁地访问相同的缓存段，那么这些缓存段的内容必须保持同步。如果想更新其中一个缓存段的内容，必须先获得独占权，这意味着其他所有处理器必须先丢弃它们缓存中的同一缓存段的拷贝。这带来的结果是，下一次有另外一个处理器要访问这个缓存段，它的内容必须先通过总线来加载。所以结果就是缓存失效率（对于其他处理器来说）和总线上额外的通讯流量都增加了。这种多个处理器访问一个频繁被更新的缓存段的现象，叫做“缓存（段）竞争”。如果你想在多个处理器共用内存的环境中拖慢一个并行的程序，这也许是最简单的方法。
总线风暴：总线上额外的通讯流量增加很多



---------------------------------------------------------------------------------------------------------------------
http://ifeve.com/java-copy-on-write/http://ifeve.com/java-copy-on-write/
从JDK1.5开始Java并发包里提供了两个使用CopyOnWrite机制实现的并发容器,它们是CopyOnWriteArrayList和CopyOnWriteArraySet。
CopyOnWrite容器即写时复制的容器。通俗的理解是当我们往一个容器添加元素的时候，不直接往当前容器添加，而是先将当前容器进行Copy，复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器。这样做的好处是我们可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
CopyOnWrite并发容器用于读多写少的并发场景。
CopyOnWrite容器有很多优点，但是同时也存在两个问题，即内存占用问题和数据一致性问题。所以在开发的时候需要注意一下。
---------------------------------------------------------------------------------------------------------------------
数据结构：堆和树
http://blog.csdn.net/juanqinyang/article/details/51418629
http://blog.csdn.net/leex_brave/article/details/51490647
堆（heap）也被称为优先队列（priority queue）。队列中允许的操作是先进先出（FIFO），在队尾插入元素，在队头取出元素。而堆也是一样，在堆底插入元素，在堆顶取出元素，但是堆中元素的排列不是按照到来的先后顺序，而是按照一定的优先顺序排列的。这个优先顺序可以是元素的大小或者其他规则。
堆优先顺序就是大的元素排在前面，小的元素排在后面，这样得到的堆称为最大堆。最大堆中堆顶的元素是整个堆中最大的，并且每一个分支也可以看成一个最大堆。同样的，我们可以定义最小堆，

树：树是由结点或顶点和边组成的(可能是非线性的)且不存在着任何环的一种数据结构。没有结点的树称为空(null或empty)树。一棵非空的树包括一个根结点，还(很可能)有多个附加结点，所有结点构成一个多级分层结构。
http://blog.jobbole.com/111680/
http://blog.csdn.net/HaoDaWang/article/details/78065162?locationNum=2&fps=1

1二叉树：每个结点至多拥有两棵子树(即二叉树中不存在度大于2的结点)，并且，二叉树的子树有左右之分，其次序不能任意颠倒。
2满二叉树：一个深度为k(>=-1)且有2^(k+1) - 1个结点的二叉树称为满二叉树。
3完全二叉树：完全二叉树从根结点到倒数第二层满足完美二叉树，最后一层可以不完全填充，其叶子结点都靠左对齐。
二叉查找树定义：又称为是二叉排序树（Binary Sort Tree）或二叉搜索树。
4平衡二叉树定义：平衡二叉树（Balanced Binary Tree）又被称为AVL树（有别于AVL算法），且具有以下性质：它是一 棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。平衡二叉树的常用算法有红黑树、AVL树等。在平衡二叉搜索树中，我们可以看到，其高度一般都良好地维持在O(log2n)，大大降低了操作的时间复杂度。
平衡查找树之AVL树
平衡二叉树之红黑树
B树的定义：B树（B-tree）是一种树状数据结构，能够用来存储排序后的数据。这种数据结构能够让查找数据、循序存取、插入数据及删除的动作，都在对数时间内完成。B树，概括来说是一个一般化的二叉查找树，可以拥有多于2个子节点。与自平衡二叉查找树不同，B-树为系统最优化大块数据的读和写操作。B-tree算法减少定位记录时所经历的中间过程，从而加快存取速度。这种数据结构常被应用在数据库和文件系统的实作上。
B+树是B树的变体，也是一种多路搜索树，叶子节点指向下一个叶子节点
B*树是B+树的变体，在B+树的非根和非叶子结点再增加指向兄弟的指针，将结点的最低利用率从1/2提高到2/3。
Tire树称为字典树，又称单词查找树，Trie树，是一种树形结构，是一种哈希树的变种。

---------------------------------------------------------------------------------------------------------------------
Queue方法：
add、remove、element  抛异常
offer、poll、peek	 返回boolean
put、take  阻塞
队列：FIFO，栈：FILO
---------------------------------------------------------------------------------------------------------------------
数组、链表，队列 ，栈，散列表，树，图：
http://blog.csdn.net/mz454619501/article/details/46652317
http://blog.csdn.net/hhu1506010220/article/details/52128383
http://blog.csdn.net/wei78008023/article/details/50735415
http://blog.csdn.net/qq_15654993/article/details/75267581
http://blog.csdn.net/RodeStillFaraway/article/details/50530142
常用数据结构的时间复杂度：http://blog.csdn.net/CloudyBird/article/details/51206789
数组Array：数组是最最基本的数据结构，很多语言都内置支持数组。数组是使用一块连续的内存空间保存数据，保存的数据的个数在分配内存的时候就是确定的
链表Linked List：非连续非顺序的存储结构，循环链表和单链表、双向链表，链表不需要提前分配固定大小存储空间，当需要存储数据的时候分配一块内存并将这块内存插入链表中。
队列Queue：FIFO，顺序队列和循序队列
栈Stack：FILO
散列表Hash表:
树Tree：树（tree）是包含n（n>0）个结点的有穷集
堆Heap：
图Graph：

访问数组中第 n 个数据的时间花费是 O(1) 但是要在数组中查找一个指定的数据则是 O(N)。当向数组中插入或者删除数据的时候，最好的情况是在数组的末尾进行操作，时间复杂度是O(1) ，但是最坏情况是插入或者删除第一个数据，时间复杂度是 O(N) 。在数组的任意位置插入或者删除数据的时候，后面的数据全部需要移动，移动的数据还是和数据个数有关所以总体的时间复杂度仍然是 O(N) 。

在链表中查找第 n 个数据以及查找指定的数据的时间复杂度是 O(N) ，但是插入和删除数据的时间复杂度是 O(1) ，因为只需要调整指针就可以

基本概念
    程序 = 算法 + 数据结构
    数据结构是计算机存储、组织数据的方式。
    数据结构是指相互之间存在一种或多种特定关系的数据元素的集合。
    通常情况下，精心选择的数据结构可以带来更高的运行或者存储效率。
    数据结构往往同高效的检索算法和索引技术有关。
    
常见数据结构
    集合：set，multiset
    线性结构：数组、链表、队列、栈
    树形结构：二叉树及其变型，线段树
    图形结构：各种图

数据结构体系图：
![数据结构体系图](./image/datastructsystem.png "三生三世")
![数据结构时间复杂度](./image/Datastructuretimecomplexity.png "三生三世")
---------------------------------------------------------------------------------------------------------------------

https://www.cnblogs.com/aspirant/p/6856487.html
1：问：讲讲对HashMap的认识？hashmap的初试容量及每次扩容因子？
回答：HashMap底层用的是哈希表的map接口，Hashmap储存数据的方式是以K-V的形式存在的；HashMap初始容量大小为16，扩容因子为2;HashMap有两个参数影响其性能：初始容量和加载因子。默认初始容量是16，加载因子是0.75。加载因子就是rehash扩容时候的参数
2：问：是否是线程安全？ 
答：不安全。
3：问：为什么不安全？
2-1：HashMap底层是一个Entry数组，存在的一些问题如：
2-2：resize死循环
2-3：如果在使用迭代器的过程中有其他线程修改了map，那么将抛出ConcurrentModificationException，这就是所谓fail-fast策略。
HashMap 是不是有序的，有哪些有序的Map？为什么TreeMap 是有序的？
LinkedHashMap 是基于元素进入集合的顺序或者被访问的先后顺序排序，TreeMap 则是基于元素的固有顺序 (由 Comparator 或者 Comparable 确定)。

死循环、脏读、操作覆盖等
hashmap扩容时候，在并发环境下可能会导致数组链表形成环路，进而在get不存在的数据的时候发生死循环，是CPU的占用率达到100%
https://www.cnblogs.com/kxdblog/p/4323892.html
https://www.cnblogs.com/study-everyday/p/6430462.html
http://blog.csdn.net/qfzhangwei/article/details/69938937
http://ifeve.com/hashmap-infinite-loop/
hashmap扩容时候rehash操作，导致链表倒置
hashmap在多线程环境下，线程同时扩容或put时候hash值相同，可能出现同时在同一数组下用链表表示，造成链表闭环，导致在get不存在的数据会出现死循环，进而导致CPU占用飙升或100%
数据
如果table[]的尺寸很小，比如只有2个，如果要放进10个keys的话，那么碰撞非常频繁，于是一个O(1)的查找算法，就变成了链表遍历，性能变成了O(n)，这是Hash表的缺陷
Hash表的尺寸和容量非常的重要。一般来说，Hash表这个容器当有数据要插入时，都会检查容量有没有超过设定的thredhold，如果超过，需要增大Hash表的尺寸，这样一来，整个Hash表里的无素都需要被重算一遍。这叫rehash，这个成本相当的大。

在多线程的环境下，存在同时其他的元素也在进行put操作，如果hash值相同，可能出现同时在同一数组下用链表表示，造成闭环，导致在get时会出现死循环，所以HashMap是线程不安全的。

只有在多线程并发的情况下才会出现这种情况，那就是在put操作的时候，如果size>initialCapacity*loadFactor，hash表进行扩容，那么这时候HashMap就会进行rehash操作，随之HashMap的结构就会很大的变化。很有可能就是在两个线程在这个时候同时触发了rehash操作，产生了闭合的回路。
多线程下[HashMap]的问题：推荐使用currentHashMap
1、多线程put操作后，get操作导致死循环。
2、多线程put非NULL元素后，get操作得到NULL值。put丢失
3、多线程put操作，导致元素丢失或者覆盖

remove与put都是一样的，由于大家拿到的不是最新链头，只要大家在Entry数组的index相同时(经过hash后的index)，就有可能出现后一个覆盖前一个的操作，即前一个的操作无效。 
可能产生的现象会是： 
1)put进行的data有可能丢失或者被覆盖
2)一些通过remove(Object key)删除掉的元素(返回删除成功)又出来了。 
3)多线程检测到HashMap容量超过负载因子时会进行多次的resize，由于要rehash，所以消耗的性能也是巨大的。 

java集合迭代时候，不能使用集合的remove删除元素，会报错：
https://www.cnblogs.com/softidea/p/3760213.html
http://blog.csdn.net/u010887744/article/details/50839129
https://www.cnblogs.com/YYCat/p/4675084.html
https://www.cnblogs.com/wangqw-cn/p/4581559.html
https://www.cnblogs.com/think-in-java/p/5170914.html
使用迭代模式模式遍历集合
在集合类内部保存记录修改次数modCount，在迭代器类内部保存有expectedModCount变量，在迭代器初始化时候时候expectedModCount=modCount，当迭代时候发现不等，就抛出异常ConcurrentModificationException
可以使用迭代器的remove，因为迭代器的remove同时修改了iterator的expectedModCount变量为最新的modCount

简单来说：迭代器在创建后就不变（所引用的集合元素是不变的），集合元素在迭代过程中是可变的。另外，因为迭代器在创建后所引用的集合元素不可变，即通过迭代器时候，如果使用集合中的操作修改集合的操作都会抛出UnsupportedOperationException异常。 

Iterator 是工作在一个独立的线程中，并且拥有一个 mutex 锁。 Iterator 被创建之后会建立一个指向原来对象的单链索引表，当原来的对象数量发生变化时，这个索引表的内容不会同步改变，所以当索引指针往后移动的时候就找不到要迭代的对象，所以按照 fail-fast 原则 Iterator 会马上抛出 java.util.ConcurrentModificationException 异常。
所以 Iterator 在工作的时候是不允许被迭代的对象被改变的。但你可以使用 Iterator 本身的方法 remove() 来删除对象， Iterator.remove() 方法会在删除当前迭代对象的同时维护索引的一致性

在使用迭代器对集合当中的数据元素进行操作时，如果要对集合当中指定的数据元素进行删除操作时，应使用迭代器当中指定的remove方法来对集合当中的数据元素进行删除操作，而不能够使用集合当中的remove方法。若使用集合当中的方法对数据进行删除操作的话，将坏破坏整个迭代器机制使得迭代器在之后的操作当中不在起作用。

我们知道java.util.HashMap不是线程安全的，因此如果在使用迭代器的过程中有其他线程修改了map，那么将抛出ConcurrentModificationException，这就是所谓fail-fast策略。

在内部类Itr中，有一个字段expectedModCount ，初始化时等于modCount，即当我们调用list.iterator()返回迭代器时，该字段被初始化为等于modCount。在类Itr中next/remove方法都有调用checkForComodification()方法，在该方法中检测modCount == expectedModCount，如果不相当则抛出异常ConcurrentModificationException。

再来看看内部类Itr的remove()方法，在删除元素后，有这么一句expectedModCount = modCount，同步修改expectedModCount 的值。所以，如果需要在使用迭代器迭代时，删除元素，可以使用迭代器提供的remove方法。对于add操作，则在整个迭代器迭代过程中是不允许的。 其他集合(Map/Set)使用迭代器迭代也是一样。

当使用 fail-fast iterator 对 Collection 或 Map 进行迭代操作过程中尝试直接修改 Collection / Map 的内容时，即使是在单线程下运行，  java.util.ConcurrentModificationException 异常也将被抛出。 　　

 HashMap迭代时Remove掉map中包含的键值对，从而改变结构（modCount加1），接下来程序若再有检测modCount的fast-fail机制，程序便将抛出ConcurrentModificationException异常。

有意思的是如果你的 Collection / Map 对象实际只有一个元素的时候， ConcurrentModificationException 异常并不会被抛出。这也就是为什么在 javadoc 里面指出： it would be wrong to write a program that depended on this exception for its correctness: ConcurrentModificationException should be used only to detect bugs.

在HashMap的API中指出：
   由所有HashMap类的“collection 视图方法”所返回的迭代器都是快速失败的：在迭代器创建之后，如果从结构上对映射进行修改，除非通过迭代器本身的 remove 方法，其他任何时间任何方式的修改，迭代器都将抛出ConcurrentModificationException。因此，面对并发的修改，迭代器很快就会完全失败，而不冒在将来不确定的时间发生任意不确定行为的风险。

   注意，迭代器的快速失败行为不能得到保证，一般来说，存在非同步的并发修改时，不可能作出任何坚决的保证。快速失败迭代器尽最大努力抛出 ConcurrentModificationException。因此，编写依赖于此异常的程序的做法是错误的，正确做法是：迭代器的快速失败行为应该仅用于检测程序错误。
   
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
Java线程的5种状态及切换(透彻讲解)
https://www.cnblogs.com/nwnu-daizh/p/8036156.html
http://blog.csdn.net/pange1991/article/details/53860651


新建-->就绪-->运行-->死亡（start(),获取cpu时间片,run/main结束）
运行-->阻塞-->就绪-->运行（sleep、t2.join、等用户输入，3中阻塞条件结束，获取cpu时间片）
运行-->等待队列-->锁池队列-->就绪（wait()+notify/notifyAll、synchronized(obj) ）
运行-->就绪（yield()、时间片用完）

![线程状态转换](./image/threadstatuschange.png "三生三世")

在调用sleep()方法的过程中，线程不会释放对象锁。
而当调用wait()方法的时候，线程会放弃对象锁，让出cpu该其他线程，进入等待此对象的等待锁定池，只有针对此对象调用notify()方法后本线程才进入对象锁定池准备




---------------------------------------------------------------------------------------------------------------------

Linux探秘之用户态与内核态
https://www.cnblogs.com/bakari/p/5520860.html
https://www.cnblogs.com/yuyang0920/p/7278446.html
http://blog.csdn.net/benjamin721/article/details/51316596
http://blog.csdn.net/ljlstart/article/details/51279184
http://blog.csdn.net/u014142287/article/details/51934940


Linux零拷贝zero-copy技术：减少数据在内核和用户空间之间拷贝次数
https://www.jianshu.com/p/fad3339e3448
https://www.cnblogs.com/metoy/p/4033366.html
http://blog.csdn.net/hzrandd/article/details/51025341
http://blog.csdn.net/linsongbin1/article/details/77650105
当应用程序访问某块数据时，操作系统首先会检查，是不是最近访问过此文件，文件内容是否缓存在内核缓冲区，如果是，操作系统则直接根据read系统调用提供的buf地址，将内核缓冲区的内容拷贝到buf所指定的用户空间缓冲区中去。如果不是，操作系统则首先将磁盘上的数据拷贝的内核缓冲区，这一步目前主要依靠DMA来传输，然后再把内核缓冲区上的内容拷贝到用户缓冲区中。
接下来，write系统调用再把用户缓冲区的内容拷贝到网络堆栈相关的内核缓冲区中，最后socket再把内核缓冲区的内容发送到网卡上。

减少拷贝次数的一种方法是调用mmap()来代替read调用
应用程序调用mmap()，磁盘上的数据会通过DMA被拷贝的内核缓冲区，接着操作系统会把这段内核缓冲区与应用程序共享，这样就不需要把内核缓冲区的内容往用户空间拷贝。应用程序再调用write(),操作系统直接将内核缓冲区的内容拷贝到socket缓冲区中，这一切都发生在内核态，最后，socket缓冲区再把数据发到网卡去。

内存映射文件File Mapping
在大文件或者非常频繁的文件操作中内存映射文件性能较高的原因
http://blog.csdn.net/whoamiyang/article/details/53365385
https://www.oschina.net/translate/10-things-to-know-about-memory-mapped-file-in-java
https://baike.baidu.com/item/%E5%86%85%E5%AD%98%E6%98%A0%E5%B0%84%E6%96%87%E4%BB%B6

内存映射文件的效率
原因是read()是系统调用，其中进行了数据 拷贝，它首先将文件内容从硬盘拷贝到内核空间的一个缓冲区，如图2中过程1，然后再将这些数据拷贝到用户空间，如图2中过程2，在这个过程中，实际上完成 了两次数据拷贝 ；而mmap()也是系统调用，如前所述，mmap()中没有进行数据拷贝，真正的数据拷贝是在缺页中断处理时进行的，由于mmap()将文件直接映射到用户空间，所以中断处理函数根据这个映射关系，直接将文件从硬盘拷贝到用户空间，只进行了 一次数据拷贝 。因此，内存映射的效率要比read/write效率高。 


数据共享编辑
文件数据共享
这种数据共享是让两个或多个进程映射同一文件映射对象的视图，即它们在共享同一物理存储页。这样，当一个进程向内存映射文件的一个视图写入数据时，其他的进程立即在自己的视图中看到变化。注意，对文件映射对象要使用同一名字。
访问方法
这样，文件内的数据就可以用内存读/写指令来访问，而不是用ReadFile和WriteFile这样的I/O系统函数，从而提高了文件存取速度。

为什么使用缓冲区读取文件会比不使用快: 
原因是每次进行IO操作,都要从用户态陷入内核态,由内核把数据从磁盘中读到内核缓冲区,再由内核缓冲区到用户缓冲区,如果没有buffer，读取都需要从用户态到内核态切换，而这种切换很耗时，所以，采用预读，减少IO次数，如果有buffer,根据局部性原理,就会一次多读数据,放到缓冲区中,减少了IO次数.


pageCache
https://blog.csdn.net/iter_zc/article/details/44195731
https://blog.csdn.net/kisimple/article/details/42559779

---------------------------------------------------------------------------------------------------------------------

String StringBuffer,StringBuilder原理

String对象有final修饰，是不可变的，也可以理解为常量，显然是线程安全的。

StringBuilder与StringBuffer有公共父类AbstractStringBuilder(抽象类)。

　　抽象类与接口的其中一个区别是：抽象类中可以定义一些子类的公共方法，子类只需要增加新的功能，不需要重复写已经存在的方法；而接口中只是对方法的申明和常量的定义。

　　StringBuilder、StringBuffer的方法都会调用AbstractStringBuilder中的公共方法，如super.append(...)。只是StringBuffer会在方法上加synchronized关键字，进行同步。

　　最后，如果程序不是多线程的，那么使用StringBuilder效率高于StringBuffer。
  因为StringBuilder是线程不安全的，直接调用了父类的方法，而StringBuffer中每个方法都加了synchronized，保证了线程安全，其他全部一样。
  
  StringBuffer与Stringbuilder用法完全相同，但是StringBuffer的线程是安全的，执行速度慢，StringBuilderd执行速度快，线程不安全，遇到多线程时，用StringBuffer。
  
  StringBuffer与StringBilder使用时都不改变字符串的地址。
  String使用时改变字符串地址。
  因为String是一个不可变的字符串，每次追加会生成一个新字符串，在最新的jdk中 + 也是调用StringBuilder来实现的
  而StringBuffer和StringBuilder是在原基础上追加。

---------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------





---------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------





---------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------













