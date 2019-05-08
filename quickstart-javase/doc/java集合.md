https://blog.csdn.net/touchSea/article/details/750923
https://www.cnblogs.com/lifelee/p/5306304.html
https://blog.csdn.net/u014136713/article/details/52089156
https://blog.csdn.net/evil_kyle/article/details/53291146
https://www.jianshu.com/p/589d58033841




List、Set、Map、Queue
并发集合：ConcurrentHashMap、ConcurrentSkipListSet、CopyOnWriteArrayList等



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



集合类HashMap
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
