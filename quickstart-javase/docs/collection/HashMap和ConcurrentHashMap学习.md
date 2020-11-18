- [HashMap实际使用技巧](#HashMap实际使用技巧)
- [默认大小、负载因子以及扩容倍数](#默认大小、负载因子以及扩容倍数)
- [为什么设置的负载因子是0.75](#为什么设置的负载因子是0.75)
- [数组长度为什么是2的幂次方](#数组长度为什么是2的幂次方)
    - [HashMap的扩容机制为什么是2幂次方](#HashMap的扩容机制为什么是2幂次方)
- [查找、插入、扩容过程](#查找、插入、扩容过程)
- [HashMap为什么多线程下会不安全](#HashMap为什么多线程下会不安全)
- [什么是CAS算法](#什么是CAS算法)
- [ConcurrentHashMap是如何解决线程安全问题的](#ConcurrentHashMap是如何解决线程安全问题的)
- [ConcurrentHashMap查找以及插入过程](#ConcurrentHashMap查找以及插入过程)
- [怎么设计hashcode更均衡](怎么设计hashcode更均衡.md)
    - [分布均匀的hash函数](怎么设计hashcode更均衡.md#分布均匀的hash函数)
        - [原理解释](怎么设计hashcode更均衡.md#原理解释)
    - [一致性哈希算法（consistent hashing）](怎么设计hashcode更均衡.md#一致性哈希算法consistent-hashing)
- [底层数据结构](#底层数据结构)
-[如何计算key的hash值](#如何计算key的hash值)
-[如何处理hash冲突](#如何处理hash冲突)
- [在HashMap中使用可变对象作为Key带来的问题](#在HashMap中使用可变对象作为Key带来的问题)
- [HashMap在jdk1.8也会出现死循环的问题](#HashMap在jdk1.8也会出现死循环的问题)



数组+链表=哈希表，具体原理是？


ConcurrentHashMap和HashMap

如何设计一个HashMap：分布均衡，hash冲突解决方式
HashMap的原理源码

HashMap是面试中经常问到的一个知识点，也是判断一个候选人基础是否扎实的标准之一，因为通过HashMap可以引出很多知识点，比如数据结构(数组、链表、红黑树)、equals和hashcode方法，除此之外还可以引出线程安全的问题






---------------------------------------------------------------------------------------------------------------------  
## HashMap实际使用技巧

HashMap实际使用技巧：
1、要设置HashMap的初始化容量
2、HashMap初始化容量设置多少合适：return (int) ((float) expectedSize / 0.75F + 1.0F);


当我们使用HashMap(int initialCapacity)来初始化容量的时候，HashMap并不会使用我们传进来的initialCapacity直接作为初识容量。

JDK会默认帮我们计算一个相对合理的值当做初始容量。所谓合理值，其实是找到第一个比用户传入的值大的2的幂。

也就是说，当我们new HashMap(7)创建HashMap的时候，JDK会通过计算，帮我们创建一个容量为8的Map；当我们new HashMap(9)创建HashMap的时候，JDK会通过计算，帮我们创建一个容量为16的Map。

但是，这个值看似合理，实际上并不尽然。因为HashMap在根据用户传入的capacity计算得到的默认容量，并没有考虑到loadFactor这个因素，只是简单机械的计算出第一个大约这个数字的2的幂。

loadFactor是负载因子，当HashMap中的元素个数（size）超过 threshold = loadFactor * capacity时，就会进行扩容。

也就是说，如果我们设置的默认值是7，经过JDK处理之后，HashMap的容量会被设置成8，但是，这个HashMap在元素个数达到 8*0.75 = 6的时候就会进行一次扩容，这明显是我们不希望见到的。

这里我们可以参考JDK8中putAll方法中的实现的，这个实现在guava（21.0版本）也被采用。

这个值的计算方法就是：
return (int) ((float) expectedSize / 0.75F + 1.0F);

比如我们计划向HashMap中放入7个元素的时候，我们通过expectedSize / 0.75F + 1.0F计算，7/0.75 + 1 = 10 ,10经过JDK处理之后，会被设置成16，这就大大的减少了扩容的几率。

当HashMap内部维护的哈希表的容量达到75%时（默认情况下），会触发rehash，而rehash的过程是比较耗费时间的。所以初始化容量要设置成expectedSize/0.75 + 1的话，可以有效的减少冲突也可以减小误差。（大家结合这个公式，好好理解下这句话）

所以，我们可以认为，当我们明确知道HashMap中元素的个数的时候，把默认容量设置成expectedSize / 0.75F + 1.0F 是一个在性能上相对好的选择，但是，同时也会牺牲些内存。

这个算法在guava中有实现，开发的时候，可以直接通过Maps类创建一个HashMap：
Map<String, String> map = Maps.newHashMapWithExpectedSize(7);

以上的操作是一种用内存换性能的做法，真正使用的时候，要考虑到内存的影响。但是，大多数情况下，我们还是认为内存是一种比较富裕的资源。


参考  
[Java开发手册建议创建HashMap时设置初始化容量，但是多少合适呢？](https://developer.aliyun.com/article/756448)  





---------------------------------------------------------------------------------------------------------------------  

## 默认大小、负载因子以及扩容倍数

默认初始容量为16，默认负载因子为0.75
threshold = 数组长度 * loadFactor，当元素个数超过threshold(容量阈值)时，HashMap会进行扩容操作
table数组中存放指向链表的引用


Map是一个双列集合  
HashMap：默认初始容量为16  
    为何是16：16是2^4，可以提高查询效率，另外，32=16<<1       -->至于详细的原因可另行分析，或分析源代码）  
    加载因子为0.75：即当 元素个数 超过 容量长度的0.75倍 时，进行扩容  
    扩容增量：原容量的 1 倍  
    如 HashSet的容量为16，一次扩容后是容量为32  

[ArrayList 和 HashMap 的默认大小是多数？](https://blog.csdn.net/qq_32575047/article/details/78942965)




### 为什么设置的负载因子是0.75
为什么是0.75 ， 不是0.5或者1？

1、当负载因子是1.0的时候，负载因子过大，虽然空间利用率上去了，但是时间效率降低了。

当负载因子是1.0的时候，也就意味着，只有当数组的8个值（这个图表示了8个）全部填充了，才会发生扩容。这就带来了很大的问题，因为Hash冲突时避免不了的。当负载因子是1.0的时候，意味着会出现大量的Hash的冲突，底层的红黑树变得异常复杂。对于查询效率极其不利。这种情况就是牺牲了时间来保证空间的利用率。
因此一句话总结就是负载因子过大，虽然空间利用率上去了，但是时间效率降低了。

2、负载因子是0.5，负载因子太小，虽然时间效率提升了，但是空间利用率降低了。

负载因子是0.5的时候，这也就意味着，当数组中的元素达到了一半就开始扩容，既然填充的元素少了，Hash冲突也会减少，那么底层的链表长度或者是红黑树的高度就会降低。查询效率就会增加。
但是，兄弟们，这时候空间利用率就会大大的降低，原本存储1M的数据，现在就意味着需要2M的空间。
一句话总结就是负载因子太小，虽然时间效率提升了，但是空间利用率降低了。


3、负载因子0.75
大致意思就是说负载因子是0.75的时候，空间利用率比较高，而且避免了相当多的Hash冲突，使得底层的链表或者是红黑树的高度比较低，提升了空间效率。



如果是0.5 ， 那么每次达到容量的一半就进行扩容，默认容量是16， 达到8就扩容成32，达到16就扩容成64， 最终使用空间和未使用空间的差值会逐渐增加，空间利用率低下。  如果是1，那意味着每次空间使用完毕才扩容，在一定程度上会增加put时候的时间。

JDK 1.7中API这样解释
作为一般规则，默认负载因子（0.75）在时间和空间成本上提供了很好的折衷。较高的值会降低空间开销，但提高查找成本（体现在大多数的HashMap类的操作，包括get和put）。设置初始大小时，应该考虑预计的entry数在map及其负载系数，并且尽量减少rehash操作的次数。如果初始容量大于最大条目数除以负载因子，rehash操作将不会发生。


参考
https://www.jianshu.com/p/64f6de3ffcc1
https://blog.csdn.net/SDDDLLL/article/details/104040352




## 底层数据结构
在 JDK1.8 中，HashMap 是由 数组+链表+红黑树构成(1.7版本是数组+链表)



## 如何计算key的hash值
Java中HashMap的hash方法是：Objects.hashCode(key) ^ Objects.hashCode(value)
使用key的hashCode和value的hashCode进行^（异或运算符）



## 如何处理hash冲突
链地址法解决
当一个值中要存储到HashMap中的时候会根据Key的值来计算出他的hash，通过hash值来确认存放到数组中的位置，如果发生hash冲突就以链表的形式存储，当链表过长的话，HashMap会把这个链表转换成红黑树来存储


### 数组长度为什么是2的幂次方
（主数组的长度为何必须是2的整数倍）  
1、当数组长度为2的幂次方时，可以使用位运算来计算元素在数组中的下标，减少resize扩容过程中元素移动的数量  
2、 增加hash值的随机性，减少hash冲突


### HashMap的扩容机制为什么是2幂次方

在旧数组中同一条Entry链上的元素，在resize过程中，通过重新计算索引位置后，有可能被放到了新数组的不同位置上。JDK8做了一些优化，resize过程中对Hash表数组大小的修改使用的是2次幂的扩展（指长度扩为原来2倍），这样有2个好处。  
- 1、分布更加均衡，不然按照现有的定位函数，会有数组位置永远不可能被entry占用，造成浪费
put方法会调用indexFor(int h, int length)方法，这个方法主要是根据key的hash值找到这个entry在Hash表数组中的位置  
注意最后return的是h&(length-1)。如果length不为2的幂，比如15。那么length-1的2进制就会变成1110。在h为随机数的情况下，和1110做&操作。尾数永远为0。那么0001、1001、1101等尾数为1的位置就永远不可能被entry占用。这样会造成浪费，不随机等问题。 length-1 二进制中为1的位数越多，那么分布就平均。  
- 2、resize过程中，只需要看看原来的hash值新增的那个bit是1还是0就好了，是0的话索引没变，是1的话索引变成“原索引+oldCap”  
resize过程中不需要像JDK1.7的实现那样重新计算hash，只需要看看原来的hash值新增的那个bit是1还是0就好了，是0的话索引没变，是1的话索引变成“原索引+oldCap”，可以看看下图为16扩充为32的resize示意图（一方面位运算更快，另一方面抗碰撞的Hash函数其实挺耗时的）


参考
https://blog.csdn.net/dalong3976/article/details/83934609




## 查找、插入、扩容过程
扩容
HashMap每次扩容都是建立一个新的table数组，长度和容量阈值都变为原来的两倍，然后把原数组元素重新映射到新数组上，具体步骤如下：
- 1、首先会判断table数组长度，如果大于0说明已被初始化过，那么按当前table数组长度的2倍进行扩容，阈值也变为原来的2倍
- 2、若table数组未被初始化过，且threshold(阈值)大于0说明调用了HashMap(initialCapacity, loadFactor)构造方法，那么就把数组大小设为threshold
- 3、若table数组未被初始化，且threshold为0说明调用HashMap()构造方法，那么就把数组大小设为16，threshold设为16*0.75
- 4、接着需要判断如果不是第一次初始化，那么扩容之后，要重新计算键值对的位置，并把它们移动到合适的位置上去，如果节点是红黑树类型的话则需要进行红黑树的拆分。

这里有一个需要注意的点就是在JDK1.8 HashMap扩容阶段重新映射元素时不需要像1.7版本那样重新去一个个计算元素的hash值，而是通过hash & oldCap的值来判断，若为0则索引位置不变，不为0则新索引=原索引+旧数组长度，为什么呢？具体原因如下：

因为我们使用的是2次幂的扩展(指长度扩为原来2倍)，所以，元素的位置要么是在原位置，要么是在原位置再移动2次幂的位置。
因此，我们在扩充HashMap的时候，不需要像JDK1.7的实现那样重新计算hash，只需要看看原来的hash值新增的那个bit是1还是0就好了，是0的话索引没变，是1的话索引变成“原索引+oldCap


链表树化
指的就是把链表转换成红黑树，树化需要满足以下两个条件：
- 1、链表长度大于等于8
- 2、table数组长度大于等于64

为什么table数组容量大于等于64才树化？
因为当table数组容量比较小时，键值对节点 hash 的碰撞率可能会比较高，进而导致链表长度较长。这个时候应该优先扩容，而不是立马树化。



查找
在看源码之前先来简单梳理一下查找流程：
1、首先通过自定义的hash方法计算出key的hash值，求出在数组中的位置
2、判断该位置上是否有节点，若没有则返回null，代表查询不到指定的元素
3、若有则判断该节点是不是要查找的元素，若是则返回该节点
4、若不是则判断节点的类型，如果是红黑树的话，则调用红黑树的方法去查找元素
5、如果是链表类型，则遍历链表调用equals方法去查找元素


插入
我们先来看看插入元素的步骤：
1、当table数组为空时，通过扩容的方式初始化table
2、通过计算键的hash值求出下标后，若该位置上没有元素(没有发生hash冲突)，则新建Node节点插入
3、若发生了hash冲突，遍历链表查找要插入的key是否已经存在，存在的话根据条件判断是否用新值替换旧值
4、如果不存在，则将元素插入链表尾部，并根据链表长度决定是否将链表转为红黑树
5、判断键值对数量是否大于阈值，大于的话则进行扩容操作

从源码也可以看出table数组是在第一次调用put方法后才进行初始化的。
这里还有一个知识点就是在JDK1.8版本HashMap是在链表尾部插入元素的，【避免扩容时候链表产生环，导致死循环】
而在1.7版本里是插入链表头部的，1.7版本这么设计的原因可能是作者认为新插入的元素使用到的频率会比较高，插入头部的话可以减少遍历次数。


删除
HashMap的删除操作并不复杂，仅需三个步骤即可完成。
1、定位桶位置
2、遍历链表找到相等的节点
3、第三步删除节点


遍历
当我们在遍历HashMap的时候，若使用remove方法删除元素时会抛出ConcurrentModificationException异常
这就是常说的fail-fast(快速失败)机制
在HashMap中有一个名为modCount的变量，它用来表示集合被修改的次数，修改指的是插入元素或删除元素，可以回去看看上面插入删除的源码，在最后都会对modCount进行自增。
当我们在遍历HashMap时，每次遍历下一个元素前都会对modCount进行判断，若和原来的不一致说明集合结果被修改过了，然后就会抛出异常，这是Java集合的一个特性


ConcurrentHashMap和HashMap在很多地方是类似的，比如底层都是数组+链表+红黑树、数组大小都是2的幂次方等.


## HashMap为什么多线程下会不安全

HashMap在并发环境下主要有以下几个问题：
1、死循环(JDK1.7)：在1.7版本，当扩容后生成新数组，在转移元素的过程中，使用的是头插法，也就是链表的顺序会翻转，当多个线程执行插入操作时可能会发生死循环。在1.8版本时将头插法改成了尾插法，解决了死循环的问题。
2、数据丢失：当两个线程同时插入元素时可能会发生数据被覆盖的情况

那么有哪些解决方法呢？
1、Hashtable：给所有方法加synchronized锁，非常低效，现在已经淘汰。
2、Synchronized Map：Collections包提供的一个方法，会同步整个对象，也不推荐使用
3、ConcurrentHashMap：尽管没有同步整个Map，但是它仍然是线程安全的，读操作非常快，而写操作则是通过加锁完成的，推荐使用



## 什么是CAS算法
CAS可以看做是乐观锁的一种实现方式，Java原子类中的递增操作就通过CAS自旋实现的。
CAS全称 Compare And Swap（比较与交换），是一种无锁算法。在不使用锁（没有线程被阻塞）的情况下实现多线程之间的变量同步。

cas的ABA问题就是 假设初始值为A，线程3和线程1都获取到了初始值A，然后线程1将A改为了B，线程2将B又改回了A，这时候线程3做修改时，是感知不到这个值从A改为了B又改回了A的过程：

AtomicStampedReference 本质是有一个int 值作为版本号，每次更改前先取到这个int值的版本号，等到修改的时候，比较当前版本号与当前线程持有的版本号是否一致，如果一致，则进行修改，并将版本号+1（当然加多少或减多少都是可以自己定义的），在zookeeper中保持数据的一致性也是用的这种方式；

AtomicMarkableReference则是将一个boolean值作是否有更改的标记，本质就是它的版本号只有两个，true和false，修改的时候在这两个版本号之间来回切换，这样做并不能解决ABA的问题，只是会降低ABA问题发生的几率而已；



## ConcurrentHashMap是如何解决线程安全问题的
ConcurrentHashMap支持并发的读写。跟1.7版本相比，JDK1.8的实现已经摒弃了Segment的概念，而是直接用Node数组+链表+红黑树的数据结构来实现，并发控制使用Synchronized和CAS来操作，虽然源码里面还保留了，也只是为了兼容性的考虑，因此本文主要讲解的是JDK1.8版本的ConcurrentHashMap。

在JDK1.7的实现上，ConrruentHashMap由一个个Segment组成，简单来说，ConcurrentHashMap是一个Segment数组，它通过继承ReentrantLock来进行加锁，通过每次锁住一个segment来保证每个segment内的操作的线程安全性从而实现全局线程安全。


数据结构
ConcurrentHashMap和HashMap都是由数组+链表+红黑树构成，不过有一个不同的是ConcurrentHashMap的数组中放入的不是TreeNode结点，而是将TreeNode包装起来的TreeBin对象



## ConcurrentHashMap查找以及插入过程

查询
在介绍get方法之前先来看看ConurrentHashMap如何计算key的hash值，ConcurrentHashMap用了spread函数来求hash值，它与HashMap的hash函数有略微不同，代码如下：
static final int spread(int h) { 
     return (h ^ (h >>> 16)) & HASH_BITS; 
}
除了高16位和低16位或操作之外，最后还和HASH_BITS相与，其值为0x7fffffff。它的作用主要是使hash值为正数。在ConcurrentHashMap中，Hash值为负数有特别的意义，如-1表示ForwardingNode结点，-2表示TreeBin结点。


hash值都是正数
hash值-1表示ForwardingNode结点，-2表示TreeBin结点
ForwardingNode节点是Node节点的子类，hash值固定为-1，只在扩容 transfer的时候出现，当旧数组中全部的节点都迁移到新数组中时，旧数组就在数组中放置一个ForwardingNode。
读操作或者迭代读时碰到ForwardingNode时，将操作转发到扩容后的新的table数组上去执行，写操作碰见它时，则尝试帮助扩容。

至于TreeBin节点也是继承自Node，hash值固定为-2，是红黑树的包装结点。


查询步骤
1、首先计算出key的hash值
2、判断table是否已经初始化以及数组下标位置上是否有元素(和HashMap一样使用(n-1)&hash计算下标)
3、判断第一个节点是否就是要查找的节点
4、若 hash = -1则调用ForwardingNode的find函数转发到nextTable上查找；若 hash = -2 则调用TreeBin的find函数查找元素
5、否则遍历链表查询元素


插入
还是一样先来看看插入过程：
1、若key或value为null则抛出NullPointerException异常，也就是说不允许key或value为null
2、判断table是否需要初始化，根据key的hash值计算出在数组中的下标，用tabAt方法读取节点，若没有发生hash冲突则用CAS插入节点
3、若发生了hash冲突，则判断是否为ForwardingNode节点，说明在扩容，调用hlepTransfer帮助扩容
4、若不是ForwardingNode节点，则使用synchronized对节点加锁，之后遍历链表，若元素已存在则更新旧值，否则在尾部插入节点
5、如果是TreeBin节点则调用putTreeVal方法插入
6、最后判断链表是否需要转换成红黑树以及调用addCount方法对节点数量+1，在该方法里面也会判断是否需要扩容

为什么ConcurrentHashMap以及Hashtable这样的同步容器不允许键值对为null呢？
因为concurrenthashmap以及hashtable是用于多线程的，如果map.get(key)得到了null，不能判断到底是映射的value是null,还是因为 没有找到对应的key而为空，而用于单线程状态的hashmap却可以用containKey（key） 去判断到底是否包含了这个null。

ConcurrentHashMap为什么就不能containKey(key)？
因为一个线程先get(key)再containKey(key)，这两个方法的中间时刻，其他线程怎么操作这个key都会可能发生，例如删掉这个key。



扩容





参考
https://segmentfault.com/a/1190000022184751
https://segmentfault.com/a/1190000022279729
https://www.jianshu.com/p/2c802af89cbc



红黑树详细分析
http://www.tianxiaobo.com/2018/01/11/%E7%BA%A2%E9%BB%91%E6%A0%91%E8%AF%A6%E7%BB%86%E5%88%86%E6%9E%90/


一文彻底搞懂CAS
https://segmentfault.com/a/1190000022342403
https://blog.csdn.net/jingzi123456789/article/details/78004074



---------------------------------------------------------------------------------------------------------------------  

## 在HashMap中使用可变对象作为Key带来的问题
其中一种问法是这样的：一个普通的对象，能够作为HashMap的key么？  
答案显然是可以的，但需要注意重写hashCode和equals方法。如果忘记重写的话，大概率会造成内存泄漏。  
对象作为HashMap结构的key，则一定要注意重写equals和hashCode两个方法。  


HashMap的key可以是任意对象，但如果对象的hashCode改变了，那么将找不到原来该key所对应的value


[在HashMap中将可变对象用作Key，需要注意什么？](https://my.oschina.net/dabird/blog/821230)  
[对象作为HashMap的key](https://blog.csdn.net/qq_22076345/article/details/98253557)  




---------------------------------------------------------------------------------------------------------------------  

## HashMap在jdk1.8也会出现死循环的问题


HashMap线程不安全，数组+链表+红黑树
Hashtable线程安全，锁住整个对象，数组+链表
ConccurentHashMap 线程安全，CAS+同步锁、数组+链表+红黑树
HashMap的key，value均可为null，其他两个不行。



[JDK 1.8 的 HashMap 详解： 为什么并发会出问题？甚至出现死循环导致系统不可用？](https://www.jianshu.com/p/ad33236610ec)  
[HashMap 1.8 还有死循环](https://www.ershicimi.com/p/ffa73925cd9834837d290b2375105333)  
[HashMap在jdk1.8也会出现死循环的问题（实测）](https://blog.csdn.net/Liu_JM/article/details/105582711)  
[HashMap 多线程下死循环分析及JDK8修复](https://cloud.tencent.com/developer/article/1120823)  
[]()  
[]()  

---------------------------------------------------------------------------------------------------------------------  




