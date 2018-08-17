Java热部署：
JRebel
使用 spring-loaded 实现 jar 包热部署


热部署的本质，简单的理解，是在运行中实时增加、替换JVM中的类文件而无需重启JVM。

众所周知，JVM使用ClassLoader加载类文件，内含的双亲委派模型通过指定类文件加载的顺序避免由于类冲突而导致核心类库加载失败。
单个ClassLoader不能加载全限定名相同的类；不能修改已加载的类的声明；不能卸载已加载的类，除非移除整个ClassLoader，或者被GC回收。


java热部署关键:
java.lang.instrument.Instrumentation的方法：
retransformClasses对于已经加载的类重新进行转换处理，即会触发重新加载类定义，需要注意的是，新加载的类不能修改旧有的类声明，譬如不能增加属性、不能修改方法声明
redefineClasses：替换掉JVM中已经加载的类字节码，与如上类似，但不是重新进行转换处理，而是直接把处理结果(bytecode)直接给JVM

我们知道，通过设置系统参数或者通过虚拟机启动参数，我们可以设置一个虚拟机运行时的 boot class 加载路径（-Xbootclasspath）和 system class（-cp）加载路径。当然，我们在运行之后无法替换它。然而，我们也许有时候要需要把某些 jar 加载到 bootclasspath 之中，而我们无法应用上述两个方法；或者我们需要在虚拟机启动之后来加载某些 jar 进入 bootclasspath。在 Java SE 6 之中，我们可以做到这一点了。
在 premain/agantmain 之中加上需要的 classpath。我们可以在我们的 Transformer 里使用 appendToBootstrapClassLoaderSearch/appendToSystemClassLoaderSearch 来完成这个任务。
java.lang.instrument.Instrumentation的方法
appendToBootstrapClassLoaderSearch/appendToSystemClassLoaderSearch 来完成这个任务。


想要在jvm启动后，动态的加载class类文件，我们首先需要了解Instrumentation、Attach、Agent、VirtualMachine、ClassFileTransformer这几个类的用法和他们之间的关系。 

VirtualMachine通过给attach方法连接到jvm上，loadAgent方法向jvm注册一个代理程序agent（agentmain），通过Instrumentation实例进行字节码处理


1、VirtualMachine类：该类允许我们通过给attach方法传入一个jvm的pid（进程id），远程连接到jvm上。然后我们可以通过loadAgent方法向jvm注册一个代理程序agent，在该agent的代理程序中会得到一个Instrumentation实例，该实例可以在class加载前改变class的字节码，可以在class加载后重新加载。在调用Instrumentation实例的方法时，这些方法会使用ClassFileTransformer接口中提供的方法进行处理。 
VirtualMachine中的attach(String id)方法允许我们通过jvm的pid，远程连接到jvm。当通过Attach API连接到JVM的进程上后，系统会加载management-agent.jar，然后在JVM中启动一个Jmx代理，最后通过Jmx连接到虚拟机。

2、目前Agent类的启动有两种方式，
一种是在JDK5版本中提供随JVM启动的Agent，我们称之为premain方式。
另一种是在JDK6中在JDK5的基础之上又提供了JVM启动之后通过Attach去加载的Agent类，我们称之为agentmain方式。 

21、premain启动代理的方式： 
在jvm的启动参数中加入 
-javaagent:jarpath[=options]  

22、agentmain启动代理的方式： 
先通过VirtualMachine.attach(targetVmPid)连接到虚拟机，然后通过virtualmachine.loadAgent(jmxAgent, "com.sun.management.jmxremote");注册agent代理类。 

3、
调用inst.getAllLoadedClasses()得到所有已经加载过的类。
调用inst.addTransformer(new SdlTransformer(), true)增加一个可重转换转换器。
调用inst.retransformClasses(Class cls)，向jvm发起重转换请求。 

Java Instrutment只提供了JVM TI中非常小的一个功能子集，
一个是允许在类加载之前，修改类字节(ClassFileTransformer)(JDK5中开始提供，即使随JVM启动的Agent)
另外一个是在类加载之后，触发JVM重新进行类加载(JDK6中开始提供，用于JVM启动之后通过Attach去加载Agent)
这两个功能表面看起来微不足道，但实际非常强大，AspectJ AOP的动态Weaving、Visual VM的性能剖析、JConsole支持Attach到进程上进行监控，都是通过这种方式来做的。

1.主要API(java.lang.instrutment) 
1）ClassFileTransformer：定义了类加载前的预处理类，可以在这个类中对要加载的类的字节码做一些处理，譬如进行字节码增强 
2）Instrutmentation：增强器，由JVM在入口参数中传递给我们，提供了如下的功能 
addTransformer/ removeTransformer：注册/删除ClassFileTransformer
retransformClasses：对于已经加载的类重新进行转换处理，即会触发重新加载类定义，需要注意的是，新加载的类不能修改旧有的类声明，譬如不能增加属性、不能修改方法声明
redefineClasses：与如上类似，但不是重新进行转换处理，而是直接把处理结果(bytecode)直接给JVM
getAllLoadedClasses：获得当前已经加载的Class，可配合retransformClasses使用
getInitiatedClasses：获得由某个特定的ClassLoader加载的类定义
getObjectSize：获得一个对象占用的空间，包括其引用的对象
appendToBootstrapClassLoaderSearch/appendToSystemClassLoaderSearch：增加BootstrapClassLoader/SystemClassLoader的搜索路径



http://zheng12tian.iteye.com/blog/1495037
https://www.ibm.com/developerworks/cn/java/j-lo-jse61/index.html
https://www.zhihu.com/question/61040749
https://my.oschina.net/xianggao/blog/364068
https://segmentfault.com/a/1190000011174467
http://rejoy.iteye.com/blog/2387833
https://blog.csdn.net/chenjie19891104/article/details/42807959


