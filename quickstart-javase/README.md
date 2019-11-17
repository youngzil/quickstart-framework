https://github.com/eclipse-ee4j/concurrency-api
https://github.com/eclipse-ee4j/common-annotations-api
https://github.com/eclipse-ee4j/jta-api
https://github.com/eclipse-ee4j/security-api



Java并发思考-导读&总结篇：http://www.jianshu.com/p/9ed8e51c3ed1?hmsr=toutiao.io&utm_medium=toutiao.io&utm_source=toutiao.io



参考代码

强软弱虚引用
https://www.baeldung.com/java-weakhashmap

https://travis-ci.org/javaee-samples
https://github.com/javaee-samples
https://github.com/javaee-samples/javaee7-samples
https://github.com/javaee-samples/javaee8-samples

【JavaSE】Java 知识汇总(资源,工具,笔记,源码,文章,文档分类整理)；项目由Gradle版本工具构建；
https://github.com/Arisono/Gradle-demo


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



JVM的GC引起的STW：
HotSpot VM的stop-the-world（STW）只有一种模式，那就是把所有Java线程都暂停下来。
一个new动作必须要在一个Java线程能运行的时候才可以执行得了。如果VM当前正在STW，正在或者即将尝试执行new动作的Java线程也必然跟其它Java线程一样被暂停了，要等触发STW的动作（例如GC）结束后才可以继续执行。
在STW结束后，Java线程恢复运行时，就像是做了场梦然后醒过来一样，先前该做啥就继续做啥，并不会感知STW过程中发生了什么事。假如一个Java线程在STW前正在执行new（它很可能就是这次STW的触发者了…），那么GC STW后它继续执行，还是照旧去看看当前GC堆里还有没有足够空间可满足这次分配请求，有的话就分配并返回，没有的话就要么触发GC要么抛OOME，一切照常。


数据传输，在操作系统层面，用户线程和核心线程之间会有数据copy，用户线程发送到socket，再刷到网卡上，发送出去



随着JDK 9的发布，Garbage-First(G1)GC取代了Parallel Collector作为默认GC。

https://github.com/rockyzhengwu/FoolNLTK
https://www.oschina.net/p/foolnltk
 FoolNLTK 是一个中文处理工具包。它可能不是最快的开源中文分词，但很可能是最准的开源中文分词。它是基于 BiLSTM 模型训练而成，包含分词，词性标注，实体识别,　都有比较高的准确率。用户还可以自定义词典。


Java字节码注入工具 Byteman
http://byteman.jboss.org/
https://www.oschina.net/p/byteman


java的深拷贝与浅拷贝http://blog.csdn.net/lcg910978041/article/details/51992614



使用jar命令替换jar中的一个或多个文件
jar -uvf rpds.jar BOOT-INF/classes/db.properties  
PS：BOOT/classes是jar包内的目录，如果不加此目录，会把文件放到jar包的根目录，另外在jar包的同级目录也要有BOOT/classes这个目录
替换多个文件的时候用空格分割



web.xml文件详解
https://segmentfault.com/a/1190000011404088





