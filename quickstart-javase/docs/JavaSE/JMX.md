- [JMX的三层结构](#JMX的三层结构)
- [JVM四类MXBean](#JVM四类MXBean)
- [JMX使用开启](#JMX使用开启)


## JMX的三层结构

JMX的结构一共分为三层：
1、基础层：主要是MBean，被管理的资源。
2、适配层：MBeanServer，主要是提供对资源的注册和管理。
3、接入层：提供远程访问的入口。Connector、Adaptor
agent可以利用protocal adapters（例如HTTP）和connectors使不同的客户端可以访问MBean。


1、MBean：接口和实现类：Standard MBean、Dynamic MBean、Open MBean、Model MBean、MXBean
2、发布到MBeanServer
3、Connector Level：RMI Adaptor、HTTP Adaptor、SNMP Adaptor


1、使用jconsole访问，/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/bin/jconsole
2、使用Connector、Adaptor，客户端使用web访问：http://localhost:8082/
3、客户端通过rmi方式连接JMXConnectorServer，jconsole或者代码



JmxBeanServer其实就是一个巨大的MBean仓库，它并不是什么真正Server，也不会启动线程和端口去侦听客户端的请求，它必须有专门的远程访问连接器和适配器，才能被远程客户端访问。MBean创建和注册后，就相当于在仓库中有了对应的MBean实例，并且还有一个唯一标识它的ObjectName。而各种类型的MBean包括动态和标准的MBean其实都是通过同一个接口DanimicMBean与JmxBeanServer进行交互，特殊情况才需要向下转型。当然本文只是介绍了JmxBeanServer的内部结构，但是jmx还有很多其他的话题，比如JmxBeanServer如何远程访问，事件通知、定期任务等


MBean与MXBean的区别是：定义MBean接口实现类的属性时，MBean只支持Java的八种基本数据和一些简单的引用类型，如String。不能支持复杂数据类型的关联映射。而MXBean接口实现类定义属性时，支持基本数据类型，引用数据类型，和自定义的数据类型。

MBean与MXBean编写规则非常相似,同样是定义xxxMXBean interface,实现xxxMXBean接口;在编写MXBean接口时，也可以不以MXBean结尾，而在是接口上使用@MXBean注释，表示接口为MXBean接口。




当我们启动java进程后，经常会使用jps，jinfo，jmap，jstat等jdk自带的命令去查询进程的状态，这其中的原理就是，当java进程启动后，会创建一个用于本机连接的“localConnectorAddress”放到当前用户目录下，当使用jps等连接时，会到当前用户目录下取到“localConnectorAddress”并连接。


## JVM四类MXBean
OperatingSystemMXBean osMXBean = java.lang.management.ManagementFactory.getOperatingSystemMXBean();
RuntimeMXBean runtimeMXBean = java.lang.management.ManagementFactory.getRuntimeMXBean();
List<GarbageCollectorMXBean> garbageCollectorMXBeanList = ManagementFactory.getGarbageCollectorMXBeans();
List<MemoryPoolMXBean> memoryPoolMXBeanList = java.lang.management.ManagementFactory.getMemoryPoolMXBeans();

java进程自带的mbean
当我们在用jconsole、jvisualvm进行监控java进程时，通常都能看到cpu、内存、线程、垃圾收集等使用情况，其实数据都是通过jmx从jvm提供的一些mbean里面取的。主要如下：
1、ClassLoadMXBean 包括一些类的装载信息，比如有多少类已经装载 / 卸载（unloaded），虚拟机类装载的 verbose 选项（即命令行中的 Java – verbose:class 选项）是否打开，还可以帮助用户打开 / 关闭该选项。
2、GarbageCollectorMXBean：仅仅提供了 GC 的次数和 GC 花费总时间的近似值。但是这个包中还提供了三个的内存管理检测类：MemoryManagerMXBean，MemoryMXBean 和 MemoryPoolMXBean。
3、MemoryManagerMXBean：这个类相对简单，提供了内存管理类和内存池（memory pool）的名字信息。
4、MemoryMXBean：这个类提供了整个虚拟机中内存的使用情况，包括 Java 堆（heap）和非 Java 堆所占用的内存，提供当前等待 finalize 的对象数量，它甚至可以做 gc（实际上是调用 System.gc）。
5、MemoryPoolMXBean：每一个 MemoryPoolMXBean 都包含了该内存池的详细信息，如是否可用、当前已使用内存 / 最大使用内存值、以及设置最大内存值等等。
6、OperatingSystemMXBean：该类提供的是操作系统的简单信息，如构架名称、当前 CPU 数、最近系统负载等。
7、RuntimeMXBean：运行时信息包括当前虚拟机的名称、提供商、版本号，以及 classpath、bootclasspath 和系统参数等等。
8、ThreadMXBean 可以提供的信息包括各个线程的各种状态，CPU 占用情况，以及整个系统中的线程状况。从 ThreadMXBean 可以得到某一个线程的 ThreadInfo 对象。这个对象中则包含了这个线程的所有信息。
9、CompilationMXBean 帮助用户了解当前的编译器和编译情况，该 mxbean 提供的信息不多。





Dynamic MBean工具：pojo-mbean  
[pojo-mbean Github](https://github.com/zhongl/pojo-mbean)  
[pojo-mbean Google Code](https://code.google.com/p/pojo-mbean/)





java进程自带的mbean
当我们在用jconsole、jvisualvm进行监控java进程时，通常都能看到cpu、内存、线程、垃圾收集等使用情况，其实数据都是通过jmx从jvm提供的一些mbean里面取的。主要如下：
ClassLoadingMXBean
ClassLoadMXBean 包括一些类的装载信息，比如有多少类已经装载 / 卸载（unloaded），虚拟机类装载的 verbose 选项（即命令行中的 Java – verbose:class 选项）是否打开，还可以帮助用户打开 / 关闭该选项。

CompilationMXBean
CompilationMXBean 帮助用户了解当前的编译器和编译情况，该 mxbean 提供的信息不多。

GarbageCollectorMXBean
相对于开放人员对 GC 的关注程度来说，该 mxbean 提供的信息十分有限，仅仅提供了 GC 的次数和 GC 花费总时间的近似值。但是这个包中还提供了三个的内存管理检测类：MemoryManagerMXBean，MemoryMXBean 和 MemoryPoolMXBean。

MemoryManagerMXBean
这个类相对简单，提供了内存管理类和内存池（memory pool）的名字信息。

MemoryMXBean
这个类提供了整个虚拟机中内存的使用情况，包括 Java 堆（heap）和非 Java 堆所占用的内存，提供当前等待 finalize 的对象数量，它甚至可以做 gc（实际上是调用 System.gc）。

MemoryPoolMXBean
该信息提供了大量的信息。在 JVM 中，可能有几个内存池，因此有对应的内存池信息，因此，在工厂类中，getMemoryPoolMXBean() 得到是一个 MemoryPoolMXBean 的 list。每一个 MemoryPoolMXBean 都包含了该内存池的详细信息，如是否可用、当前已使用内存 / 最大使用内存值、以及设置最大内存值等等。

OperatingSystemMXBean
该类提供的是操作系统的简单信息，如构架名称、当前 CPU 数、最近系统负载等。

RuntimeMXBean
运行时信息包括当前虚拟机的名称、提供商、版本号，以及 classpath、bootclasspath 和系统参数等等。

ThreadMXBean
在 Java 这个多线程的系统中，对线程的监控是相当重要的。ThreadMXBean 就是起到这个作用。ThreadMXBean 可以提供的信息包括各个线程的各种状态，CPU 占用情况，以及整个系统中的线程状况。从 ThreadMXBean 可以得到某一个线程的 ThreadInfo 对象。这个对象中则包含了这个线程的所有信息。




SNMP：中文名 简单网络管理协议  外文名 Simple Network Management Protocol 
简单网络管理协议（SNMP），由一组网络管理的标准组成，包含一个应用层协议（application layer protocol）、数据库模型（database schema）和一组资源对象。该协议能够支持网络管理系统，用以监测连接到网络上的设备是否有任何引起管理上关注的情况。该协议是互联网工程工作小组（IETF，Internet Engineering Task Force）定义的internet协议簇的一部分。SNMP的目标是管理互联网Internet上众多厂家生产的软硬件平台，因此SNMP受Internet标准网络管理框架的影响也很大。SNMP已经出到第三个版本的协议，其功能较以前已经大大地加强和改进了。





## JMX使用开启
JMX（Java Management Extensions，即Java管理扩展）是一个为Java应用程序植入管理功能的框架。当需要通过JMX对远程服务器上的JVM进行监控时，可以在Java应用启动时增加如下参数：

   -Dcom.sun.management.jmxremote

   -Djava.rmi.server.hostname=100.0.66.1

   -Dcom.sun.management.jmxremote.port=9999

   -Dcom.sun.management.jmxremote.ssl=false

   -Dcom.sun.management.jmxremote.authenticate=false

   其中，ssl=false表示不使用ssl；authenticate=false表示不需要认证，即不需要用户名、密码，如果该参数为true，则还需要其他用户名、密码的相关参数。

   需要注意的是，这几个参数在使用时要连在一起，实际使用过程中发现，如果这几个参数中间有夹杂其他的JVM参数，则可能无法开启JMX的远程访问。



JMX开发：

理解JMX之介绍和简单使用
https://blog.csdn.net/lmy86263/article/details/71037316

开发：
Server服务端：使用MBeanServer注册MBean，使用JMXConnectorServer.start()发布
Client客户端：MBeanServerConnection可以获取domain、ObjectName、MBean信息，下面一行可以获取MBean的接口实例
MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
HelloWorldMBean proxy = MBeanServerInvocationHandler.newProxyInstance(mbsc, mBeanName, HelloWorldMBean.class, false);




参考
http://www.cnblogs.com/dongguacai/p/5900507.html
https://blog.csdn.net/u013256816/article/details/52800742 
https://blog.csdn.net/fanyingnew/article/details/1546238
https://blog.csdn.net/drykilllogic/article/details/38384045
https://blog.csdn.net/j43350860/article/details/5885474
https://blog.csdn.net/hanxingwang0806/article/details/25161243
https://blog.csdn.net/u013068377/article/details/78342186
http://www.open-open.com/lib/view/open1397359125465.html
https://blog.csdn.net/expleeve/article/details/37502501
https://blog.csdn.net/java_huashan/article/details/34100543
https://blog.csdn.net/chaofanwei2/article/details/51291118
http://blog.sina.com.cn/s/blog_45d7a5280100ngyk.html


