1、java集合整体和并发集合
2、HashMap和ConcurrentHashMap在1.7和1.8的区别
3、LinkedHashMap如何保证元素迭代的顺序
利用LinkedHashMap实现LRU算法缓存
4、集合类HashMap详解
5、fail-fast机制/快速失败机制
6、延时队列DelayQueue
7、





---------------------------------------------------------------------------------------------------------------------
https://blog.csdn.net/touchSea/article/details/750923
https://www.cnblogs.com/lifelee/p/5306304.html
https://blog.csdn.net/u014136713/article/details/52089156
https://blog.csdn.net/evil_kyle/article/details/53291146
https://www.jianshu.com/p/589d58033841


深入 DelayQueue 内部实现参考
https://www.zybuluo.com/mikumikulch/note/712598
https://blog.csdn.net/zsj777/article/details/82260227


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


---------------------------------------------------------------------------------------------------------------------
集合类HashMap详解
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
   

hash冲突解决：
java中hashmap是通过数据+链表/红黑树的方式来解决的


总结：
1、HashMap 的 key 和 value 都可以是 null
2、Map 的 key 和 value 都 不允许 是 基本数据类型
3、HashMap 的 key 可以是 任意对象，但如果 对象的 hashCode 改变了，那么将找不到原来该 key 所对应的 value

---------------------------------------------------------------------------------------------------------------------
HashMap和ConcurrentHashMap在1.7和1.8的区别
https://my.oschina.net/hosee/blog/618953
https://blog.csdn.net/qq296398300/article/details/79074239
https://blog.csdn.net/WuJun_025/article/details/88541966
https://blog.csdn.net/bolang789/article/details/79855053

HashMap：

JDK7中的HashMap：位桶+链表
JDK8：位桶+链表/红黑树

JDK7中HashMap采用的是位桶+链表的方式，即我们常说的散列链表的方式，
而JDK8中采用的是位桶+链表/红黑树（有关红黑树请查看红黑树）的方式，也是非线程安全的。当某个位桶的链表的长度达到某个阀值的时候，这个链表就将转换成红黑树。



ConcurrentHashMap：
总结
其实可以看出JDK1.8版本的ConcurrentHashMap的数据结构已经接近HashMap，相对而言，ConcurrentHashMap只是增加了同步的操作来控制并发，
从JDK1.7版本的ReentrantLock+Segment+HashEntry，
到JDK1.8版本中synchronized+CAS+HashEntry+红黑树。

1.数据结构：取消了Segment分段锁的数据结构，取而代之的是数组+链表+红黑树的结构。
2.保证线程安全机制：JDK1.7采用segment的分段锁机制实现线程安全，其中segment继承自ReentrantLock。JDK1.8采用CAS+Synchronized保证线程安全。
3.锁的粒度：原来是对需要进行数据操作的Segment加锁，现调整为对每个数组元素加锁（Node）（首节点）
4.链表转化为红黑树:定位结点的hash算法简化会带来弊端,Hash冲突加剧,因此在链表节点数量大于8时，会将链表转化为红黑树进行存储。
5.查询时间复杂度：从原来的遍历链表O(n)，变成遍历红黑树O(logN)。


JDK6与JDK7中的实现：分段锁并发、rehash优化、get和containsKey的弱一致性
ConcurrentHashMap采用了分段锁的设计
实际上就是ConcurrentHashMap中的分段锁个数，即Segment[]的数组长度。ConcurrentHashMap默认的并发度为16
如果并发度设置的过小，会带来严重的锁竞争问题；如果并发度设置的过大，原本位于同一个Segment内的访问会扩散到不同的Segment中，CPU cache命中率会下降，从而引起程序性能下降。
rehash：由于扩容是基于2的幂指来操作，因此大多数HashEntry节点在扩容前后index可以保持不变，避免让所有的节点都进行复制操作
get和containsKey返回的可能是过时的数据，这一点是ConcurrentHashMap在弱一致性上的体现。如果要求强一致性，那么必须使用Collections.synchronizedMap()方法。


JDK8中的实现：
摒弃了Segment（锁段）的概念，而是启用了一种全新的方式实现,利用CAS算法

主要设计上的变化有以下几点: 
1、不采用segment而采用node，锁住node来实现减小锁粒度。
2、设计了MOVED状态 当resize的中过程中 线程2还在put数据，线程2会帮助resize。
3、使用3个CAS操作来确保node的一些操作的原子性，这种方式代替了锁。
4、sizeCtl的不同值来代表不同含义，起到了控制的作用。
至于为什么JDK8中使用synchronized而不是ReentrantLock，我猜是因为JDK8中对synchronized有了足够的优化吧。


---------------------------------------------------------------------------------------------------------------------
2、一致性hash的实现原理，hashmap和concurrenthashmap的区别，扩容的不安全体现，死循环问题

1：问：讲讲对HashMap的认识？hashmap的初试容量及每次扩容因子？
回答：HashMap底层用的是哈希表的map接口，Hashmap储存数据的方式是以K-V的形式存在的；HashMap初始容量大小为16，扩容因子为2;
2：问：是否是线程安全？ 
答：不安全。
3：问：为什么不安全？
2-1：HashMap底层是一个Entry数组，存在的一些问题如：
2-2：resize死循环，导致CPU100%
2-3：如果在使用迭代器的过程中有其他线程修改了map，那么将抛出ConcurrentModificationException，这就是所谓fail-fast策略。


2.HashMap 是不是有序的，有哪些有序的Map？为什么TreeMap 是有序的？

HashMap是不是线程安全的？若不是，如何实现线程安全问题？加sychronized，要如何加？

CurrentHashMap如何实现线程安全的？内部每个segement是如何实现线程安全的？


---------------------------------------------------------------------------------------------------------------------
http://www.php.cn/java-article-362041.html
LinkedHashMap如何保证元素迭代的顺序

通过维护一个运行于所有条目的双向链表，LinkedHashMap保证了元素迭代的顺序。
LinkedHashMap可以认为是HashMap+LinkedList，即它既使用HashMap操作数据结构，又使用LinkedList维护插入元素的先后顺序


利用LinkedHashMap实现LRU算法缓存
LRU即Least Recently Used，最近最少使用，也就是说，当缓存满了，会优先淘汰那些最近最不常访问的数据
原理：LinkedList是有序的，每次访问一个元素（get或put），被访问的元素都被提到最后面去了




---------------------------------------------------------------------------------------------------------------------
https://blog.csdn.net/chenssy/article/details/38151189
https://blog.csdn.net/onlyoncelove/article/details/81193662


fail-fast机制/快速失败机制

迭代器的快速失败行为应该仅用于检测程序错误
编写依赖于此异常的程序的做法是错误的


“快速失败”也就是fail-fast，它是Java集合的一种错误检测机制。当多个线程对集合进行结构上的改变的操作时，有可能会产生fail-fast机制。记住是有可能，而不是一定。
例如：假设存在两个线程（线程1、线程2），线程1通过Iterator在遍历集合A中的元素，在某个时候线程2修改了集合A的结构（是结构上面的修改，而不是简单的修改集合元素的内容），那么这个时候程序就会抛出 ConcurrentModificationException 异常，从而产生fail-fast机制。


fail-fast机制，是一种错误检测机制。它只能被用来检测错误，因为JDK并不保证fail-fast机制一定会发生。若在多线程环境下使用fail-fast机制的集合，建议使用“java.util.concurrent包下的类”去取代“java.util包下的类”。

注意，迭代器的快速失败行为不能得到保证，一般来说，存在非同步的并发修改时，不可能作出任何坚决的保证。快速失败迭代器尽最大努力抛出 ConcurrentModificationException。因此，编写依赖于此异常的程序的做法是错误的，正确做法是：迭代器的快速失败行为应该仅用于检测程序错误。



原因：

ArrayList中无论add、remove、clear方法只要是涉及了改变ArrayList元素的个数的方法都会导致modCount的改变。
所以我们这里可以初步判断由于expectedModCount 得值与modCount的改变不同步，导致两者之间不等从而产生fail-fast机制。


fail-fast解决办法

方案一：在遍历过程中所有涉及到改变modCount值得地方全部加上synchronized或者直接使用Collections.synchronizedList，这样就可以解决。但是不推荐，因为增删造成的同步锁可能会阻塞遍历操作。

方案二：使用CopyOnWriteArrayList来替换ArrayList。推荐使用该方案。

CopyOnWriteArrayList在两种情况下，它非常适合使用。
1：在不能或不想进行同步遍历，但又需要从并发线程中排除冲突时。
2：当遍历操作的数量大大超过可变操作的数量时。遇到这两种情况使用CopyOnWriteArrayList来替代ArrayList再适合不过了。

CopyOnWriterArrayList根本就不会产生ConcurrentModificationException异常，也就是它使用迭代器完全不会产生fail-fast机制。
因为CopyOnWriterArrayList的方法根本就没有像ArrayList中使用checkForComodification方法来判断expectedModCount 与 modCount 是否相等。

CopyOnWriterArrayList所代表的核心概念就是：任何对array在结构上有所改变的操作（add、remove、clear等），CopyOnWriterArrayList都会copy现有的数据，再在copy的数据上修改，这样就不会影响COWIterator中的数据了，修改完成之后改变原有数据的引用即可。同时这样造成的代价就是产生大量的对象，同时数组的copy也是相当有损耗的。



---------------------------------------------------------------------------------------------------------------------


延时队列DelayQueue
  // implements Delayed ，实现getDelay方法
  // if (delay <= 0)//延迟时间到期，获取并删除头部元素。


DelayQueue 的主要成员

public class DelayQueue<E extends Delayed> extends AbstractQueue<E>
    implements BlockingQueue<E> {
    // 持有内部重入锁。
    private final transient ReentrantLock lock = new ReentrantLock();
    // 优先级队列，存放工作任务。
    private final PriorityQueue<E> q = new PriorityQueue<E>();
    private Thread leader = null;
    // 依赖于重入锁的 condition。
    private final Condition available = lock.newCondition();
}


本文我们演示了PriorityBlockingQueue 队列的两大特性：按优先顺序进行处理，对空队列情况，获取方法会阻塞线程。


一、优先级队列PriorityBlockingQueue必须是实现Comparable接口，队列通过这个接口的compare方法确定对象的priority。当前和其他对象比较，如果compare方法返回负数，那么在队列里面的优先级就比较搞

    比较规则：当前对象和其他对象做比较，当前优先级大就返回-1，优先级小就返回1

二、优先级队列是一个基于堆的无界并发安全的优先级队列。

三、优先级队列不允许null值，不允许未实现Comparable接口的对象。

四、优先级中传入的实体对象

优先级队列PriorityBlockingQueue
https://blog.csdn.net/qq_38293564/article/details/80586040
https://blog.csdn.net/neweastsun/article/details/88085955



---------------------------------------------------------------------------------------------------------------------









---------------------------------------------------------------------------------------------------------------------


