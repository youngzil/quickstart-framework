怎么设计hashcode更均衡.md


分布均匀的hash函数：
1、jump consistent hash
2、一致性哈希算法（consistent hashing）







jump consistent hash是一种一致性哈希算法, 此算法零内存消耗，均匀分配，快速，并且只有5行代码。
此算法适合使用在分shard的分布式存储系统中 。
此算法的作者是 Google 的 John Lamping 和 Eric Veach，论文原文在 http://arxiv.org/ftp/arxiv/papers/1406/1406.2294.pdf

输入是一个64位的key，和桶的数量（一般对应服务器的数量），输出是一个桶的编号。



原理解释：

jump consistent hash的设计目标是：
1、平衡性，把对象均匀地分布在所有桶中。
2、单调性，当桶的数量变化时，只需要把一些对象从旧桶移动到新桶，不需要做其它移动。

jump consistent hash的设计思路是：计算当bucket数量变化时，有哪些输出需要变化。




参考
https://blog.csdn.net/innobase/article/details/51645438
https://blog.csdn.net/lmjy102/article/details/71726017
https://tech.meituan.com/2016/06/24/java-hashmap.html





一致性哈希算法（consistent hashing）



参考
https://www.cnblogs.com/myseries/p/10959050.html
https://lihuimintu.github.io/2019/05/24/Consistent-Hashing/
https://zhuanlan.zhihu.com/p/92630432
https://zhuanlan.zhihu.com/p/129049724
https://www.hellojava.com/a/86523.html
https://segmentfault.com/a/1190000020182775




