以前在写各种处理Java OOM总结的ppt的时候，列到的OOM是以下这几种：

1.GC overhead limit exceeded
2.Java Heap Space
3.Unable to create new native thread
4.PermGen Space
5.Direct buffer memory
6.request {} bytes for {}. Out of swap space?
7、 Metaspace





https://blog.csdn.net/renfufei/article/details/76350794


1、java.lang.OutOfMemoryError: Java heap space 错误
原因：
1、超出预期的访问量/数据量
2、内存泄露(Memory leak).就是那些逻辑上不再使用的对象, 却没有被 垃圾收集程序 给干掉. 如ThreadLocal中因为异常导致没有remove，线程池中，线程一直存在，还会导致业务获取错误的数据


解决方案
1、哪类对象占用了最多内存？
2、这些对象是在哪部分代码中分配的。

搞清楚这一点，就要分析dump文件
用另一台机器来加载Dump文件。一般来说, 如果出问题的JVM内存是8GB, 那么分析 Heap Dump 的机器内存需要大于 8GB. 打开转储分析软件(我们推荐Eclipse MAT , 当然你也可以使用其他工具)。

Plumbr 工具（免费和收费版本）：
不需要其他工具和分析, 就能直接看到:
1、哪类对象占用了最多的内存(此处是 271 个 com.example.map.impl.PartitionContainer 实例, 消耗了 173MB 内存, 而堆内存只有 248MB)
2、这些对象在何处创建(大部分是在 MetricManagerImpl 类中,第304行处)
3、当前是谁在引用这些对象(从 GC root 开始的完整引用链)



2、java.lang.OutOfMemoryError: PermGen space 错误
永久代(Permanent Generation)内存区域已满，是加载到内存中的 class 数量太多或体积太大。


3、 java.lang.OutOfMemoryError: Metaspace 错误的主要原因, 是加载到内存中的 class 数量太多或者体积太大。
从Java 8开始,内存结构发生重大改变, 不再使用Permgen, 而是引入一个新的空间: Metaspace. 
错误的主要原因, 是加载到内存中的 class 数量太多或者体积太大。


4、java.lang.OutOfMemoryError: Direct buffer memory解决办法
用来nio，但是direct buffer不够



5、 java.lang.OutOfMemoryError: GC overhead limit exceeded 错误
执行垃圾收集的 时间比例太大, 有效的运算量太小. 默认情况下, 如果GC花费的时间超过 98%, 并且GC回收的内存少于 2%, JVM就 会抛出这个错误。

假如不抛出 GC overhead limit 错误会发生什么情况呢? 那就是GC清理的这么点内存很快会再 次填满, 迫使GC再次执行. 这样就形成恶性循环, CPU使用率一直是100%, 而GC却没有任何成果. 系统用户就会看 到系统卡死 ­ 以前只需要几毫秒的操作, 现在需要好几分钟才能完成。



6、java.lang.OutOfMemoryError: Unable to create new native thread 错误表达的意思是:程序创建的线程数量已达到上限值

JVM向操作系统申请创建新的 native thread(原生线程)时, 就有可能会碰到 java.lang.OutOfMemoryError: Unable to createnewnativethread错误.
如果底层操作系统创建新的nativethread失败, JVM就会抛出相应的 OutOfMemoryError. 
原生线程的数量受到具体环境的限制, 通过一些测试用例可以找出这些限制



7、 java.lang.OutOfMemoryError: Out of swap space? 表明, 交换空间(swap space,虚拟内存) 不足,是由于物 理内存和交换空间都不足所以导致内存分配失败。

操作系统的交换空间太小。
机器上的某个进程耗光了所有的内存资源。
当然也可能是应用程序的本地内存泄漏(native leak)引起的, 例如, 某个程序/库不断地申请本地内存,却不进行释放。


8、java.lang.OutOfMemoryError: Requested array size exceeds VM limit 错误, 就说明想要创建的 数组长度超过限制。



















