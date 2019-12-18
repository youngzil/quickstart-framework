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




