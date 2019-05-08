
Java 生态系统提供了标准的格式来分发同一个应用中的所有 Java 类。我们可以将这些类打包为 JAR（Java Archive）、WAR（Web Archive）以及 EAR（Enterprise Archive），在这些格式中包含了前端、后端以及嵌入其中的库。

---------------------------------------------------------------------------------------------------------------------
sleep和wait方法：
1、这两个方法来自不同的类分别是Thread和Object
2、最主要是sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法。
3、wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用(使用范围)
4、sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常

为什么wait()，notify()方法要和synchronized一起使用？
因为wait()方法是通知当前线程等待并释放对象锁，notify()方法是通知等待此对象锁的线程重新获得对象锁，然而，如果没有获得对象锁，wait方法和notify方法都是没有意义的，即必须先获得对象锁，才能对对象锁进行操作，于是，才必须把notify和wait方法写到synchronized方法或是synchronized代码块中了。


java 接口之多继承,类为什么不可以多继承
接口 是可以多继承的。接口（jdk 1.7 以下版本）里面的方法并有实现,即使接口之间具有相同的方法仍然是可以的 几个接口可以有想通的实现类和实现方法。而且接口 接口里面的成员变量都是 static   final的  有自己静态域 只能自己使用。
https://blog.csdn.net/buzaiguihun/article/details/52996818


---------------------------------------------------------------------------------------------------------------------

java7开始提供Aio的实现，BIO、NIO、AIO
https://blog.csdn.net/anxpp/article/details/51512200
https://www.cnblogs.com/diegodu/p/6823855.html
https://www.cnblogs.com/doit8791/p/4951591.html



Java transient关键字使用小记
https://www.cnblogs.com/lanxuezaipiao/p/3369962.html



LinkedHashMap如何保证元素迭代的顺序
http://www.php.cn/java-article-362041.html
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

---------------------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------------------


