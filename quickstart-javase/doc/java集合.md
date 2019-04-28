https://blog.csdn.net/touchSea/article/details/750923
https://www.cnblogs.com/lifelee/p/5306304.html
https://blog.csdn.net/u014136713/article/details/52089156
https://blog.csdn.net/evil_kyle/article/details/53291146
https://www.jianshu.com/p/589d58033841




List、Set、Map、Queue




Collection
├List
│├LinkedList  不安全的，可以通过List list = Collections.synchronizedList(new LinkedList(...));构建安全的
│├ArrayList  不安全的
│└Vector  安全的
│　└Stack
└Set   无序，不重复的

Map
├Hashtable  线程安全的
├HashMap    线程不安全的
└WeakHashMap  一种改进的HashMap，它对key实行“弱引用”，如果一个key不再被外部所引用，那么该key可以被GC回收。



Queue方法：
add、remove、element  抛异常
offer、poll、peek	 返回boolean
put、take  阻塞
队列：FIFO，栈：FILO




