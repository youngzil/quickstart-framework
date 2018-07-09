https://github.com/ben-manes/concurrentlinkedhashmap


ConcurrentLinkedHashMap是java.util.LinkedHashMap的一个高性能实现。主要用于软件缓存。

ConcurrentLinkedHashMap 是google团队提供的一个容器。它有什么用呢？其实它本身是对
ConcurrentHashMap的封装，可以用来实现一个基于LRU策略的缓存。
详细介绍可以参见  
http://code.google.com/p/concurrentlinkedhashmap



这个结构必须满足访问修改快速，并发性好，占用内存空间少，存储的个数大小可控制，并且可以驱逐访问量少的entry，从而可以达到实时统计热点的查询数字。
2.1.a 支持并发。它是实现了ConcurrentMap接口的。基本上它的实现是遵循concurrentMap的思想的。
2.1.b 它把我们普通concurrenthashmap的segments用一个额外的双链表维护起来，通过操作这个链表，里面的entry可以在O(1)的时间之类删除或者重新排序。当一个entry被访问，则被移动到表头；当这个map的容量超过预设，则移除位于链表尾的entry。这样就可以保证表头是最新被使用的entry，表尾是最近最少被使用的entry
2.1.c 从另外一个角度来说，可以把ConcurrentLinkedHashMap 分成两个view. 一个是同步的，一个是异步的。Hashtable对于调用方来说是同步的，链表对于调用方式不透明的，异步的。
2.1.d 性能和concurrenthashmap的比较, 肯定比concurrenthashmap差，但是属于可以忍受的范围。

综上所述，我们可以利用这个LRU concurrent map link,保证我们在单位时间内，仅保存有限数量访问量较高的key, 最近比较少访问的key,我们则可以认为它不是热点，当map的Size达到上限之后，清除这个key


LRU（Least recently used，最近最少使用）算法根据数据的历史访问记录来进行淘汰数据，其核心思想是“如果数据最近被访问过，那么将来被访问的几率也更高”。




参考
http://jm.taobao.org/2016/02/01/3744/
