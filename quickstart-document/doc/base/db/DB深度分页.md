https://blog.csdn.net/li772030428/article/details/52839987
https://blog.csdn.net/bestcleaner/article/details/52993468
https://blog.csdn.net/yongshuai185/article/details/55506210



https://blog.csdn.net/ldTrueLove/article/details/52921961
大数据分页优化：
limit 及翻页优化
limit offset,N,  当offset非常大时, 效率极低,
原因是mysql并不是跳过offset行,然后单取N行,
而是取offset+N行,返回放弃前offset行,返回N行.
效率较低,当offset越大时,效率越低


1、利用表的覆盖索引来加速分页查询，查询的都是索引中的字段，直接从索引中返回
2、非要物理删除,还要用offset精确查询,还不限制用户分页,怎么办?先使用主键索引查出记录，再用in或者JOIN去查询具体的数据
	分析: 优化思路是 不查,少查,查索引,少取.
	我们现在必须要查,则只查索引,不查数据,得到id.
	再用id去查具体条目.  这种技巧就是延迟索引.
3、倒排序从后面取出对应的记录
4、多缓存几页，每次查询先返回缓存，再查询后面未用到的放进缓存
5、不允许分页太多，比如百度最多70多页
