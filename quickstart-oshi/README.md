OSHI 是一款为 Java 语言提供的基于 JNA 的（本机）操作系统和硬件信息库。

JNA(Java Native Access)[4]是一个开源的 Java 框架，是 Sun 公司推出的一种调用本地方法的技术，是建立在经典的 JNI 基础之上的一个框架。之所以说它是 JNI 的替代者，是因为 JNA 大大简化了调用本地方法的过程，使用很方便，基本上不需要脱离 Java 环境就可以完成。

JNI（Java Native Interface） 是 JDK 提供的一个编程接口，它允许 Java 程序调用其他语言编写的程序或者代码库，其实 JDK 本身的实现也大量用到 JNI 技术来调用本地 C 程序库。

通过 OSHI ，我们不需要安装任何其他本机库，就能查看内存和 CPU 使用率、磁盘和分区使用情况、设备、传感器等信息。

OSHI 旨在提供一种跨平台的实现来检索系统信息，支持 Windows、Linux、MacOS、Unix 等主流操作系统。


[OSHI Github](https://github.com/oshi/oshi)

[OSHI介绍](http://oshi.github.io/oshi/oshi-core/project-info.html)

[JNA(Java Native Access)](https://github.com/java-native-access/jna)


使用参考  
[JAVA通过oshi获取系统和硬件信息](https://www.cnblogs.com/songxingzhu/p/9107878.html)  
[获取系统信息（oshi-core）](https://blog.csdn.net/qq_41609208/article/details/105856260)  






