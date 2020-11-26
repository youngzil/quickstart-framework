- [分布均匀的hash函数](#分布均匀的hash函数)
    - [原理解释](#原理解释)
- [一致性哈希算法（consistent hashing）](#一致性哈希算法consistent-hashing)
- [看下JDK8中的hash算法](#看下JDK8中的hash算法)

## 分布均匀的hash函数
1、jump consistent hash  
2、一致性哈希算法（consistent hashing）

jump consistent hash是一种一致性哈希算法, 此算法零内存消耗，均匀分配，快速，并且只有5行代码。
此算法适合使用在分shard的分布式存储系统中 。
此算法的作者是 Google 的 John Lamping 和 Eric Veach，论文原文在 http://arxiv.org/ftp/arxiv/papers/1406/1406.2294.pdf

输入是一个64位的key，和桶的数量（一般对应服务器的数量），输出是一个桶的编号。



## 原理解释

jump consistent hash的设计目标是：
1、平衡性，把对象均匀地分布在所有桶中。
2、单调性，当桶的数量变化时，只需要把一些对象从旧桶移动到新桶，不需要做其它移动。

jump consistent hash的设计思路是：计算当bucket数量变化时，有哪些输出需要变化。




参考
https://blog.csdn.net/innobase/article/details/51645438
https://blog.csdn.net/lmjy102/article/details/71726017
https://tech.meituan.com/2016/06/24/java-hashmap.html




## 一致性哈希算法consistent hashing
一致性哈希算法（consistent hashing）



参考
https://www.cnblogs.com/myseries/p/10959050.html
https://lihuimintu.github.io/2019/05/24/Consistent-Hashing/
https://zhuanlan.zhihu.com/p/92630432
https://zhuanlan.zhihu.com/p/129049724
https://www.hellojava.com/a/86523.html
https://segmentfault.com/a/1190000020182775






## 看下JDK8中的hash算法

就是把高16位不变，低16位和高16位进行异或运算

//重新计算哈希值
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);//key如果是null 新hashcode是0 否则 计算新的hashcode
}


首先是取key的hashCode算法，然后对16进行异或运算和右移运算。

将h无符号右移16为相当于将高区16位移动到了低区的16位，再与原hashcode做异或运算，可以将高低位二进制特征混合起来

右位移16位，正好是32bit的一半，自己的高半区和低半区做异或，就是为了混合原始哈希码的高位和低位，以此来加大低位的随机性。而且混合后的低位掺杂了高位的部分特征，这样高位的信息也被变相保留下来。


[HashMap中的hash算法总结](https://www.cnblogs.com/wang-meng/p/9b6c35c4b2ef7e5b398db9211733292d.html)  
[JDK 源码中 HashMap 的 hash 方法原理是什么？](https://www.zhihu.com/question/20733617)  
[HashMap中的hash算法总结](https://blog.csdn.net/a314774167/article/details/100110216)  
[HashMap中的hash算法中的几个疑问](https://www.cnblogs.com/zxporz/p/11204233.html)  


