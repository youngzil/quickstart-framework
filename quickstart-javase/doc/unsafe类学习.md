https://www.cnblogs.com/pkufork/p/java_unsafe.html
https://www.cnblogs.com/mickole/articles/3757278.html
http://www.importnew.com/14511.html
https://blog.csdn.net/zhxdick/article/details/52003123

线程池、队列的超时等待：UNSAFE.park(false, nanos)、unpark，
nio的DirectByteBuffer内存分配：unsafe.allocateMemory(size)，freeMemory(long)
原子类的cas方法：unsafe.compareAndSwapInt方法、unsafe.compareAndSwapLong

1、通过Unsafe类可以分配内存，可以释放内存；
类中提供的3个本地方法allocateMemory、reallocateMemory、freeMemory分别用于分配内存，扩充内存和释放内存，与C语言中的3个方法对应。
2、CAS操作
是通过compareAndSwapXXX方法实现的
3、挂起与恢复
将一个线程进行挂起是通过park方法实现的，调用 park后，线程将一直阻塞直到超时或者中断等条件出现。unpark可以终止一个挂起的线程，使其恢复正常。整个并发框架中对线程的挂起操作被封装在 LockSupport类中，LockSupport类中有各种版本pack方法，但最终都调用了Unsafe.park()方法。
4、数组操作。
这部分包括了arrayBaseOffset（获取数组第一个元素的偏移地址）、arrayIndexScale（获取数组中元素的增量地址）等方法。arrayBaseOffset与arrayIndexScale配合起来使用，就可以定位数组中每个元素在内存中的位置。
由于Java的数组最大值为Integer.MAX_VALUE，使用Unsafe类的内存分配方法可以实现超大数组。实际上这样的数据就可以认为是C数组，因此需要注意在合适的时间释放内存。


