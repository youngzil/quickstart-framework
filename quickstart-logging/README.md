1、Log Facade：现在推荐使用 Log4j-API 或者 SLF4j，不推荐继续使用 JCL（Commons Logging）。
2、Log Implementation：Logback，Log4j2，Log4j，Jul。
3、 logback的appender重写：logback-kafka-appender.md




Java 日志性能那些事
https://www.infoq.cn/article/things-of-java-log-performance
https://www.cnblogs.com/dolphin0520/p/10396894.html




该追加器使您的应用程序可以将其应用程序日志直接发布到Apache Kafka。
https://github.com/danielwegener/logback-kafka-appender


---------------------------------------------------------------------------------------------------------------------
1、总是使用Log Facade，而不是具体Log Implementation
具体来说，现在推荐使用 Log4j-API 或者 SLF4j，不推荐继续使用 JCL。
门面模式（Facade Pattern），也称之为外观模式，其核心为：外部与一个子系统的通信必须通过一个统一的外观对象进行，使得子系统更易于使用。



Slf4j 类似于Commons Logging，是一套简易Java日志门面，本身并无日志的实现。（Simple Logging Facade for Java，缩写Slf4j）。

Commons Logging：简称JCL，是Apache下面的项目。JCL 是一个Log Facade，只提供 Log API，不提供实现，然后有 Adapter 来使用 Log4j 或者 JUL 作为Log Implementation。




Logback，Log4j2，Log4j，Jul。
Sun推出了自己的日志库JUL(Java Util Logging),Jul (Java Util Logging),自Java1.4以来的官方日志实现。



在项目中，Log Implementation的依赖强烈建议设置为runtime scope，并且设置为optional。

设为optional，依赖不会传递，这样如果你是个lib项目，然后别的项目使用了你这个lib，不会被引入不想要的Log Implementation 依赖；

Scope设置为runtime，是为了防止开发人员在项目中直接使用Log Implementation中的类，而不适用Log Facade中的类。



参考
https://zhuanlan.zhihu.com/p/24272450
http://www.cnblogs.com/chenhongliang/p/5312517.html
https://blog.csdn.net/sgdd123/article/details/80701262
https://blog.csdn.net/jeikerxiao/article/details/62423749
http://ckjava.com/2019/02/24/Java-SLF4J-Logback/
https://developer.aliyun.com/article/756446


