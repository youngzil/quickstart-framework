JCTools简介

JVM的Java并发工具。该项目旨在提供JDK当前缺少的一些并发数据结构：


[JCTools官网](http://jctools.github.io/JCTools)  
[JCTools Github](https://github.com/JCTools/JCTools)  




使用示例  
[ShadeJCToolsSamples](https://github.com/JCTools/ShadeJCToolsSamples)  
[JCTools简介](https://my.oschina.net/go4it/blog/1528958)





1、并发队列的SPSC / MPSC / SPMC / MPMC变体：  
    SPSC-单一生产者单一消费者（免费，有界和无界）  
    MPSC-多生产者单个消费者（较少锁定，有界和无界）  
    SPMC-单生产者多消费者（锁定较少，有界）  
    MPMC-多生产者多消费者（较少锁定，有界）  

2、SPSC / MPSC链接阵列队列可在性能，分配和占用空间之间实现平衡

3、扩展的队列接口（MessagePassingQueue）：
    easeOffer / Peek / Poll：权衡对完整/空队列状态的混合保证和改进的性能。
    清空/填充：批量读取和写入方法可提高吞吐量并减少争用
    
    
JCTools是一款对jdk并发数据结构进行增强的并发工具，主要提供了map以及queue的增强数据结构。原来netty还是自己写的MpscLinkedQueueNode，后来新版本就换成使用JCTools的并发队列了。

增强map
- ConcurrentAutoTable(后面几个map/set结构的基础)
- NonBlockingHashMap
- NonBlockingHashMapLong
- NonBlockingHashSet
- NonBlockingIdentityHashMap
- NonBlockingSetInt


增强队列queue  
- SPSC - Single Producer Single Consumer (Wait Free, bounded and unbounded)
- MPSC - Multi Producer Single Consumer (Lock less, bounded and unbounded)
- SPMC - Single Producer Multi Consumer (Lock less, bounded)
- MPMC - Multi Producer Multi Consumer (Lock less, bounded)




// 为了&计算下标，初始化值为：容量-1
protected final long mask;
// 存放数据的数组
protected final E[] buffer;
// 生产者的索引，可能大于数组的长度
private volatile long producerIndex;
// 生产者的下标的最大值，动态变化，用来判断队列是否已满
private volatile long producerLimit;
// 消费者的索引，可能大于数组的长度
protected long consumerIndex;





源码解析  
https://blog.csdn.net/theludlows/article/details/90646236  
https://blog.csdn.net/youaremoon/article/details/50351929  


