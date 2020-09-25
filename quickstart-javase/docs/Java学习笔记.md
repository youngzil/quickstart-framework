- [Java基础](#Java基础)
    - [JavaOOM类型](#JavaOOM类型)
    - [Java泛型的类型擦除和Java语法糖](#Java泛型的类型擦除和Java语法糖)
    - [sleep和wait方法](#sleep和wait方法)
    - [jar包和替换jar包类](#jar包和替换jar包类)
    - [java中的数字魔法](#java中的数字魔法)
    - [Java到底是值传递还是引用传递](#Java到底是值传递还是引用传递)
    - [java8lambda表达式的优缺点总结](#java8lambda表达式的优缺点总结)
    - [java在读多写少、写多读少分别使用什么类](#java在读多写少、写多读少分别使用什么类)
- [Java集合](#Java集合)
- [Java并发](#Java并发)
- [JVM](#JVM)
- [I/O](#IO)
- [JDK8](#JDK8)
- [JDK9](#JDK9)



学习网站
https://www.toutiao.com/c/user/93762594808/#mid=1594621692172296



---------------------------------------------------------------------------------------------------------------------

## Java基础

### [JavaOOM类型](JavaSE/JavaOOM类型/JavaOOM类型.md)
### [正则表达式Regex](JavaSE/正则表达式Regex/正则表达式Regex.md)
### [](JavaSE/bit、byte、位、字节、汉字的关系.md)
### [](JavaSE/BloomFilter与CuckooFilter.md)
### [](JavaSE/ClassLoader学习.md)
### [](JavaSE/copyonwrite机制.md)
### [](JavaSE/CPU缓存和内存屏障.md)
### [](JavaSE/java new一个对象的过程中发生了什么.md)
### [](JavaSE/javaagent.md)
### [](JavaSE/Java中boolean类型占用多少个字节.md)
### [](JavaSE/Java中char.md)
### [](JavaSE/java中有三种移位运算符.md)
### [Java反射和agent](JavaSE/Java反射和agent.md)
### [](JavaSE/Java接口默认实现方法.md)
### [](JavaSE/java的jar和war的打包解压.md)
### [](JavaSE/Java的简单类型及其封装器类.md)
### [](JavaSE/java读取文件方式.md)
### [](JavaSE/JAVA调用系统命令Process.md)
### [](JavaSE/Java链式调用-Builder模式.md)
### [](JavaSE/Java锁学习.md)
### [](JavaSE/JMX.md)
### [](JavaSE/JVM与Linux的内存关系详解.md)
### [](JavaSE/N进制转换.md)
### [](JavaSE/OracleJDK和OpenJDK.md)
### [](JavaSE/SecureRandom详解.md)
### [](JavaSE/servlet介绍.md)
### [](JavaSE/Thread线程学习.md)
### [](JavaSE/unsafe类学习.md)
### [](JavaSE/从n个数选出最大的m个的算法比较.md)
### [](JavaSE/代码优化.md)
### [](JavaSE/内存泄漏.md)
### [](JavaSE/内部类.md)
### [](JavaSE/单例模式.md)
### [](JavaSE/图片加载缓慢优化.md)
### [](JavaSE/如何优雅地根治null值.md)
### [](JavaSE/如何计算Java对象所占内存的大小.md)
### [](JavaSE/定位常见Java性能问题.md)
### [](JavaSE/怎么设计hashcode更均衡.md)
### [](JavaSE/日期处理类库.md)
### [](JavaSE/泛型通配符extends与super的区别.md)
### [](JavaSE/深入理解JAVA反序列化漏洞.md)
### [](JavaSE/深入理解JAVA反序列化漏洞.pdf)
### [](JavaSE/负载均衡.md)
### [](JavaSE/进程，线程和协程的区别.md)
### [](JavaSE/重写hashCode()方法和equals()方法及如何重写.md)
### [](JavaSE/锁的种类.md)
### [](JavaSE/零拷贝.md)




---------------------------------------------------------------------------------------------------------------------

## Java集合

### [](collection/HashMap和ConcurrentHashMap学习.md)
### [](collection/java集合.md)

---------------------------------------------------------------------------------------------------------------------

## Java并发

### [](concurrent/Java并发编程的艺术.pdf)
### [](concurrent/Java多线程学习.md)
### [](concurrent/java并发包concurrent和并发工具类.md)
### [](concurrent/java并发基础AQS类.md)
### [](concurrent/ThreadPool线程池.md)
### [](concurrent/并发编程学习.md)



---------------------------------------------------------------------------------------------------------------------

## JVM
### [](JVM/ClassLoader种类和类隔离.md)
### [](JVM/HotSpot VM.md)
### [](JVM/java-GC日志分析.md)
### [](JVM/JVM参数.md)
### [](JVM/jvm学习.md)
### [](JVM/openjdk.md)
### [](JVM/获取JVM进程的线程堆栈dump和堆dump.md)


---------------------------------------------------------------------------------------------------------------------

## IO
### [](IO/epoll的ET和LT模式详解.md)
### [](IO/IO整体.md)
### [](IO/java-AIO.md)
### [](IO/java-BIO.md)
### [](IO/java-nio.md)
### [](IO/Java直接内存.md)
---------------------------------------------------------------------------------------------------------------------
## JDK8
### [](JDK8/CompletableFuture学习.md)
### [](JDK8/ForkJoin框架.md)
### [](JDK8/JDK8学习.md)
### [](JDK8/Lambda表达式和函数式接口.md)
### [](JDK8/ManagedBlocker学习.md)
### [](JDK8/StreamAPI学习.md)
### [](JDK8/函数式编程FP和反应式编程RP.md)
### [](JDK8/函数式编程（functional programming）.md)


---------------------------------------------------------------------------------------------------------------------

## JDK9

### [](JDK9/JDK9以上学习.md)


---------------------------------------------------------------------------------------------------------------------


---------------------------------------------------------------------------------------------------------------------


java中的数字魔法：
1、缓存问题：对于int的变量，在对于值范围在-128到127之间的数，==是相等的，超过这个范围的因为自动装箱，是两个不同的Integer对象，==就不相等了
2、越界问题：
  i==Math.abs(i)： Long、Short、Byte等的如负数Integer.MIN_VALUE值等于自己绝对值Math.abs(Integer.MIN_VALUE))
  i+1<i ：Integer.MAX_VALUE，因为+1越界成为负数
  i != 0 && i == -i：还是Integer.MIN_VALUE
3、浮点奥秘：
  i=i+1，i=i-1：Double.POSITIVE_INFINITY代表正无穷大，正无穷大加减一个数还是正无穷大
  i != i：Double.NaN，



一、缓存问题
JAVA编译器编译Integer a = 50的时候,被翻译成Integer a = Integer.valueOf(50);
而valueOf的源码是下面这样的
‍看到了嘛，Integer内部有一个IntegerCache缓存。对于值范围在-128到127之间的数，会进行缓存。因此a和b范围在-128到127之间，所以指向的是同一个对象，所以判断结果是true。而c和d在128之外，所以每次都是返回一个新对象，所以判断结果是false。


二、越界问题
Math.abs(Integer.MIN_VALUE))的结果是正数还是负数
绝对值运算的原理是判断这个数是否大于零，如果小于零则取负值
Integer.MIN_VALUE，它的十六进制表示是 0x80000000。其符号位为1，其余所有的位都是0。取负数(反码+1)则为 0x7fffffff+1，也就是 0x80000000。你会发现对Integer.MIN_VALUE取负值还是本身。因此，结果还是负数。
这套理论对Long、Short、Byte都成立
Math.abs(Short.MIN_VALUE)=它本身-32768
是否存在一个数i，可以使其满足i+1<i，这样看来，这个i就是Integer.MAX_VALUE，因为加完1就溢出了变为负值了。"
是否存在一个数，满足i != 0 && i == -i"其实还是Integer.MIN_VALUE，原因你刚才说过了！"


三、浮点奥秘
double i = Double.POSITIVE_INFINITY代表正无穷大，无穷大加一个常数还是无穷大，无穷大减去一个常数也是无穷大
所以 i 等于 i+1 等于 i-1

double j = Double.NaN;翻译过来就是(Not a Number)，所以他本身不等于它本身 j != j 是true



---------------------------------------------------------------------------------------------------------------------

java7开始提供Aio的实现，BIO、NIO、AIO
https://blog.csdn.net/anxpp/article/details/51512200
https://www.cnblogs.com/diegodu/p/6823855.html
https://www.cnblogs.com/doit8791/p/4951591.html



Java transient关键字使用小记
https://www.cnblogs.com/lanxuezaipiao/p/3369962.html




ClassLoader 详解及用途
https://blog.csdn.net/u010015108/article/details/52025220

Java代码编译过程简述
https://blog.csdn.net/fuzhongmin05/article/details/54880257


AccessController.doPrivileged
http://blog.csdn.net/laiwenqiang/article/details/54321588


Spring Boot 1.5.8.RELEASE同时配置Oracle和MySQL
https://segmentfault.com/a/1190000012148813
http://blog.csdn.net/thl331860203/article/details/77849662


redis两种持久化方式的优缺点
https://www.cnblogs.com/ssssdy/p/7132856.html


kafka配置
https://hacpai.com/article/1501331595492

https://github.com/search?o=desc&q=tutorials&s=stars&type=Repositories
https://github.com/search?utf8=%E2%9C%93&q=tutorials&type=Repositories


Jupyter notebook（又称IPython notebook）是一个交互式的笔记本，支持运行超过40种编程语言。
https://jupyter.org/
https://jupyterhub.readthedocs.io/en/latest/



JVM安全退出（如何优雅的关闭java服务）：Runtime.addShutdownHook(Thread hook)注册自定义钩子
http://www.voidcn.com/article/p-wfdtbucc-bbs.html


使用ThreadGroup监控线程退出
http://yang.run/2016/03/29/Using-ThreadGroup-to-monitor-thread-exit/


---------------------------------------------------------------------------------------------------------------------

java在读多写少、写多读少分别使用什么类

java并发控制参考
docs/java并发包concurrent和并发工具类.md中的并发工具类


并发控制：
1、乐观锁
2、读写锁：分治思想



在读多写少的环境下使用
1、ConcurrentHashMap
2、copyonwrite的机制：CopyOnWriteArrayList，CopyOnWriteArraySet。
3、读写锁实现



在写多读少环境下：
1、ConcurrentHashMap的思想：切分
2、CAS变量


---------------------------------------------------------------------------------------------------------------------

Java到底是值传递还是引用传递




参考  
https://www.zhihu.com/question/31203609  



---------------------------------------------------------------------------------------------------------------------
