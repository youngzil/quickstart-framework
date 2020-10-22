- [直接内存分配和释放](#直接内存分配和释放)
- [直接内存原理分析](#直接内存原理分析)


-------------------------------------------------------------------------
## 直接内存分配和释放

在我目前的接触到的Java中分配直接内存主要有如下三种方式:
1、Unsafe.allocateMemory()
2、ByteBuffer.allocateDirect()
3、native方法




Unsafe类：

Java提供了Unsafe类用来进行直接内存的分配与释放
public native long allocateMemory(long var1);
public native void freeMemory(long var1);




DirectByteBuffer类内存分配
虽然Unsafe可以通过反射调用来进行内存分配，但是按照其设计方式，它并不是给开发者来使用的，而且Unsafe里面的方法也十分原始，更像是一个底层设施。而其上层的封装则是DirectByteBuffer，这个才是最终留给开发者使用的。
DirectByteBuffer的分配是通过ByteBuffer.allocateDirect(int capacity)方法来实现的。

整个DirectByteBuffer分配过程中，比较需要关注的Bits.reserveMemory()和Cleaner,Deallocator，
其中Bits.reserveMemory()与分配相关，
Cleaner、Deallocator则与内存释放相关。

内存释放是通过Cleaner和Deallocator来实现的。




## 直接内存原理分析
查看ByteBuffer 源码可知 ByteBuffer.allocateDirect()创建DirectByteBuffer实例，DirectByteBuffer通过Unsafe分配内存，下面具体看一下执行过程。
1. 调用 ByteBuffer.allocateDirect(int cap)
2. 创建DirectByteBuffer：主要分三步，
    第一步调用Bits.reserveMemory(long size, int cap)) 在函数内部调用System.gc() 通知GC如有必要进行垃圾回收，第一次调用一般不会触发；
    第二步，调用Unsafe.allocateMemory(long var )方法分配内存；
    第三步，调用Cleaner.create(Object var0, Runnable var1) 创建Cleaner对象，用于回收内存。
3. Cleaner类继承自PhantomReference< Object>在此处保留Cleaner对象的虚引用。此类中还包含一个静态DirectByteBuffer引用队列用于得知那些虚引用所指向的对象已回收，这是一个很棒的设计因为jvm不知道堆外内存的使用情况，通过DirectByteBuffer对象的回收来间接控制堆外内存的回收。
4. 在 2 中System.gc() 给GC一个调用建议，如果在接下来的堆外内存分配中发现空间不足就会触发fullGC 。可以通过XX:MaxDirectMemorySize=40M来模拟。GC之后，“触发”调用Cleaner.clean() 方法，进而调用Deallocator.run() 在run方法中调用unsafe.freeMemory(long var1)释放堆外内存。
5. 为验证是否因为System.gc() 可在jvm启动参数加入-XX:+DisableExplicitGC禁用该代码。






native方法：
运行发现native方法分配的内存并不会产生DirectByteBuffer对象，同样的也不受-XX:MaxDirectMemorySize影响。






参考
https://juejin.im/post/5d04ffa6e51d45599e019db0
https://blog.csdn.net/Big_Blogger/article/details/77654240
https://www.cnblogs.com/duanxz/p/6089485.html



