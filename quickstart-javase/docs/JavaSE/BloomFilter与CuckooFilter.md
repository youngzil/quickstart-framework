- [BloomFilter布隆过滤器 和 布谷鸟哈希CuckooFilter](#BloomFilter布隆过滤器-和-布谷鸟哈希CuckooFilter)


-------------------------------------------------------------------------
## BloomFilter布隆过滤器 和 布谷鸟哈希CuckooFilter


使用Bit-map位图的思想，我们可以将存储空间进行压缩，而且可以对数字进行快速排序、去重和查询的操作。Bloom Fliter是Bit-map思想的一种扩展，它可以在允许低错误率的场景下，大大地进行空间压缩，是一种拿错误率换取空间的数据结构。




BloomFilter布隆过滤器：只能插入和查询，可能存在误判存在，并且无法删除元素
Cuckoo 布谷鸟哈希：多个hash函数和多个哈希表，随机存入一个位子，全部冲突就随机选择一个存入，把提出的再重新找位置

Counting Bloom Filter ：支持删除，插入时候+1，删除时候-1


https://www.cnblogs.com/chenny7/p/4074250.html
https://blog.csdn.net/qq_18495465/article/details/78500472
https://blog.csdn.net/load2006/article/details/52804032
https://blog.csdn.net/chdhust/article/details/48576961
https://blog.csdn.net/u014653197/article/details/76397037


CuckooFilter： 布谷鸟哈希
https://blog.csdn.net/BigFatSheep/article/details/80045371
https://github.com/MGunlogson/CuckooFilter4J


CountingBloomFilter：CBF
https://www.cnblogs.com/chenying99/p/4375174.html
https://blog.csdn.net/zhaodedong/article/details/78445931


BitMap位图
https://blog.csdn.net/u013063153/article/details/70800381
https://blog.csdn.net/kl1106/article/details/79478787




