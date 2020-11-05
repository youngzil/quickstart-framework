- [无锁队列Disruptor](#无锁队列Disruptor)
- [环形缓冲区的实现原理（ring buffer）](#环形缓冲区的实现原理（ring buffer）)




---------------------------------------------------------------------------------------------------------------------

## 无锁队列Disruptor


[LMAX官网](https://www.lmax.com/)  
[LMAX Disruptor官网](http://lmax-exchange.github.io/disruptor/)  
[Disruptor Github](https://github.com/LMAX-Exchange/disruptor)  


High Performance Inter-Thread Messaging Library  
高性能线程间消息传递库  


Disruptor它是一个开源的并发框架，并获得2011 Duke’s 程序框架创新奖，能够在无锁的情况下实现网络的Queue并发操作。


Disruptor相对于传统方式的优点：
- 1、没有竞争=没有锁=非常快。
- 2、所有访问者都记录自己的序号的实现方式，允许多个生产者与多个消费者共享相同的数据结构。
- 3、在每个对象中都能跟踪序列号（ring buffer，claim Strategy，生产者和消费者），加上神奇的cache line padding，就意味着没有为伪共享和非预期的竞争。


Disruptor  
面对复杂的系统，能够支撑稳定的高并发，是我们每个coder心目中追求的极致。  
Disruptor是一个高性能的异步处理框架，或者可以认为是最快的消息框架（轻量的JMS），也可以认为是一个观察者模式实现，或者事件-监听模式的实现，直接称disruptor模式。

Disruptor最大特点是高性能，异步低延时，更少的java gc，实现无锁Ring Buffer, 可以高效地实现控制高并发.

应用场景：在一些获取验证码、发短信、发送邮件的场景下，对实时性要求不够，如果收不到，用户可以再次要求重发；对于一些奖品、抢购、卡券、积分的发放，在高峰期，可以只入队，之后用异步的方式慢慢发放；对于比较复杂的逻辑可以进行并发操作。




[带中文注释的源码github地址](https://github.com/daoqidelv/disruptor)


http://ifeve.com/disruptor/
http://ifeve.com/disruptor-getting-started/
http://ifeve.com/dissecting-disruptor-wiring-up-cn/
http://ifeve.com/disruptor-2-change/
http://ifeve.com/transation-plan-2/
https://my.oschina.net/u/1765168?tab=newest&catalogId=5769037
https://lihaoquan.me/2017/4/21/ring-buffer-in-go.html
http://tutorials.jenkov.com/java-performance/ring-buffer.html




http://ifeve.com/dissecting-disruptor-whats-so-special/
http://ifeve.com/tag/ringbuffer/
https://my.oschina.net/u/1765168/blog/1807887
https://github.com/coffeemug




---------------------------------------------------------------------------------------------------------------------

## 环形缓冲区的实现原理（ring buffer）
按照严格的先进先出顺序进行处理，这是环形缓冲区的使用必须遵守的一项要求。

在通信程序中，经常使用环形缓冲区作为数据结构来存放通信中发送和接收的数据。环形缓冲区是一个先进先出的循环缓冲区，可以向通信程序提供对缓冲区的互斥访问。

1、环形缓冲区的实现原理

环形缓冲区通常有一个读指针和一个写指针。  
读指针指向环形缓冲区中可读的数据，写指针指向环形缓冲区中可写的缓冲区。  
通过移动读指针和写指针就可以实现缓冲区的数据读取和写入。

在通常情况下，环形缓冲区的读用户仅仅会影响读指针，而写用户仅仅会影响写指针。

如果仅仅有一个读用户和一个写用户，那么不需要添加互斥保护机制就可以保证数据的正确性。

如果有多个读写用户访问环形缓冲区，那么必须添加互斥保护机制来确保多个用户互斥访问环形缓冲区。





---------------------------------------------------------------------------------------------------------------------




