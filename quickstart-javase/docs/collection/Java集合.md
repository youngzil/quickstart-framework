- [Java集合整体和并发集合](#Java集合整体和并发集合)
- [集合类HashMap详解](#集合类HashMap详解)
- [HashMap和ConcurrentHashMap在1.7和1.8的区别](#HashMap和ConcurrentHashMap在1.7和1.8的区别)
- [HashMap和ConcurrentHashMap学习](HashMap和ConcurrentHashMap学习.md)
- [LinkedHashMap如何保证元素迭代的顺序](#LinkedHashMap如何保证元素迭代的顺序)
- [快速失败(fail-fast)和安全失败(fail-safe)](#Java集合迭代快速失败和安全失败)
    - [fail-fast机制/快速失败机制](#Java集合迭代快速失败机制)
    - [CopyOnWrite实现原理](../JavaSE/copyonwrite机制.md)
- [延时队列DelayQueue](#延时队列DelayQueue)
- [利用LinkedHashMap实现LRU算法缓存 ](#利用LinkedHashMap实现LRU算法缓存 )
- [Java中集合的有序性怎么实现的（TreeMap、PriorityQueue优先队列）](#Java中集合的有序性怎么实现的)
    - [TreeMap实现有序的原理(基于红黑树（Red-Black tree）实现)](#TreeMap实现有序的原理)
    - [PriorityQueue优先队列实现原理(最小堆的完全二叉树)](#PriorityQueue优先队列实现原理)
    - [各种树的复杂度和原理](https://github.com/youngzil/notes/blob/master/docs/base/datastructure/%E5%90%84%E7%A7%8D%E6%A0%91%E7%9A%84%E5%A4%8D%E6%9D%82%E5%BA%A6%E5%92%8C%E5%8E%9F%E7%90%86.md)



---------------------------------------------------------------------------------------------------------------------
## Java集合整体和并发集合

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

Java集合详解
https://www.cnblogs.com/cxuanBlog/p/13424318.html

Map 和 Collection 接口
Map 接口和 Collection 接口是集合框架体系的两大门派，
Collection 是存储元素本身，而 Map 是存储<key, value>键值对


Collection 集合体系详解
Collection 集合体系的顶层接口就是Collection，它规定了该集合下的一系列行为约定。

该集合下可以分为三大类集合：List，Set和Queue
1、Set接口定义了该类集合不允许存储重复的元素，且任何操作时均需要通过哈希函数映射到集合内部定位元素，集合内部的元素默认是无序的。
2、List接口定义了该类集合允许存储重复的元素，且集合内部的元素按照元素插入的顺序有序排列，可以通过索引访问元素。
3、Queue接口定义了该类集合是以队列作为存储结构，所以集合内部的元素有序排列，仅可以操作头结点元素，无法访问队列中间的元素。


Collection 接口提供了整个集合框架最通用的增删改查以及集合自身操作的抽象方法，让子类去实现
Set 接口决定了它的子类都是无序、无重复元素的集合，其主要实现有HashSet、TreeSet、LinkedHashSet。
    HashSet 底层采用 HashMap 实现，而 TreeSet 底层使用 TreeMap 实现，大部分 Set 集合的操作都会转换为 Map 的操作，TreeSet 可以将元素按照规则进行排序。
List 接口决定了它的子类都是有序、可存储重复元素的集合，常见的实现有 ArrayList，LinkedList，Vector
    ArrayList 使用数组实现，而 LinkedList 使用链表实现，所以它们两个的使用场景几乎是相反的，频繁查询的场景使用 ArrayList，而频繁插入删除的场景最好使用 LinkedList
    LinkedList 和 ArrayDeque 都可用于双端队列，而 Josh Bloch and Doug Lea 认为 ArrayDeque 具有比 LinkedList 更好的性能，ArrayDeque使用数组实现双端队列，LinkedList使用链表实现双端队列。
Queue 接口定义了队列的基本操作，子类集合都会拥有队列的特性：先进先出，主要实现有：LinkedList，ArrayDeque
    PriorityQueue 底层使用二叉堆维护的优先级队列，而二叉堆是由数组实现的，它可以按照元素的优先级进行排序，优先级越高的元素，排在队列前面，优先被弹出处理。
Map接口定义了该种集合类型是以<key,value>键值对形式保存，其主要实现有：HashMap，TreeMap，LinkedHashMap，Hashtable
    LinkedHashMap 底层多加了一条双向链表，设置accessOrder为true并重写方法则可以实现LRU缓存
    TreeMap 底层采用数组+红黑树实现，集合内的元素默认按照自然排序，也可以传入Comparator定制排序

LinkedList(双向链表)  
ArrayList（数组）  


Collection  
├List  
│├LinkedList  不安全的，可以通过List list = Collections.synchronizedList(new LinkedList(...));构建安全的  
│├ArrayList  不安全的  
│└Vector  安全的  
│　└Stack  
└Set   无序，不重复的  
└Queue  
  
Map  
├Hashtable  线程安全的  
├HashMap    线程不安全的  
└WeakHashMap  一种改进的HashMap，它对key实行“弱引用”，如果一个key不再被外部所引用，那么该key可以被GC回收。  
  
  
  
Queue方法：  
add、remove、element  抛异常  
offer、poll、peek	 返回boolean  
put、take  阻塞  
队列：FIFO，栈：FILO  

Java 数据结构之Deque(双向队列)、Queue、Stack
https://blog.csdn.net/top_code/article/details/8650729

add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常

offer       添加一个元素并返回true       如果队列已满，则返回false
poll         移除并返问队列头部的元素    如果队列为空，则返回null
peek       返回队列头部的元素             如果队列为空，则返回null

put         添加一个元素                      如果队列满，则阻塞
take        移除并返回队列头部的元素     如果队列为空，则阻塞
  
  
---------------------------------------------------------------------------------------------------------------------  
## 集合类HashMap详解

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
1、HashMap的key和value都可以是null
2、Map的key和value都不允许是基本数据类型
3、HashMap的key可以是任意对象，但如果对象的hashCode改变了，那么将找不到原来该key所对应的value



---------------------------------------------------------------------------------------------------------------------  
## HashMap和ConcurrentHashMap在1.7和1.8的区别

  
HashMap：  
JDK7中的HashMap：位桶+链表  
JDK8：位桶+链表/红黑树  


HashMap即是采用了链地址法.
JDK7 使用了数组+链表的方式
JDK8 使用了数组+链表+红黑树的方式


发生冲突关于entry节点插入链表还是链头呢？
JDK7:插入链表的头部，头插法
JDK8:插入链表的尾部，尾插法【避免死循环】（避免出现逆序且链表死循环的问题）



JDK7中HashMap采用的是位桶+链表的方式，即我们常说的散列链表的方式，  
而JDK8中采用的是位桶+链表/红黑树（有关红黑树请查看红黑树）的方式，也是非线程安全的。当某个位桶的链表的长度达到某个阀值的时候，这个链表就将转换成红黑树。  

jdk1.7版本中多线程同时对HashMap扩容时，会引起链表死循环，尽管jdk1.8修复了该问题，但是同样在jdk1.8版本中多线程操作hashMap时仍然会引起死循环，只是原因不一样。  

1.7版本中hashmap在并发执行put操作时会引起死循环，因为在put中会引起扩容操作，使链表形成环形的数据结构，     
jdk1.8版本中多线程put不会在出现死循环问题了，只有可能出现数据丢失的情况，因为1.8版本中，会将原来的链表结构保存在节点e中，然后依次遍历e,根据hash&n是否等于0,分成两条支链，保存在新数组中。    
jdk1.7版本中，扩容过程中会新数组会和原来的数组有指针引用关系，所以将引起死循环问题。   

jdk1.8 hashmap多线程put不会造成死循环
原因：声明两对指针，维护两个连链表，依次在末端添加新的元素。（在多线程操作的情况下，无非是第二个线程重复第一个线程一模一样的操作）
总结。1.8中hashmap的确不会因为多线程put导致死循环，但是依然有其他的弊端，比如数据丢失等等。因此多线程情况下还是建议使用concurrenthashmap。


  
  
ConcurrentHashMap：  
总结  
其实可以看出JDK1.8版本的ConcurrentHashMap的数据结构已经接近HashMap，相对而言，ConcurrentHashMap只是增加了同步的操作来控制并发，  
从JDK1.7版本的ReentrantLock+Segment+HashEntry，
到JDK1.8版本中CAS+同步锁、数组+链表+红黑树：synchronized+CAS+HashEntry+红黑树，CAS操作  

  
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
  
  
  
  
  
参考
https://www.jianshu.com/p/a7767e6ff2a2
https://my.oschina.net/hosee/blog/618953  
https://blog.csdn.net/qq296398300/article/details/79074239  
https://blog.csdn.net/WuJun_025/article/details/88541966  
https://blog.csdn.net/bolang789/article/details/79855053  
https://tech.meituan.com/2016/06/24/java-hashmap.html

  
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
## LinkedHashMap如何保证元素迭代的顺序
  
通过维护一个运行于所有条目的双向链表，LinkedHashMap保证了元素迭代的顺序。  
LinkedHashMap可以认为是HashMap+LinkedList，即它既使用HashMap操作数据结构，又使用LinkedList维护插入元素的先后顺序  
  
  
## 利用LinkedHashMap实现LRU算法缓存  
LRU即Least Recently Used，最近最少使用，也就是说，当缓存满了，会优先淘汰那些最近最不常访问的数据  
原理：LinkedList是有序的，每次访问一个元素（get或put），被访问的元素都被提到最后面去了  

[详解LinkedHashMap如何保证元素迭代的顺序](https://www.php.cn/java-article-362041.html)

---------------------------------------------------------------------------------------------------------------------

## Java集合迭代快速失败和安全失败

快速失败(fail-fast)和安全失败(fail-safe)

一:快速失败(fail—fast)

在用迭代器遍历一个集合对象时，如果遍历过程中对集合对象的内容进行了修改(增 加、删除、修改)，则会抛出Concurrent Modification Exception。

原理:迭代器在遍历时直接访问集合中的内容，并且在遍历过程中使用一个 modCo unt 变量。集合在被遍历期间如果内容发生变化，就会改变 modCount 的值。每当迭代器使 用 hashNext()/next()遍历下一个元素之前，都会检测 modCount 变量是否为 expectedmod Count 值，是的话就返回遍历;否则抛出异常，终止遍历。

注意:这里异常的抛出条件是检测到 modCount!=expectedmodCount 这个条件。如 果集合发生变化时修改 modCount 值刚好又设置为了 expectedmodCount 值，则异常不会 抛出。因此，不能依赖于这个异常是否抛出而进行并发操作的编程，这个异常只建议用于检 测并发修改的 bug。

场景:java.util 包下的集合类都是快速失败的，不能在多线程下发生并发修改(迭代过 程中被修改)。



二:安全失败(fail—safe)

采用安全失败机制的集合容器，在遍历时不是直接在集合内容上访问的，而是先复制原有集合内容，在拷贝的集合上进行遍历。

原理:由于迭代时是对原集合的拷贝进行遍历，所以在遍历过程中对原集合所作的修改
并不能被迭代器检测到，所以不会触发Concurrent Modification Exception。

缺点:基于拷贝内容的优点是避免了Concurrent Modification Exception，但同样地， 迭代器并不能访问到修改后的内容，即:迭代器遍历的是开始遍历那一刻拿到的集合拷贝， 在遍历期间原集合发生的修改迭代器是不知道的。

场景:java.util.concurrent 包下的容器都是安全失败，可以在多线程下并发使用并发修改。




快速失败和安全失败是对迭代器而言的。 

快速失败:当在迭代一个集合的时候，如果有另 外一个线程在修改这个集合，就会抛出 ConcurrentModification 异常，java.util 下都是快速 失败。 

安全失败:在迭代时候会在集合二层做一个拷贝，所以在修改集合上层元素不会影 响下层。在 java.util.concurrent 下都是安全失败


快速失败和安全失败是对迭代器而言的。 

快速失败：当在迭代一个集合的时候，如果有另外一个线程在修改这个集合，就会抛出ConcurrentModification异常，java.util包下都是快速失败。 比如HashMap,Vector,ArrayList,HashSet

安全失败：在迭代时候会对集合做一个拷贝，所以在修改原集合上元素不会影响拷贝的集合。在java.util.concurrent包下都是安全失败，比如ConcurrentHashMap、CopyOnWriteArrayList。



[CopyOnWrite实现原理](../JavaSE/copyonwrite机制.md)


参考  
[java中快速失败(fail-fast)和安全失败(fail-safe)的区别是什么？](https://www.cnblogs.com/williamjie/p/11158588.html)  
[快速失败(fail-fast)和安全失败(fail-safe)](https://blog.csdn.net/pange1991/article/details/84826630)  


---------------------------------------------------------------------------------------------------------------------


https://blog.csdn.net/chenssy/article/details/38151189  
https://blog.csdn.net/onlyoncelove/article/details/81193662  


## Java集合迭代快速失败机制


## 快速失败(fail-fast)和安全失败(fail-safe)

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



[CopyOnWrite实现原理](../JavaSE/copyonwrite机制.md)


---------------------------------------------------------------------------------------------------------------------  
## 延时队列DelayQueue  
  // implements Delayed ，实现getDelay方法  
  // if (delay <= 0)//延迟时间到期，获取并删除头部元素。  
  
  
  DelayQueue内部的实现使用了一个优先队列。当调用DelayQueue的offer方法时，把Delayed对象加入到优先队列q中。如下：
  DelayQueue的take方法，把优先队列q的first拿出来（peek），如果没有达到延时阀值，则进行await处理。如下：
available.awaitNanos(delay);
  
  
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



DelayQueue基本原理
首先，这种队列中只能存放实现Delayed接口的对象，而此接口有两个需要实现的方法。最重要的就是getDelay，这个方法需要返回对象过期前的时间。简单说，队列在某些方法处理前，会调用此方法来判断对象有没有超时。

其次，DelayQueue是一个BlockingQueue，其特化的参数是Delayed。（不了解BlockingQueue的同学，先去了解BlockingQueue再看本文）
Delayed扩展了Comparable接口，比较的基准为延时的时间值，Delayed接口的实现类getDelay的返回值应为固定值（final）。DelayQueue内部是使用PriorityQueue实现的。

总结，DelayQueue的关键元素BlockingQueue、PriorityQueue、Delayed。可以这么说，DelayQueue是一个使用优先队列（PriorityQueue）实现的BlockingQueue，优先队列的比较基准值是时间。本质上即：

DelayQueue = BlockingQueue +PriorityQueue + Delayed



  
优先级队列PriorityBlockingQueue  
https://blog.csdn.net/qq_38293564/article/details/80586040  
https://blog.csdn.net/neweastsun/article/details/88085955  
  
  
  
---------------------------------------------------------------------------------------------------------------------

## Java中集合的有序性怎么实现的


## TreeMap实现有序的原理

LinkedHashMap是按照插入顺序排序，而TreeMap是按照Key的自然顺序或者Comprator的顺序进行排序。

在实现原理上LinkedHashMap是双向链表，TreeMap是红黑树。TreeMap还有个好兄弟叫TreeSet，实现原理是一样的。


TreeMap是一个有序的key-value集合，是非线程安全的，基于红黑树（Red-Black tree）实现。其映射根据键的自然顺序进行排序，或者根据创建映射时提供的 Comparator 进行排序，具体取决于使用的构造方法。其基本操作 containsKey、get、put 和 remove 的时间复杂度是 log(n) 。TreeMap是非同步的。 它的iterator 方法返回的迭代器是fail-fastl的。



[TreeMap实现原理及源码分析之JDK8](https://www.cnblogs.com/nananana/p/10426377.html)  
[TreeMap实现有序的原理](https://blog.csdn.net/u010623927/article/details/87177156)  




## PriorityQueue优先队列实现原理


Java中PriorityQueue通过二叉小顶堆实现，可以用一棵完全二叉树表示（任意一个非叶子节点的权值，都不大于其左右子节点的权值），也就意味着可以通过数组来作为PriorityQueue的底层实现。

PriorityQueue是非线程安全的，所以Java提供了PriorityBlockingQueue（实现BlockingQueue接口）用于Java多线程环境。



什么是最小堆

最小堆是一个完全二叉树，所谓的完全二叉树是一种没有空节点的二叉树。

最小堆的完全二叉树有一个特性是根节点必定是最小节点，子女节点一定大于其父节点。还有一个特性是叶子节点数量=全部非叶子节点数量+1

在 PriorityQueue队列中，基于数组保存了完全二叉树。所以在已知任意一个节点在数组中的位置，就可以通过一个公式推算出其左子树和右子树的下标。已知节点下标是i，那么他的左子树是2*i+1，右子树是2*i+2。



插入原理：

新加入的元素x可能会破坏小顶堆的性质，因此需要进行调整。调整的过程为：从k指定的位置开始，将x逐层与当前点的parent进行比较并交换，直到满足x >= queue[parent]为止。注意这里的比较可以是元素的自然顺序，也可以是依靠比较器的顺序。

2、为什么要调整节点顺序？  
因为这是一个最小堆，最小堆必须要严格按照子节点大于父亲节点的顺序做数组中存放。

3、如何调整？  

siftup方法有个if-else判断，如果有比较器，则使用siftUpUsingComparator(k, x);如果没有则调用siftUpComparable(k, x);这个方法会默认给一个比较器。

比较什么呢？？我们说最小堆实现了这个队列，队列一定有入队操作，入队是元素首先进入队列尾，然后和自己的父节点比较，像冒泡一样将该节点冒到合适的位置，即比自己的父亲节点大，比自己的儿子节点小。




应用场景：PriorityQueue队列不适合进场出队入队的频繁操作，但是他的优先级特性非常适合一些对顺序有要求的数据处理场合。


[PriorityQueue的用法和底层实现原理](https://blog.csdn.net/u010623927/article/details/87179364)  
[PriorityQueue优先队列实现原理](https://blog.csdn.net/lisuyibmd/article/details/53205403)  
[优先队列原理与实现](https://www.cnblogs.com/luoxn28/p/5616101.html)  


[各种树的复杂度和原理](https://github.com/youngzil/notes/blob/master/docs/base/datastructure/%E5%90%84%E7%A7%8D%E6%A0%91%E7%9A%84%E5%A4%8D%E6%9D%82%E5%BA%A6%E5%92%8C%E5%8E%9F%E7%90%86.md)  


---------------------------------------------------------------------------------------------------------------------  




