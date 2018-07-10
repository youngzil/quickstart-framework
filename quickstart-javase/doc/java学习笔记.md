sleep和wait方法：
1、这两个方法来自不同的类分别是Thread和Object
2、最主要是sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法。
3、wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用(使用范围)
4、sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常

为什么wait()，notify()方法要和synchronized一起使用？
因为wait()方法是通知当前线程等待并释放对象锁，notify()方法是通知等待此对象锁的线程重新获得对象锁，然而，如果没有获得对象锁，wait方法和notify方法都是没有意义的，即必须先获得对象锁，才能对对象锁进行操作，于是，才必须把notify和wait方法写到synchronized方法或是synchronized代码块中了。




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





