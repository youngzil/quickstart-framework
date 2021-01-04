- [NIO的介绍和分类](#NIO的介绍和分类)
- [NIO的模型：文件IO和网络IO](#NIO的模型：文件IO和网络IO)
- [Netty的高性能及NIO的epoll空轮询bug](#Netty的高性能及NIO的epoll空轮询bug)
- [Java NIO浅析](#Java-NIO浅析)
- [Java NIO的selector和linux的epoll select](#Java-NIO的selector和linux的epoll-select)
- [Java NIO使用](#Java-NIO使用)
    - [NIO核心部件：Channels、Buffers、Selectors](#NIO核心部件：Channels、Buffers、Selectors)
    - [Selectors:CONNECT、ACCEPT、READ、WRITE四个事件](#Selectors:CONNECT、ACCEPT、READ、WRITE四个事件)
    - [Channels实现](#Channels实现)
    - [Buffer实现](#Buffer实现)
    - [Buffer分配和读写](#Buffer分配和读写)
    - [Zero Copy零拷贝](#Zero-Copy零拷贝)
    - [内存映射文件](#内存映射文件)
- [Reactor与Proactor的区别](#Reactor与Proactor的区别)


-------------------------------------------------------------------------
## NIO的介绍和分类


BIO是面向流、阻塞IO，顺序读  
NIO面向缓冲、非阻塞IO、选择器Selector，可以使用position等跳跃读  

Channels：4种  
Buffers：1+2+3+1  
Selectors：4个事件  
Buffer分配：3种  
常用的方法：常用的读写切换、定位等  
buffer读写方法  
Buffer的capacity,position和limit、mark(读的时候)

直接内存MappedByteBuffer：不受young gc的影响，只有full gc的时候回收，当众多的DirectByteBuffer对象从新生代被送入老年代后触发了 full gc才会会释放回收，  
MappedByteBuffer在处理大文件时的确性能很高，但也存在一些问题，如内存占用、文件关闭不确定，被其打开的文件只有在垃圾回收的才会被关闭，而且这个时间点是不确定的。  
  
  
  
网络：  
SocketChannel:创建连接，读写数据，从channel到buffer，从buffer到channel  
ServerSocketChannel:监听连接，默认是阻塞模式，可以设置为非阻塞模式（while循环）  
  
  
零拷贝( zero-copy )  
文件IO：通过mmap实现的零拷贝I/O  
网络IO：FileChannel.transferTo 和 FileChannel.transferFrom方法  
  
Netty的高性能及NIO的epoll空轮询bug  


nio的模型：文件IO和网络IO


---------------------------------------------------------------------------------------------------------------------  
## NIO的模型：文件IO和网络IO

NIO的模型：
文件IO：三个变量怎么切换的，常用方法，内存整理

默认是写模式，从头开始写，position=0，limit=capacity
flip 切换为读，position=0，limit=写的最后位置position
compact()方法：将所有未读的数据拷贝到Buffer起始处，position=（读模式下的limit-position,也就是还剩下的未读内存），limit=capacity
clear()方法：position将被设回0，limit被设置成 capacity的值。换句话说，Buffer 被清空了  


这两个方法都是只有在读模式中间下才有用，因为flip、compact、clear都会重置mark
buffer.mark();//保存当前的position到mark，mark默认是-1
buffer.reset();//恢复position到之前的mark，如果mark=-1，说明没有执行过mark()，会报错InvalidMarkException


网络IO：
1、selector主要做了什么事情，轮训是谁实现的？jdk吗？不是JDK实现的，在不同的系统下，实现是不一样的，如Linux下是Epoll
2、selector的bug：空轮序，CPU100%，若Selector的轮询结果为空，也没有wakeup或新消息处理，则发生空轮询，CPU使用率100%，
3、线程、selector和channel的关系？selector可以跨线程还是在只能在一个线程？
4、Selector（选择器）介绍


1、selector主要做了什么事情，轮训是谁实现的？jdk吗？
用于检查一个或多个NIO Channel（通道）的状态是否处于可读、可写。
不同系统对应着不同的实现，在Java中，Selector这个类是select/epoll/poll的外包类，在不同的平台上，底层的实现可能有所不同，但其基本原理是一样的
linux系统下sun.nio.ch.DefaultSelectorProvider.create(); 会生成一个sun.nio.ch.EPollSelectorProvider类型的SelectorProvider，这里对应于linux系统的epoll


2、selector的bug：空轮序，CPU100%，若Selector的轮询结果为空，也没有wakeup或新消息处理，则发生空轮询，CPU使用率100%，


3、线程、selector和channel的关系？selector可以跨线程还是在只能在一个线程？
一个线程中,并不是必须只能有一个Selector.一个Selector也不是只能注册三个Channel.
理论上都可以是多对多的，但是下面这种有点问题：
一个Channel可以注册到多个不同的Selector中。【多个Selector同时监听到这个channel的同一个事件，怎么办？】
多个线程对应一个Selector【轮训到怎么处理，Selector的select是阻塞的，多个线程有问题，阻塞并且线程切换，完蛋了】

把多个IO阻塞复用到同一个select阻塞，单线程可以处理多个客户端请求，性能开销小，不需其他的进程或线程，节省资源
select是阻塞的，无论是通过操作系统的通知（epoll）还是不停的轮询(select，poll)，这个函数是阻塞的。所以你可以放心大胆地在一个while(true)里面调用这个函数而不用担心CPU空转。
NIO由原来的阻塞读写（占用线程）变成了单线程轮询事件，找到可以进行读写的网络描述符进行读写。除了事件的轮询是阻塞的（没有可干的事情必须要阻塞），剩余的I/O操作都是纯CPU操作，没有必要开启多线程。

1.事件分发器，单线程选择就绪的事件。
2.I/O处理器，包括connect、read、write等，这种纯CPU操作，一般开启CPU核心个线程就可以。
3.业务线程，在处理完I/O后，业务一般还会有自己的业务逻辑，有的还会有其他的阻塞I/O，如DB操作，RPC等。只要有阻塞，就需要单独的线程。

Java的Selector对于Linux系统来说，有一个致命限制：同一个channel的select不能被并发的调用。因此，如果有多个I/O线程，必须保证：一个socket只能属于一个IoThread，而一个IoThread可以管理多个socket。
一个NIO线程可以处理N个Channel，一个Channel对应一个NIO线程
另外连接的处理和读写的处理通常可以选择分开，这样对于海量连接的注册和读写就可以分发。虽然read()和write()是比较高效无阻塞的函数，但毕竟会占用CPU，如果面对更高的并发则无能为力。


4、Selector（选择器）介绍
Selector 一般称 为选择器 ，当然你也可以翻译为 多路复用器 。它是Java NIO核心组件中的一个，用于检查一个或多个NIO Channel（通道）的状态是否处于可读、可写。如此可以实现单线程管理多个channels,也就是可以管理多个网络链接。

不同系统对应着不同的sun.nio.ch.DefaultSelectorProvider
这里我们看linux下面的sun.nio.ch.DefaultSelectorProvider
public class DefaultSelectorProvider {
    /**
     * Prevent instantiation.
     */
    private DefaultSelectorProvider() { }
    /**
     * Returns the default SelectorProvider.
     */
    public static SelectorProvider create() {
        return new sun.nio.ch.EPollSelectorProvider();
    }
}
可以看见，linux系统下sun.nio.ch.DefaultSelectorProvider.create(); 会生成一个sun.nio.ch.EPollSelectorProvider类型的SelectorProvider，这里对应于linux系统的epoll



netty做了什么封装：传输（内存）、协议、线程（高效的Reactor线程模型）
netty线程模型：好处是什么？
work线程池有3个线程，对应几个selector？每个线程一个Selector，有什么优缺点？
一个线程对应一个selector，有什么好处和坏处吗？性能会提高吗？
如果太多的channel共用一个selector，io太多事件，会不会处理不过来，如果太多了，会排队吗？还是丢失？每个线程一个Selector、一个任务队列
boss 和 work 线程池是使用同一个selector吗，不是同一个，各处理什么事件？boss是连接，work是io读写事件，然后每个线程都是对应的一个selector，这样性能高一点
谁从网络上把数据接收到本地，是谁来做的，耗时在什么地方？
在什么时候处理IO时间


Acceptor 线程用于绑定监听端口，接收客户端连接，将SocketChannel 从主线程池的Reactor 线程的多路复用器上移除，重新注册到Sub 线程池的线程上，用于处理I/O 的读写等操作，从而保证mainReactor只负责接入认证、握手等操作；

每个NioEventLoop对应一个线程和一个Selector，NioServerSocketChannel会主动注册到某一个NioEventLoop的Selector上，NioEventLoop负责事件轮询。
NioEventLoop 继承于 SingleThreadEventExecutor, 而 SingleThreadEventExecutor 中有一个 Queue<Runnable> taskQueue 字段, 用于存放添加的 Task. 在 Netty 中, 每个 Task 都使用一个实现了 Runnable 接口的实例来表示.

EventLoop一直在不停的处理这三件事情：
1、首先轮询注册到Reactor线程对应的selector上的所有Channel的IO事件。（一个EventLoop会包含一个selector，NioEventLoop对应的selector就是原生java nio的selector）
2、处理产生网络IO事件的Channel。
3、处理任务队列。

Outbound 事件都是请求事件, 发起者是 Channel，处理者是 unsafe，通过 Outbound 事件进行通知，传播方向是 tail到head。
Inbound 事件发起者是 unsafe，事件的处理者是 Channel, 是通知事件，传播方向是从头到尾。

内存管理机制，首先会预申请一大块内存Arena，Arena由许多Chunk组成，而每个Chunk默认由2048个page组成。
Chunk通过AVL树的形式组织Page，每个叶子节点表示一个Page，而中间节点表示内存区域，节点自己记录它在整个Arena中的偏移地址。
当区域被分配出去后，中间节点上的标记位会被标记，这样就表示这个中间节点以下的所有节点都已被分配了。
大于8k的内存分配在poolChunkList中，而PoolSubpage用于分配小于8k的内存，它会把一个page分割成多段，进行内存分配。

ByteBuf的特点：支持自动扩容（4M），保证put方法不会抛出异常、通过内置的复合缓冲类型，实现零拷贝（zero-copy）；
不需要调用flip()来切换读/写模式，读取和写入索引分开；
方法链；引用计数基于AtomicIntegerFieldUpdater用于内存回收；
PooledByteBuf采用二叉树来实现一个内存池，集中管理内存的分配和释放，不用每次使用都新建一个缓冲区对象。
UnpooledHeapByteBuf每次都会新建一个缓冲区对象。


关于Netty我们都需要知道什么?
https://juejin.im/post/5c6eb30d518825629f385ae4
https://github.com/wangzhiwubigdata/God-Of-BigData
https://tech.meituan.com/2016/11/04/nio.html
https://www.kancloud.cn/ssj234/netty-source/434741
http://ifeve.com/non-blocking-server/
https://www.cnblogs.com/doit8791/p/7461479.html
https://segmentfault.com/a/1190000007403937


参考
https://www.jianshu.com/p/a4d35d218235

NIO网络编程参考
http://www.blogjava.net/landon/archive/2017/01/19/432272.html
https://blog.csdn.net/u010898743/article/details/79494290
https://blog.csdn.net/yhl_jxy/article/details/79332092
https://www.cnblogs.com/snailclimb/p/9086334.html
https://www.cnblogs.com/wuer888/p/10136759.html
https://www.cnblogs.com/cz123/p/8421660.html


官方JDK相关文档：https://docs.oracle.com/javase/8/docs/api/
谷歌搜索排名第一的Java NIO教程：http://tutorials.jenkov.com/java-nio/channels.html

《Java程序员修炼之道》：https://book.douban.com/subject/24841235/
ByteBuffer常用方法详解：https://blog.csdn.net/u012345283/article/details/38357851
Java NIO 易百教程：https://www.yiibai.com/java_nio/java-nio-buffers.html

Java NIO 与 IO之间的区别:https://blog.csdn.net/evan_man/article/details/50910542
《Java NIO》:https://book.douban.com/subject/1433583/
《Java 8编程官方参考教程（第9版）》:https://book.douban.com/subject/26320992/



  
---------------------------------------------------------------------------------------------------------------------  
## Java NIO使用
  
  
http://ifeve.com/overview/  
https://blog.csdn.net/column/details/15438.html  
  
  
jdk7新增的File操作类：  
WatchService  
Paths、Path  
Files  
FileSystems  
  
  
byte：8位，最大存储数据量是255，存放的数据范围是-128~127之间。  
char：16位，存储Unicode码，用单引号赋值。  
short：16位，最大数据存储量是65536，数据范围是-32768~32767之间。  
int：32位，最大数据存储容量是2的32次方减1，数据范围是负的2的31次方到正的2的31次方减1。  
long：64位，最大数据存储容量是2的64次方减1，数据范围为负的2的63次方到正的2的63次方减1。  
float：32位，数据范围在3.4e-45~1.4e38，直接赋值时必须在数字后加上f或F。  
double：64位，数据范围在4.9e-324~1.8e308，赋值时可以加d或D也可以不加。  
  
  
  
  
BIO是面向流、阻塞IO，顺序读  
NIO面向缓冲、非阻塞IO、选择器Selector，可以使用position等跳跃读  
  


### NIO核心部件：Channels、Buffers、Selectors
Java NIO 由以下几个核心部分组成：  
Channels  
Buffers  
Selectors  
  
  
### Selectors:CONNECT、ACCEPT、READ、WRITE四个事件


### Channels实现
Channels：这些通道涵盖了UDP 和 TCP 网络IO，以及文件IO。  
FileChannel 从文件中读写数据。  
DatagramChannel 能通过UDP读写网络中的数据。  
SocketChannel 能通过TCP读写网络中的数据。  
ServerSocketChannel可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。  


### Buffer实现
Buffer实现：Buffer覆盖了你能通过IO发送的基本数据类型：byte,char, short, int, long, float, double  
ByteBuffer：HeapByteBuffer、DirectByteBuffer  
  
CharBuffer  
  
ShortBuffer  
IntBuffer  
LongBuffer  
  
FloatBuffer  
DoubleBuffer  
  
Java NIO 还有个 MappedByteBuffer、  
  
缓冲区本质上是一块可以写入数据，然后可以从中读取数据的内存。这块内存被包装成NIO Buffer对象，并提供了一组方法，用来方便的访问该块内存。  


### Buffer分配和读写

Buffer分配：  
1、HeapByteBuffer:ByteBuffer.allocate(cap);  
2、DirectByteBuffer:ByteBuffer.allocateDirect(cap);  
3、MappedByteBuffer ：FileChannel的map方法，FileChannel.map(FileChannel.MapMode.READ_WRITE, 0, length);  
  
  
ByteBuffer：不做读写切换，会导致position和limit混乱，读写的数据也会混乱,如果导致position大于limit，报错BufferOverflowException  
1、初始化之后直接可以写（也可以直接读，读的都是0，也默认写进去0，就相当于写0进去并读出来，其实是position和limit混乱导致）  
2、写完之后使用flip()切换到读模式  
3、使用clear()（清除，丢弃未读的）或者compact()（整理，保留未读的，清除已读的）切换到写，rewind()方法重置rewind()方法将position重置为0开始位置  
4、使用flip()切换到读模式下，mark()标记当前读位置，继续读，reset()回到当初标记的位置  
5、rewind()方法：重读Buffer  
PS：先读再写（不使用clear或compact），会导致position往后移动，前面读的都变成0，就好像是写一样  
  
ByteBuffer和Channels交换：  
1、简单交换：read和write  
2、数组参数：分散（scatter）和 聚集（gather）  
3、零拷贝：transferFrom 和 transferTo  
  
FileChannel:mmap方法（内存映射文件）、position导致空洞文件，truncate()截取，force()持久化  
FileChannel.read()方法将数据从FileChannel读取到Buffer中  
FileChannel.write()方法向FileChannel写数据  
FileChannel的position方法:可能导致“文件空洞”，磁盘上物理文件中写入的数据间有空隙。  
FileChannel实例的size()方法将返回该实例所关联文件的大小  
FileChannel.truncate()方法截取一个文件  
FileChannel.force()方法将通道里尚未写入磁盘的数据强制写到磁盘上  
关闭FileChannel.close()用完FileChannel后必须将其关闭  




### Zero Copy零拷贝

transferFrom方法和transferTo方法:零拷贝  
toChannel.transferFrom(position, count, fromChannel);  
fromChannel.transferTo(position, count, toChannel);  
  
Buffer的capacity,position和limit  
position和limit的含义取决于Buffer处在读模式还是写模式。不管Buffer处在什么模式，capacity的含义总是一样的。  
  
capacity:Buffer的容量  
position:读或者写的当前位置  
limit:  
在写模式下，Buffer的limit表示你最多能往Buffer里写多少数据。 写模式下，limit等于Buffer的capacity  
读模式时， limit表示你最多能读到多少数据，你能读到之前写入的所有数据（limit被设置成已写数据的数量，这个值在写模式下就是position）  
  
Buffer的分配：每一个Buffer类都有一个allocate方法  
下面是一个分配48字节capacity的ByteBuffer的例子。  
ByteBuffer buf = ByteBuffer.allocate(48);  
  
  
常用的方法：  
1、分配的：allocate、allocateDirect、map（truncate()截取，force()持久化）  
2、读写切换的：flip()、clear()与compact()方法  
3、读写的：put、get、channel的 read、write  
4、定位的：capacity,position和limit、rewind()、mark()与reset()  
5、channel传输的：transferTo、transferFrom  


默认是写模式，从头开始写，position=0，limit=capacity
flip 切换为读，position=0，limit=写的最后位置position
compact()方法：将所有未读的数据拷贝到Buffer起始处，position=（读模式下的limit-position,也就是还剩下的未读内存），limit=capacity
clear()方法：position将被设回0，limit被设置成 capacity的值。换句话说，Buffer 被清空了  


这两个方法都是只有在读模式中间下才有用，因为flip、compact、clear都会重置mark
buffer.mark();//保存当前的position到mark，mark默认是-1
buffer.reset();//恢复position到之前的mark，如果mark=-1，说明没有执行过mark()，会报错InvalidMarkException



  
  
flip()方法：从写模式转为读模式，会使得limit=position，position=0，也就是会让position从头开始，limit=调用方法之前的position，  
在读模式下调用flip()会出错  
  
向Buffer中写数据：  
1、从Channel写到Buffer。int bytesRead = inChannel.read(buf); //read into buffer.  
2、通过Buffer的put()方法写到Buffer里。buf.put(127);  
  
从Buffer中读取数据：  
1、从Buffer读取数据到Channel。int bytesWritten = inChannel.write(buf);//read from buffer into channel.  
2、使用get()方法从Buffer中读取数据。byte aByte = buf.get();  
  
rewind()方法：重读Buffer，Buffer.rewind()将position设回0，所以你可以重读Buffer中的所有数据。limit保持不变，仍然表示能从Buffer中读取多少个元素（byte、char等）。  
  
clear()与compact()方法：清除缓存，clear清楚全部，compact()清楚已读，其实都是逻辑清楚，Buffer中的数据未被清除，但是compact()会覆盖前面部分数据  
clear()方法，position将被设回0，limit被设置成 capacity的值。换句话说，Buffer 被清空了  
compact()方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。limit属性依然像clear()方法一样，设置成capacity。现在Buffer准备好写数据了，但是不会覆盖未读的数据。  
  
mark()与reset()方法  
通过调用Buffer.mark()方法，可以标记Buffer中的一个特定position。之后可以通过调用Buffer.reset()方法恢复到这个position  
  
  
equals()与compareTo()方法  
可以使用equals()和compareTo()方法两个Buffer。  
  
equals()  
当满足下列条件时，表示两个Buffer相等：  
1、有相同的类型（byte、char、int等）。  
2、Buffer中剩余的byte、char等的个数相等。  
3、Buffer中所有剩余的byte、char等都相同。  
如你所见，equals只是比较Buffer的一部分，不是每一个在它里面的元素都比较。实际上，它只比较Buffer中的剩余元素。  
  
compareTo()方法  
compareTo()方法比较两个Buffer的剩余元素(byte、char等)， 如果满足下列条件，则认为一个Buffer“小于”另一个Buffer：  
1、第一个不相等的元素小于另一个Buffer中对应的元素 。  
2、所有元素都相等，但第一个Buffer比另一个先耗尽(第一个Buffer的元素个数比另一个少)。  
（译注：剩余元素是从 position到limit之间的元素）  
  
  
分散（scatter）从Channel中读取是指在读操作时将读取的数据写入多个buffer中。因此，Channel将从Channel中读取的数据“分散（scatter）”到多个Buffer中。  
聚集（gather）写入Channel是指在写操作时将多个buffer的数据写入同一个Channel，因此，Channel 将多个Buffer中的数据“聚集（gather）”后发送到Channel。  
  
分散（scatter）：channel.read(bufferArray);按照bufferArray的顺序读取channel的数据到buffer，前面的buffer必须写满，适用前面是定长，最后一个可以不定长的buffer，比如消息头定长，消息体不定长的buffer，分别读取  
聚集（gather）：channel.write(bufferArray);按照bufferArray的顺序写入到channel，只有position和limit之间的数据才会被写入，能较好的处理动态消息。  
  
在Java NIO中，如果两个通道中有一个是FileChannel，那你可以直接将数据从一个channel（译者注：channel中文常译作通道）传输到另外一个channel。  
transferFrom方法和transferTo方法:zero copy零拷贝  
toChannel.transferFrom(position, count, fromChannel);  
fromChannel.transferTo(position, count, toChannel);  
  
  
Selectors:  
一个server socket channel准备好接收新进入的连接称为“接收就绪”。一个有数据可读的通道可以说是“读就绪”。等待写数据的通道可以说是“写就绪”。  
这四种事件用SelectionKey的四个常量来表示：  
SelectionKey.OP_CONNECT  
SelectionKey.OP_ACCEPT  
SelectionKey.OP_READ  
SelectionKey.OP_WRITE  
  
  
FileChannel:  
FileChannel.read()方法将数据从FileChannel读取到Buffer中  
FileChannel.write()方法向FileChannel写数据  
FileChannel的position方法:可能导致“文件空洞”，磁盘上物理文件中写入的数据间有空隙。  
FileChannel实例的size()方法将返回该实例所关联文件的大小  
FileChannel.truncate()方法截取一个文件  
FileChannel.force()方法将通道里尚未写入磁盘的数据强制写到磁盘上  
关闭FileChannel.close()用完FileChannel后必须将其关闭  
  
  
  
  
SocketChannel:创建连接，读写数据，从channel到buffer，从buffer到channel  
ServerSocketChannel:监听连接，默认是阻塞模式，可以设置为非阻塞模式（while循环）  
  
SocketChannel:打开 SocketChannel：socketChannel.connect  
ServerSocketChannel：打开ServerSocketChannel.open();  
监听新进来的连接serverSocketChannel.accept()  
  
  
DatagramChannel：  
通过receive()方法从DatagramChannel接收数据  
通过send()方法从DatagramChannel发送数据  
  
  
BIO是面向流、阻塞IO  
NIO面向缓冲、非阻塞IO、选择器Selector  
  


### 内存映射文件
内存映射文件：map()  
（1）直接内存DirectMemory的大小默认为 -Xmx 的JVM堆的最大值，但是并不受其限制，而是由JVM参数 MaxDirectMemorySize单独控制。  
（2）直接内存不是分配在JVM堆中。并且直接内存不受 GC(新生代的Minor GC)影响，只有当执行老年代的 Full GC时候才会顺便回收直接内存！  
    而直接内存是通过存储在JVM堆中的DirectByteBuffer对象来引用的，所以当众多的DirectByteBuffer对象从新生代被送入老年代后才触发了 full gc。  
（3）MappedByteBuffer在处理大文件时的确性能很高，但也存在一些问题，如内存占用、文件关闭不确定，被其打开的文件只有在垃圾回收的才会被关闭，而且这个时间点是不确定的。  
  
  
关于零拷贝的一点认识  
https://juejin.im/post/5cad6f1ef265da039f0ef5df  
  
  
  
## Java NIO的selector和linux的epoll select
  
https://www.cnblogs.com/jukan/p/5272257.html  
http://blog.csdn.net/u010853261/article/details/53464475  
选择器的创建  
当调用Selector.open()时，选择器通过专门的工厂SelectorProvider来创建Selector的实现，SelectorProvider屏蔽了不同操作系统及版本创建实现的差异性。  
public static Selector open() throws IOException {  
    return SelectorProvider.provider().openSelector();  
}  
这里系统默认的provider在不同系统上是不一样的，在MacOSX上是默认的KQueueSelectorProvider  
因为SelectorProvider本身为一个抽象类，通过调用provider()提供对应的Provider实现，如PollSelectorProvider、EPollSelectorProvider  
这是linux操作系统下的DefaultSelectorProvider的实现，可以看到，如果内核版本>=2.6则，具体的SelectorProvider为EPollSelectorProvider，否则为默认的PollSelectorProvider  
结合上文，可以猜测一下EPollSelectorProvider提供的Selector肯定是与内核epoll有关的，PollSelectorProvider提供的Selector肯定是与poll有关的。  
  
linux下select/poll/epoll机制的比较  
https://www.cnblogs.com/zhaodahai/p/6831456.html  
FD：文件描述符（file descriptor）  
  
epoll的优点：  
1、没有最大并发连接的限制，能打开的FD的上限远大于1024（1G的内存上能监听约10万个端口）；  
2、效率提升，不是轮询的方式，不会随着FD数目的增加效率下降。只有活跃可用的FD才会调用callback函数；  
即Epoll最大的优点就在于它只管你“活跃”的连接，而跟连接总数无关，因此在实际的网络环境中，Epoll的效率就会远远高于select和poll。  
3、 内存拷贝，利用mmap()文件映射内存加速与内核空间的消息传递；即epoll使用mmap减少复制开销。  
  
  
http://blog.csdn.net/shallwake/article/details/5265287  
那么，为什么epoll,kqueue比select高级？   
答案是，他们无轮询。因为他们用callback取代了。  
  
  
  
  
Java nio学习的代码案例，常用模式  
ibmdw.nio 包下的源码来源于 http://www.ibm.com/developerworks/cn/education/java/j-nio/index.html  
    这个是我的 nio 入门文档  
Java NIO tutorial   http://tutorials.jenkov.com/java-nio/index.html  
http://www.javaworld.com/javaworld/jw-10-2012/121016-maximize-java-nio-and-nio2-for-application-responsiveness.html  
  
  
  
---------------------------------------------------------------------------------------------------------------------  
  
参考  
https://www.cnblogs.com/JAYIT/p/8241634.html  
http://www.voidcn.com/article/p-rzokhbzl-zh.html  
https://blog.csdn.net/zhangjunli/article/details/89382006  
  
  
  
## Netty的高性能及NIO的epoll空轮询bug  
  
  
Selector BUG出现的原因  
若Selector的轮询结果为空，也没有wakeup或新消息处理，则发生空轮询，CPU使用率100%，  
  
Netty的解决办法  
1、对Selector的select操作周期进行统计，每完成一次空的select操作进行一次计数，  
2、若在某个周期内连续发生N次空轮询，则说明触发了JDK NIO的epoll死循环bug。  
3、重建Selector，判断是否是其他线程发起的重建请求，若不是则将原SocketChannel从旧的Selector上去除注册，重新注册到新的Selector上，并将原来的Selector关闭。  
  
  
创建新的Selector，将出现bug的Selector上的channel重新注册到新的Selector上。  关闭bug的Selector，使用新的Selector进行替换。  



Netty的解决办法：对Selector的select操作周期进行统计，每完成一次空的select操作进行一次计数，若在某个周期内连续发生N次空轮询，则触发了epoll死循环bug。
重建Selector，判断是否是其他线程发起的重建请求，若不是则将原SocketChannel从旧的Selector上去除注册，重新注册到新的Selector上，并将原来的Selector关闭。

  
  
  
  
---------------------------------------------------------------------------------------------------------------------
  
## Java NIO浅析
  
BIO模型：线程池：单请求单线程，请求:线程=1:1  
  
这个模型最本质的问题在于，严重依赖于线程。但线程是很”贵”的资源，主要表现在：   
1. 线程的创建和销毁成本很高，在Linux这样的操作系统中，线程本质上就是一个进程。创建和销毁都是重量级的系统函数。   
2. 线程本身占用较大内存，像Java的线程栈，一般至少分配512K～1M的空间，如果系统中的线程数过千，恐怕整个JVM的内存都会被吃掉一半。   
3. 线程的切换成本是很高的。操作系统发生线程切换的时候，需要保留线程的上下文，然后执行系统调用。如果线程数过高，可能执行线程切换的时间甚至会大于线程执行的时间，这时候带来的表现往往是系统load偏高、CPU sy使用率特别高（超过20%以上)，导致系统几乎陷入不可用的状态。   
4. 容易造成锯齿状的系统负载。因为系统负载是用活动线程数或CPU核心数，一旦线程数量高但外部网络环境不是很稳定，就很容易造成大量请求的结果同时返回，激活大量阻塞线程从而使系统负载压力过大。  
  
  
  
BIO模型，之所以需要多线程，是因为在进行I/O操作的时候，一是没有办法知道到底能不能写、能不能读，只能”傻等”，即使通过各种估算，算出来操作系统没有能力进行读写，也没法在socket.read()和socket.write()函数中返回，这两个函数无法进行有效的中断。所以除了多开线程另起炉灶，没有好的办法利用CPU。  
  
NIO的读写函数可以立刻返回，这就给了我们不开线程利用CPU的最好机会：如果一个连接不能读写（socket.read()返回0或者socket.write()返回0），我们可以把这件事记下来，记录的方式通常是在Selector上注册标记位，然后切换到其它就绪的连接（channel）继续进行读写。  
  
  
  
  
NIO线程模型主要包括以下几种：   
1. 事件分发器，单线程选择就绪的事件。   
2. I/O处理器，包括connect、read、write等，这种纯CPU操作，一般开启CPU核心个线程就可以。   
3. 业务线程，在处理完I/O后，业务一般还会有自己的业务逻辑，有的还会有其他的阻塞I/O，如DB操作，RPC等。只要有阻塞，就需要单独的线程。  
  
  


## Reactor与Proactor的区别

事件分发器的两种模式称为：Reactor和Proactor  
两种I/O多路复用模式：Reactor和Proactor  

Reactor模式是基于同步I/O的，而Proactor模式是采用异步I/O相关的。  



### I/O多路复用机制
一般地,I/O多路复用机制都依赖于一个事件多路分离器(Event Demultiplexer)。  
分离器对象可将来自事件源的I/O事件分离出来，并分发到对应的read/write事件处理器(Event Handler)。  
开发人员预先注册需要处理的事件及其事件处理器（或回调函数）；事件分离器负责将请求事件传递给事件处理器。  
两个与事件分离器有关的模式是Reactor和Proactor。Reactor模式采用同步IO，而Proactor采用异步IO。  



### Reactor模式
在Reactor中，事件分离器负责等待文件描述符或socket为读写操作准备就绪，然后将就绪事件传递给对应的处理器，最后由处理器负责完成实际的读写工作。

在Reactor模式中，事件分发器等待某个事件或者可应用或个操作的状态发生（比如文件描述符可读写，或者是socket可读写），事件分发器就把这个事件传给事先注册的事件处理函数或者回调函数，由后者来做实际的读写操作。  


### Proactor模式
而在Proactor模式中，处理器--或者兼任处理器的事件分离器，只负责发起异步读写操作。IO操作本身由操作系统来完成。  
传递给操作系统的参数需要包括用户定义的数据缓冲区地址和数据大小，操作系统才能从中得到写出操作所需数据，或写入从socket读到的数据。  
事件分离器捕获IO操作完成事件，然后将事件传递给对应处理器。比如，在windows上，处理器发起一个异步IO操作，再由事件分离器等待IOCompletion事件。  
典型的异步模式实现，都建立在操作系统支持异步API的基础之上，我们将这种实现称为“系统级”异步或“真”异步，因为应用程序完全依赖操作系统执行真正的IO工作。


而在Proactor模式中，事件处理者（或者代由事件分发器发起）直接发起一个异步读写操作（相当于请求），而实际的工作是由操作系统来完成的。  
发起时，需要提供的参数包括用于存放读到数据的缓存区、读的数据大小或用于存放外发数据的缓存区，以及这个请求完后的回调函数等信息。  
事件分发器得知了这个请求，它默默等待这个请求的完成，然后转发完成事件给相应的事件处理者或者回调。  
举例来说，在Windows上事件处理者投递了一个异步IO操作（称为overlapped技术），事件分发器等IO Complete事件完成。这种异步模式的典型实现是基于操作系统底层异步API的，所以我们可称之为“系统级别”的或者“真正意义上”的异步，因为具体的读写是由操作系统代劳的。  
  


### Reactor与Proactor流程

标准/典型的Reactor：  
步骤0：注册读就绪事件和相应的事件处理器
步骤1：事件分离器等待事件到来（Reactor负责）。  
步骤2：事件到来，激活分离器，分离器调用事件对应的处理器。将读就绪事件分发给用户定义的处理器（Reactor负责）。  
步骤3：事件处理器完成实际的读操作，读数据（用户处理器负责）。  
步骤4：处理读到的数据，注册新的事件，然后返还控制权。（用户处理器负责）。  
  
改进实现的模拟Proactor：  
步骤1：等待事件到来（Proactor负责）。  
步骤2：得到读就绪事件，执行读数据（现在由Proactor负责）。  
步骤3：将读完成事件分发给用户处理器（Proactor负责）。  
步骤4：处理数据（用户处理器负责）。  

- 处理器发起异步读操作（注意：操作系统必须支持异步IO）。在这种情况下，处理器无视IO就绪事件，它关注的是完成事件。
- 事件分离器等待操作完成事件
- 在分离器等待过程中，操作系统利用并行的内核线程执行实际的读操作，并将结果数据存入用户自定义缓冲区，最后通知事件分离器读操作完成。
- 事件分离器呼唤处理器。
- 事件处理器处理用户自定义缓冲区中的数据，然后启动一个新的异步操作，并将控制权返回事件分离器。


### Reactor与Proactor比较

可以看出，两个模式的相同点，都是对某个IO事件的事件通知(即告诉某个模块，这个IO操作可以进行或已经完成)。

在结构上，两者也有相同点：demultiplexor负责提交IO操作(异步)、查询设备是否可操作(同步)，然后当条件满足时，就回调handler；

不同点在于，异步情况下(Proactor)，当回调handler时，表示IO操作已经完成；同步情况下(Reactor)，回调handler时，表示IO设备可以进行某个操作(can read or can write)。




通俗理解  
使用Proactor框架和Reactor框架都可以极大的简化网络应用的开发，但它们的重点却不同。

Reactor框架中用户定义的操作是在实际操作之前调用的。比如你定义了操作是要向一个SOCKET写数据，那么当该SOCKET可以接收数据的时候，你的操作就会被调用；而Proactor框架中用户定义的操作是在实际操作之后调用的。比如你定义了一个操作要显示从SOCKET中读入的数据，那么当读操作完成以后，你的操作才会被调用。

Proactor和Reactor都是并发编程中的设计模式。在我看来，他们都是用于派发/分离IO操作事件的。这里所谓的IO事件也就是诸如read/write的IO操作。"派发/分离"就是将单独的IO事件通知到上层模块。

两个模式不同的地方在于，Proactor用于异步IO，而Reactor用于同步IO。





参考  
[Reactor与Proactor的区别](https://blog.csdn.net/windows_nt/article/details/25151049)  
https://tech.meituan.com/2016/11/04/nio.html  
[Java NIO浅析](https://tech.meituan.com/2016/11/04/nio.html)  



[IO多路复用机制(Reactor模式)](https://www.cnblogs.com/syhx/p/9809047.html)  
[IO多路复用之Reactor](https://www.cnblogs.com/amei0/p/8117660.html)  
[IO多路复用和Reactor模式](https://blog.csdn.net/aigoogle/article/details/38012075)  
[Java NIO多路复用(I/O multiplexing)](https://cs.xieyonghui.com/java/java-nio-multiplexing_33.html)  
[Netty这些我必问](https://www.lagou.com/lgeduarticle/71048.html)  


---------------------------------------------------------------------------------------------------------------------  
