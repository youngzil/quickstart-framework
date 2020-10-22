- [JPDA由三个独立的模块JVMTI、JDWP、JDI组成](#JPDA由三个独立的模块JVMTI、JDWP、JDI组成)
    - [JVMTI功能](#JVMTI功能)
    - [JVMTI从功能上大致可以分为4类](#JVMTI从功能上大致可以分为4类)
- [基于Java Instrument的Agent实现](#基于Java-Instrument的Agent实现)
- [Java程序的诊断和调试](#Java程序的诊断和调试)
- [Java探针javaAgent的使用](#Java探针javaAgent的使用)
- [Javaagent的主要功能](#Javaagent的主要功能)
- [Java的热部署和热加载：联系、区别、原理、使用场景](#Java的热部署和热加载：联系、区别、原理、使用场景)
- [字节码修改工具框架](#字节码修改工具框架)
- [常用 Java Profiling 工具的分析与比较](#常用-Java-Profiling-工具的分析与比较)
- [内存分析工具](#内存分析工具)
- [远程Debugging工具：IDEA、eclipse](#远程Debugging工具：IDEA、eclipse)
- [内存分析工具](#内存分析工具)
- [远程监控工具：JMX（Java Management Extensions）](#远程监控工具：JMX（Java Management Extensions）)
- [理解JVM的safepoint](#理解JVM的safepoint)


-------------------------------------------------------------------------



Java程序的诊断和调试：总体运行情况、提升效率和瓶颈、内存问题


JVMTIAgent的Java实现：基于Java Instrument API实现Agent


javaagent使用 + 字节码修改框架
动态代理和静态代理：参考quickstart-proxy/doc
java探针javaAgent的使用：
java的热部署和热加载：联系、区别、原理、使用场景
java热加载：VirtualMachine、Attach、Agent、Instrumentation：只能修改方法体，不能修改的的结构，不能变更方法签名、增加和删除方法/类的成员属性





热部署：一般是使用自定义的ClassLoader来加载应用，类似tomcat中配置的context，检测到class文件有变化，就重新加载项目，并替换之前的classloader
热加载：就是通过Attach技术使用javaagent来重新加载类，retransformClasses对于已经加载的类重新进行转换处理，即会触发重新加载类定义，或者使用字节码修改框架来实现类
热部署可以进行模块化开发：比如可以进行同一个类的不同版本的加载，不同项目的加载（依赖jar版本不一样），直接进行不同版本的jar加载
模块可以是一个类的不同版本、不同的包路径下的代码、同一个jar的不同版本，同一个项目的不同模块，任何一个 Java 类库
模块化好处：便于项目管理，解耦：开发、测试、交付、升级，引用其他类库都可以独立进行


字节码修改框架：ASM、Javassist、AspectJ（编译器级别）

动态代理：JDK动态代理、CGLIB（可以使用jdk动态代理，也可以使用基于asm修改字节码的实现）
静态代理：JDK代理设计模式、Javassist

目前字节码修改技术有ASM，javassist等。cglib就是基于封装的Asm. Spring 就是使用cglib代理库。
AspectJ是插件形式，编译期注入代理代码



性能分析工具：Profiler
内存分析工具：获取和分析Dump的几种工具
远程Debugging工具：IDEA、eclipse：原理就是java探针agent：引入jdwp这个Agent 的
远程监控工具：JMX（Java Management Extensions），即JAVA管理扩展，用来监视和管理JVM以及其运行的操作系统。9个MXBean


Java Profiler工具的原理
Profiler的常见问题
java profiler工具使用：JProfiler、async-profiler


堆分析器或者 Dump 文件分析器等工具
java dump分类
dump获取
dump分析
Java Dump 中的很多线程处于 state:CW 和 state:B 状态，它们之间有何区别


Java的Profiling和Debugging


理解JVM的safepoint定义、特定位置有哪些，使用场景，
JVM的GC等待所有线程safepoint导致JVM就Freezen了
JVM让所有线程主动进入safepoint状态有两种执行方式：解释型和编译型(JIT)




---------------------------------------------------------------------------------------------------------------------

## 基于Java Instrument的Agent实现

对于instrumentation agent来说有两种代理方式，
一种是在类加载之前进行代理，可以进行字节码的修改即所谓的agent on load。
另一种是在运行时动态进行加载，这种方法对于字节码的修改有较大的限制，但是利用运行时动态加载可以获得JVM的一些运行时信息，这种方式为agent on attach 。


premain
以vm参数的形式载入，在程序main方法执行之前执行
其jar包的manifest需要配置属性Premain-Class

agentmain
动态注入，JVM运行时动态注入
其jar包的manifest需要配置属性Agent-Class



premain：
public static void premain(String agentArgs, Instrumentation inst);  [1]
public static void premain(String agentArgs); [2]

java -javaagent:jar文件的位置 [= 传入 premain 的参数 ]

转换发生在 premain 函数执行之后，main 函数执行之前，这时每装载一个类，transform 方法就会执行一次，看看是否需要转换



agentmain：
public static void agentmain (String agentArgs, Instrumentation inst); [1] 
public static void agentmain (String agentArgs);[2]

与“Premain-Class”类似，开发者必须在 manifest 文件里面设置“Agent-Class”来指定包含 agentmain 函数的类。



 Instrument两个核心API
1、ClassFileTransformer：定义了类加载前的预处理类，可以在这个类中对要加载的类的字节码做一些处理，譬如进行字节码增强；
2、Instrumentation：增强器，由JVM在入口参数中传递给我们，提供了如下的功能：
    1、addTransformer/removeTransformer：注册/删除ClassFileTransformer；
    2、retransformClasses：对于已经加载的类重新进行转换处理，即会触发重新加载类定义，需要注意的是，新加载的类不能修改旧有的类声明，譬如不能增加属性、不能修改方法声明；
    3、redefineClasses：与如上类似，但不是重新进行转换处理，而是直接把处理结果(bytecode)直接给JVM；
    4、getAllLoadedClasses：获得当前已经加载的Class，可配合retransformClasses使用；
    5、getInitiatedClasses：获得由某个特定的ClassLoader加载的类定义；
    6、getObjectSize：获得一个对象占用的空间，包括其引用的对象；
    7、appendToBootstrapClassLoaderSearch/appendToSystemClassLoaderSearch：增加BootstrapClassLoader/SystemClassLoader的搜索路径；
    8、isNativeMethodPrefixSupported/setNativeMethodPrefix：判断JVM是否支持拦截Native Method；



[从两款开源工具学习 Java_Instrumentation 技术](https://paper.seebug.org/1071/) :  
[JavaProbe(0Kee-Team)](https://github.com/0Kee-Team/JavaProbe)  
[OpenRasp(Baidu)](https://github.com/baidu/openrasp)

JavaProbe(0Kee-Team)和OpenRasp(Baidu)的源码，两者都使用了instrumentation agent技术，但是由于场景不同，所以使用的差异也比较大


OpenRasp使用agent在jvm初始化后进入premain方法，将自定义的ClassTransformer注册到instrumentation中，在有类加载时会触发其的transform方法，其根据匹配的class去调用具体hook的transform方法，在里面使用了javassit来操作字节码来改变被hook的class类定义时的字节码。

JavaProbe为了无侵入地获得所有JVM的运行时信息，采用instrumentation的agentmain，独立于其他目标JVM，可以动态将代理attach到指定的JVM上去获取有关的信息。


参考  
[基于Java Instrument的Agent实现](https://www.jianshu.com/p/b72f66da679f)  
[JVM之-----热部署hotswap](https://blog.csdn.net/nameix/article/details/52277464)


---------------------------------------------------------------------------------------------------------------------

## JPDA由三个独立的模块JVMTI、JDWP、JDI组成
 JDI：调试者通过 JDI 发送接受调试命令。
 JDWP 定义调试者和被调试者交流数据的格式。
 JVMTI 可以控制当前虚拟机运行状态。

https://my.oschina.net/as7365/blog/716702
https://blog.csdn.net/xiangwanpeng/article/details/79787108
https://www.ibm.com/developerworks/cn/java/j-lo-jpda2/index.html
https://docs.huihoo.com/vm/jvmti.html

如果您的应用程序具有使用 Java™ 虚拟机概要分析接口 (JVMPI) 或 Java 虚拟机调试接口 (JVMDI) 的本机代码，那么这些接口会受到从 32 位更改为 64 位的影响。


名词解释：
1.JPDA（Java Platform Debugger Architecture）： Java 平台调试体系结构的缩写，通过 JPDA 提供的 API，开发人员可以方便灵活的搭建 Java 调试应用程序。JPDA 主要由三个部分组成：Java 虚拟机工具接口（JVMTI），Java 调试线协议（JDWP），以及 Java 调试接口（JDI）
2.JVMTI（JVM Tool Interface）：Java 虚拟机工具接口，JDK提供的一套用于开发JVM监控, 问题定位与性能调优工具的通用编程接口。JVMTI 的前身是 JVMDI 和 JVMPI。
3.JDWP（Java Debug Wire Protocol）：Java 调试线协议，是一个为 Java 调试而设计的一个通讯交互协议，它定义了调试器和被调试程序之间传递的信息的格式。在 JPDA 体系中，作为前端（front-end）的调试者（debugger）进程和后端（back-end）的被调试程序（debuggee）进程之间的交互数据的格式就是由 JDWP 来描述的，它详细完整地定义了请求命令、回应数据和错误代码，保证了前端和后端的 JVMTI 和 JDI 的通信通畅。
4.JDI（Java Debug Interface）：是 JPDA 三层模块中最高层的接口，定义了调试器（Debugger）所需要的一些调试接口。基于这些接口，调试器可以及时地了解目标虚拟机的状态，例如查看目标虚拟机上有哪些类和实例等。另外，调试者还可以控制目标虚拟机的执行，例如挂起和恢复目标虚拟机上的线程，设置断点等。
5.JVMPI（Java Virtual Machine Profiler Interface）：可以监控JVM发生的各种事件。例如当JVM创建，关闭，Java类被加载，创建对象，或GC回收，等37种事件。
6.JVMDI（Java Virtual Machine Debug Interface）：Java 虚拟机调试接口。
JVMTI的前身为JVMPI及JVMDI，Java5.0中这两套接口已被JVMTI取代，JVMDI在java6中已经不支持，JVMPI在Java7中将彻底不存在。


JPDA 层次比较
模块    层次    编程语言    作用
JVMTI    底层    C    获取及控制当前虚拟机状态
JDWP    中介层    C    定义 JVMTI 和 JDI 交互的数据格式
JDI    高层    Java    提供 Java API 来远程控制被调试虚拟机


J总结：
 JPDA 定义了一套如何开发调试工具的接口和规范。
 JPDA 由三个独立的模块 JVMTI、JDWP、JDI 组成。
 调试者通过 JDI 发送接受调试命令。
 JDWP 定义调试者和被调试者交流数据的格式。
 JVMTI 可以控制当前虚拟机运行状态。
 除了标准实现，JPDA 还有许多开源实现供使用（Apache Harmony、org.eclipse.jdt.debug）。
 Java 调试工具的优点。



### JVMTI功能
JVMTI 提供了可用于 debug 和 profiler 的接口；同时，在 Java 5/6 中，虚拟机接口也增加了监听（Monitoring），线程分析（Thread analysis）以及覆盖率分析（Coverage Analysis）等功能。

概述
JVM TI是JDK提供的一套用于开发JVM监控, 问题定位与性能调优工具的通用编程接口（API）。
通过JVMTI，我们可以开发各式各样的JVMTI Agent。这个Agent的表现形式是一个以c/c++语言编写的动态共享库。

JVMTI Agent原理: java启动或运行时，动态加载一个外部基于JVM TI编写的dynamic module到Java进程内，然后触发JVM源生线程Attach Listener来执行这个dynamic module的回调函数。在函数体内，你可以获取各种各样的VM级信息，注册感兴趣的VM事件，甚至控制VM的行为。





### JVMTI从功能上大致可以分为4类
1. Heap
获取所有类的信息，对象信息，对象引用关系，Full GC开始/结束，对象回收事件等。
2. 线程与堆栈
获取所有线程的信息，线程组信息，控制线程（start,suspend,resume,interrupt…）, Thread Monitor(Lock)，得到线程堆栈，控制出栈，方法强制返回，方法栈本地变量等。
3. Class & Object & Method & Field 元信息
class信息，符号表，方法表，redefine class（hotswap）, retransform class，object信息，fields信息，method信息等。
4. 工具类
线程cpu消耗，classloader路径修改，系统属性获取等。

开发jvm ti agent，简单的来讲，就是开发一个c/c++的共享库。在windows下后缀是dll，linux/unix下是so，mac下就是dylib。所以我们创建工程和编译环境的时候，记得以共享库(share library)的形式来构建。



## Java程序的诊断和调试

1、总体运行情况：在 Java 程序运行的过程中，程序员希望掌握它总体的运行状况，这个时候程序员可以直接使用 JDK 提供的 jconsole 程序。
2、提升效率，瓶颈：如果希望提高程序的执行效率，开发人员可以使用各种 Java Profiler。这种类型的工具非常多，各有优点，能够帮助开发人员找到程序的瓶颈，从而提高程序的运行速度。
3、内存问题：开发人员还会遇到一些与内存相关的问题，比如内存占用过多，大量内存不能得到释放，甚至导致内存溢出错误（OutOfMemoryError）等等，这时可以把当前的内存输出到 Dump 文件，再使用堆分析器或者 Dump 文件分析器等工具进行研究，查看当前运行态堆（Heap）中存在的实例整体状况来诊断问题。


---------------------------------------------------------------------------------------------------------------------
## Java探针javaAgent的使用


https://blog.csdn.net/u010039929/article/details/62881743
https://blog.csdn.net/u010039929/article/details/70117018
https://juejin.im/post/5b0925ec51882538aa1ee248
https://www.infoq.cn/article/javaagent-illustrated
https://www.jianshu.com/p/6096bfe19e41
https://www.ibm.com/developerworks/cn/java/j-lo-jse61/index.html

Java探针javaAgent的使用：参考quickstart-proxy
JVMTI的Java实现：基于Java Instrument API实现Agent
premain和agentmain


premain
以vm参数的形式载入，在程序main方法执行之前执行
其jar包的manifest需要配置属性Premain-Class

agentmain
动态注入，JVM运行时动态注入
其jar包的manifest需要配置属性Agent-Class


premain
1、在main之前运行，
2、ClassFileTransformer接口的方法transform
byte[] transform(  ClassLoader         loader,
                String              className,
                Class<?>            classBeingRedefined,
                ProtectionDomain    protectionDomain,
                byte[]              classfileBuffer)
        throws IllegalClassFormatException;
3、使用：java -javaagent:PreMain.jar=Hello1 -javaagent:PreMain.jar=Hello2 -jar Main.jar -javaagent:PreMain.jar=Hello3
   只会有前个生效，第三个是无效的。 
4、premain 只能在类加载之前修改字节码，类加载之后无能为力，只能通过重新创建ClassLoader 这种方式重新加载。

agentmain
1、可以后期注入方式执行，应用程序的VM启动后在动态添加代理的方式，即agentmain方式
2、热部署





前一节讲述了基于JVMTI如何实现Agent，还有一种是基于Java Instrument API实现Agent，可以在Java代码层面编写Agent代码，而非基于C++/C的代码

以-javaagent为例，工作原理
1、在JVM启动时，通过JVM参数-javaagent，传入agent jar，Instrument Agent被加载；
2、在Instrument Agent 初始化时，注册了JVMTI初始化函数eventHandlerVMinit；
3、在JVM启动时，会调用初始化函数eventHandlerVMinit，启动了Instrument Agent，用sun.instrument.instrumentationImpl类里的方法loadClassAndCallPremain方法去初始化Premain-Class指定类的premain方法；
4、初始化函数eventHandlerVMinit，注册了class解析的ClassFileLoadHook函数；
5、在解析Class之前，JVM调用JVMTI的ClassFileLoadHook函数，钩子函数调用sun.instrument.instrumentationImpl类里的transform方法，通过TransformerManager的transformer方法最终调用我们自定义的Transformer类的transform方法；
6、因为字节码在解析Class之前改的，直接使用修改后的字节码的数据流替代，最后进入Class解析，对整个Class解析无影响；
7、重新加载Class依然重新走5-6步骤；


## Javaagent的主要功能
1、可以在加载 class 文件之前做拦截，对字节码做修改
2、可以在运行期对已加载类的字节码做变更，但是这种情况下会有很多的限制，后面会详细说
3、还有其他一些小众的功能
  获取所有已经加载过的类
  获取所有已经初始化过的类（执行过 clinit 方法，是上面的一个子集）
  获取某个对象的大小
  将某个 jar 加入到 bootstrap classpath 里作为高优先级被 bootstrapClassloader 加载
  将某个 jar 加入到 classpath 里供 AppClassloard 去加载
  设置某些 native 方法的前缀，主要在查找 native 方法的时候做规则匹配
  
javaagent 除了做字节码上面的修改之外，其实还有一些小功能，有时候还是挺有用的
获取所有已经被加载的类：Class[] getAllLoadedClasses(); 
获取所有已经初始化了的类： Class[] getInitiatedClasses(ClassLoader loader); 
获取某个对象的大小： long getObjectSize(Object objectToSize); 
将某个 jar 加入到 bootstrap classpath 里优先其他 jar 被加载： void appendToBootstrapClassLoaderSearch(JarFile jarfile); 
将某个 jar 加入到 classpath 里供 appclassloard 去加载：void appendToSystemClassLoaderSearch(JarFile jarfile); 
设置某些 native 方法的前缀，主要在找 native 方法的时候做规则匹配： void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix)。


---------------------------------------------------------------------------------------------------------------------

Java agent premain 详细介绍
参数 javaagent 可以用于指定一个 jar 包，并且对该 java 包有2个要求：
1、这个 jar 包的MANIFEST.MF 文件必须指定 Premain-Class 项。
2、Premain-Class 指定的那个类必须实现 premain（）方法。

重点就在 premain 方法，也就是我们今天的标题。从字面上理解，就是运行在 main 函数之前的的类。当Java 虚拟机启动时，在执行 main 函数之前，JVM 会先运行 -javaagent 所指定 jar 包内 Premain-Class 这个类的 premain 方法，其中，该方法可以签名如下：
1.public static void premain(String agentArgs, Instrumentation inst)
2.public static void premain(String agentArgs)

JVM 会优先加载 1 签名的方法，加载成功忽略 2，如果1 没有，加载 2 方法。这个逻辑在sun.instrument.InstrumentationImpl 类中：


同样Java agent agentmain 使用步骤如下：
1、定义一个MANIFEST.MF 文件，文件中必须包含 Agent-Class;
2、创建一个 Agent-Class 指定的类，该类必须包含 agentmain 方法（参数和 premian 相同）。
3、将MANIFEST.MF 和 Agent 类打成 jar 包;
4、将 jar 包载入目标虚拟机。目标虚拟机将会自动执行 agentmain 方法执行方法逻辑，同时，ClassFileTransformer 也会长期有效，在每一个类加载器加载 Class 的时候都会拦截。


操作步骤：
1、导出PreMain.jar
导出时候选择使用src/META-INF/MANIFEST.MF

最后导出的jar中的MANIFEST.MF内容如下：
Manifest-Version: 1.0
Premain-Class: org.quickstart.proxy.java.agent.PreMyProgram
Can-Redefine-Classes: true


2、导出Main.jar
导出时候可以使用默认的生成的MANIFEST.MF或者指定自己写的MANIFEST.MF

最后导出的jar中的MANIFEST.MF内容如下：
Manifest-Version: 1.0
Main-Class: org.quickstart.proxy.java.agent.MyProgram


3、进入桌面路径下面执行：
java -javaagent:PreMain.jar=Hello1 -javaagent:PreMain.jar=Hello2 -jar Main.jar.jar

特别提醒：
（1）-javaagent参数必须放在-jar参数前面，否则不会生效。也就是说，放在主程序后面的 agent 是无效的。

比如执行：
java -javaagent:PreMain.jar=Hello1 -javaagent:PreMain.jar=Hello2 -jar Main.jar -javaagent:PreMain.jar=Hello3
只会有前个生效，第三个是无效的。 



参考
https://www.jianshu.com/c/7f48adacf83e
https://www.cnblogs.com/aspirant/p/8796974.html
https://blog.csdn.net/catoop/article/details/51034778
http://blog.oneapm.com/apm-tech/509.html
https://blog.csdn.net/hello2mao/article/details/77488073
https://www.ibm.com/developerworks/cn/java/j-lo-hotdeploy/index.html



Java agent premain参考
https://www.cnblogs.com/aspirant/p/8796974.html
 

JAVA AgentMain开发示例
https://blog.csdn.net/u010039929/article/details/70117018
https://www.jianshu.com/c/7f48adacf83e


Instrumentation 简介
https://www.cnblogs.com/orionhp/p/6362625.html


Java系列笔记(3) - Java 内存区域和GC机制
http://www.cnblogs.com/zhguang/p/3257367.html
 


主清单MANIFEST.MF
先说一下文件格式：

Manifest-Version: 1.0
Main-Class: com.socket.server.SimpleHttpServer
Class-Path: lib/commons-beanutils-1.8.0.jar
  lib/commons-codec-1.3.jar
  lib/commons-io-1.4.jar
  lib/commons-logging-1.1.1.jar
  lib/httpclient-4.0.jar
  lib/httpcore-4.0.1.jar
  lib/jcouchdb-1.0.1-1.jar
  
Manifest-Version是指程序的版本号 
Main-Class是指程序的主方法入口类 
Class-Path就指定了外来jar包的位置 

注意事项： 
(1)Manifest-Version、Main-Class、Class-Path冒号后要有一个空格 
(2)Class-Path换行后，每增加一行之前都要加两个空格，不要使用TAB键，否则会报错：invalid header 
(3)在文件最后加两个回车




使用 spring-loaded 实现 jar 包热部署
在项目开发中我们可以把一些重要但又可能会变更的逻辑封装到某个 logic.jar 中，当我们需要随时更新实现逻辑的时候，可以在不重启服务的情况下让修改后的 logic.jar 被重新加载生效。
spring-loaded是一个开源项目，项目地址:https://github.com/spring-projects/spring-loaded

使用方法：

在启动主程序之前指定参数
-javaagent:C:/springloaded-1.2.5.RELEASE.jar -noverify
如果你想让 Tomat 下面的应用自动热部署，只需要在 catalina.sh 中添加：
set JAVA_OPTS=-javaagent:springloaded-1.2.5.RELEASE.jar -noverify
这样就完成了 spring-loaded 的安装，它能够自动检测Tomcat 下部署的webapps ，在不重启Tomcat的情况下，实现应用的热部署。

通过使用 -noverify 参数，关闭 Java 字节码的校验功能。 
使用参数 -Dspringloaded=verbose;explain;watchJars=tools.jar 指定监视的jar （verbose;explain; 非必须），多个jar用“冒号”分隔，如 watchJars=tools.jar:utils.jar:commons.jar

当然，它也有一些小缺限： 
1. 目前官方提供的1.2.4 版本在linux上可以很好的运行，但在windows还存在bug，官网已经有人提出：https://github.com/spring-projects/spring-loaded/issues/145 
2. 对于一些第三方框架的注解的修改，不能自动加载，比如：spring mvc的@RequestMapping 
3. log4j的配置文件的修改不能即时生效。


---------------------------------------------------------------------------------------------------------------------

## Java的热部署和热加载：联系、区别、原理、使用场景

热加载是指可以在不重启服务的情况下让更改的代码生效，热加载可以显著的提升开发以及调试的效率，它是基于 Java 的类加载器实现的，但是由于热加载的不安全性，一般不会用于正式的生产环境。


热部署与热加载联系
不重启服务器编译/部署项目
基于Java的类加载器实现

热部署与热加载的区别

在部署方式上：
热部署在服务器运行时重新加载部署项目
热加载是在运行时重新加载 class。

在实现原理上：
热部署是直接重新加载整个应用，耗时相对较高。
热加载是在运行时重新加载 class，后台会启动一个线程不断检测你的类是否改变。

在使用场景上：
热部署更多的是在生产环境使用。
热加载则更多的是在开发环境上使用。线上由于安全性问题不会使用，难以监控。


热加载几乎没有安全性，直接修改Java虚拟机中的字节码文件，难以监控和控制
热加载有个通俗的名字就是开发者模式

在 Java 开发领域，热部署一直是一个难以解决的问题，目前的 Java 虚拟机只能实现方法体的修改热部署，对于整个类的结构修改，仍然需要重启虚拟机，对类重新加载才能完成更新操作。


热部署：一般是使用自定义的ClassLoader来加载应用，类似tomcat中配置的context，检测到class文件有变化，就重新加载项目，并替换之前的classloader
热加载：就是通过Attach技术使用javaagent来重新加载类，retransformClasses对于已经加载的类重新进行转换处理，即会触发重新加载类定义，或者使用字节码修改框架来实现类
热部署可以进行模块化开发：比如可以进行同一个类的不同版本的加载，不同项目的加载（依赖jar版本不一样），直接进行不同版本的jar加载
模块可以是一个类的不同版本、不同的包路径下的代码、同一个jar的不同版本，同一个项目的不同模块，任何一个 Java 类库


premain
以vm参数的形式载入，在程序main方法执行之前执行
其jar包的manifest需要配置属性Premain-Class

agentmain
动态注入，JVM运行时动态注入
其jar包的manifest需要配置属性Agent-Class


http://fanyilun.me/2017/07/18/%E8%B0%88%E8%B0%88Java%20Intrumentation%E5%92%8C%E7%9B%B8%E5%85%B3%E5%BA%94%E7%94%A8/
https://yq.aliyun.com/articles/65023
https://www.ibm.com/developerworks/cn/java/j-lo-hotdeploy/
https://blog.csdn.net/u014527619/article/details/79404482
https://mp.weixin.qq.com/s/TTnWr-rHqFZl14FSeWX9sg


java热加载：
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




---------------------------------------------------------------------------------------------------------------------

## 字节码修改工具框架

https://www.infoq.cn/article/Living-Matrix-Bytecode-Manipulation

https://blog.51cto.com/7317859/2106469

字节码修改框架
ASM、Javassist、AspectJ（编译器）

目前字节码修改技术有ASM，javassist等。cglib就是基于封装的Asm. Spring 就是使用cglib代理库。

ASM 是一个 Java 字节码操控框架。它能够以二进制形式修改已有类或者动态生成类。ASM 可以直接产生二进制 class 文件，也可以在类被加载入 Java 虚拟机之前动态改变类行为。ASM 从类文件中读入信息后，能够改变类行为，分析类信息，甚至能够根据用户要求生成新类。
不过ASM在创建class字节码的过程中，操纵的级别是底层JVM的汇编指令级别，这要求ASM使用者要对class组织结构和JVM汇编指令有一定的了解。

Java字节码生成开源框架介绍--Javassist：

Javassist是一个开源的分析、编辑和创建Java字节码的类库。是由东京工业大学的数学和计算机科学系的 Shigeru Chiba （千叶 滋）所创建的。它已加入了开放源代码JBoss 应用服务器项目,通过使用Javassist对字节码操作为JBoss实现动态AOP框架。javassist是jboss的一个子项目，其主要的优点，在于简单，而且快速。直接使用java编码的形式，而不需要了解虚拟机指令，就能动态改变类的结构，或者动态生成类。


动态代理：JDK动态代理、CGLIB（可以使用jdk动态代理，也可以使用基于asm修改字节码的实现）
静态代理：JDK代理设计模式、Javassist

目前字节码修改技术有ASM，javassist等。cglib就是基于封装的Asm. Spring 就是使用cglib代理库。
AspectJ是插件形式，编译期注入代理代码




---------------------------------------------------------------------------------------------------------------------
## 常用 Java Profiling 工具的分析与比较

IDEA 之 JVM Profiler 浅析
[常用 Java Profiling 工具的分析与比较](https://blog.csdn.net/carolzhang8406/article/details/68065231)
[IDEA 之 JVM Profiler 浅析](https://qiyuey.top/2019/02/19/IDEA-%E4%B9%8B-JVM-Profiler-%E6%B5%85%E6%9E%90/)


Java Profiler工具的原理

相对于静态代码分析，Profiling 是通过收集程序运行时的信息来研究程序行为的动态分析方法。其目的在于定位程序需要被优化的部分，从而提高程序的运行速度或是内存使用效率。收集程序运行时信息的方法主要有以下三种：
1、事件方法：对于 Java，可以采用 JVMTI（JVM Tools Interface）API 来捕捉诸如方法调用、类载入、类卸载、进入 / 离开线程等事件，然后基于这些事件进行程序行为的分析。
2、统计抽样方法（sampling）: 该方法每隔一段时间调用系统中断，然后收集当前的调用栈（call stack）信息，记录调用栈中出现的函数及这些函数的调用结构，基于这些信息得到函数的调用关系图及每个函数的 CPU 使用信息。由于调用栈的信息是每隔一段时间来获取的，因此不是非常精确的，但由于该方法对目标程序的干涉比较少，目标程序的运行速度几乎不受影响。
3、植入附加指令方法（BCI）: 该方法在目标程序中插入指令代码，这些指令代码将记录 profiling 所需的信息，包括运行时间、计数器的值等，从而给出一个较为精确的内存使用情况、函数调用关系及函数的 CPU 使用信息。该方法对程序执行速度会有一定的影响，因此给出的程序执行时间有可能不准确。但是该方法在统计程序的运行轨迹方面有一定的优势。



Profiler的常见问题
Profiling 过程中数据的获取方式主要有抽样统计和附加指令的方式，前者的典型应用有商用应用 JProfiler、YourKit，后者有诸如阿里的 Tprofiler 等。这两种方式，均存在若干缺陷：
1、附加指令：会增加了性能开销；同时因为修改了程序代码，导致java编译器的优化行为不确定；同时影响了代码的层次，层次越深自然也影响执行效率；
2、抽样统计：获取系统范围的线程栈，JVM 必须处于 Safepoint 状态，只有当线 程处于 Safepoint 状态的时候，别的线程才能去获取它的线程栈，而这个 Safepoint 是由 JVM 控制的，这对于 Profiler 非常不利，有可能一个很热的代码块，JVM 不会在该代码块中间放置 Safepoint，导致 Profiler 无法获得该线程栈，导致错误的 Profiler 结果。


文章 Evaluating the Accuracy of Java Profilers，列举了xprof，hprof，jprofile 和 yourkit 四种采样器，并通过几个压测场景证明了这几种采样器的结果是相互矛盾的。总结的原因有：
1、附加指令：不同的采样器会注入不同的代码，从而影响程序优化过程，同时也影响了Safepoint 的分布，进一步造成采样差异；
2、抽样统计：采样器采样点不够随机，这几种采样器都只有在 Safepoint 采样。


java profiler工具使用：JProfiler使用和async-profiler使用.md

JProfiler强大的JAVA性能分析工具，可以详细分析线程、包、类、方法、对象占用内存与占用CPU的情况，从分析结果进行性能调优，使用程序达到最优，集成IDEA分析JAVA项目性能情况。


---------------------------------------------------------------------------------------------------------------------
## 内存分析工具


[利用 Java dump 进行 JVM 故障诊断](https://www.ibm.com/developerworks/cn/websphere/library/techarticles/0903_suipf_javadump/index.html)  
[通过分析Heap Dump 来了解 Memory Leak ,Retained Heap,Shallow Heap](https://blog.51cto.com/supercharles888/1347144)  
[记一次 JAVA 的内存泄露分析](https://juejin.im/entry/59ddcff86fb9a0452340da0e)


内存分析工具：

java dump分类
dump获取
dump分析

java dump两种：thread dump文件和heap dump文件
./jmap -dump:format=b,file=heap.hprof 2576
./jstack 2576 > thread.txt


java 获取内存dump 的几种方式
1、获取内存详情：jmap -dump:format=b,file=e.bin pid
这种方式可以用 jvisualvm.exe 进行内存分析，或者采用 Eclipse Memory Analysis Tools (MAT)这个工具

2. 获取内存dump：  jmap -histo:live pid
这种方式会先出发fullgc，所有如果不希望触发fullgc 可以使用jmap -histo pid

3.第三种方式：jdk启动加参数：
-XX:+HeapDumpBeforeFullGC 
-XX:+HeapDumpOnOutOfMemoryError 
-XX:HeapDumpPath=/httx/logs/dump

这种方式会产生dump日志，再通过jvisualvm.exe 或者Eclipse Memory Analysis Tools 工具进行分析


jmap是java自带的工具：
1. 查看整个JVM内存状态 
jmap -heap [pid]

2. 查看JVM堆中对象详细占用情况
jmap -histo [pid]

3. 导出整个JVM 中内存信息，可以利用其它工具打开dump文件分析，例如jdk自带的visualvm工具
jmap -dump:file=文件名.dump [pid]
./jmap -dump:format=b,file=heap.hprof 2576
./jstack 2576 > thread.txt


dump分析
1、jdk自带的：jhat和jvisualvm
jhat <heap-dump-file>，这个时候访问 http://localhost:7000/ 就可以看到结果了。7000是jhat启动的端口号
jvisualvm可以查看到类名、实例数百分比，实力数目，实例占用内存大小

2、如果dump文件比较大，亲测jvisiualvm打开失败，这时推荐选择eclipse的内存分析工具：Eclipse Memory Analyzer(MAT)
查看类名、实例数百分比，实力数目，实例占用内存大小


3、第三方工具：MAT，VIsualVM

4、profiler工具JProfiler和async-profiler也可以分析


Java Dump 中的很多线程处于 state:CW 和 state:B 状态，它们之间有何区别？
答：两者都处于等待状态。不同是：
CW - Condition Wait – 条件等待. 这种等待一般是线程主动等待或者正在进行某种 IO 操作，而并非等待其它线程释放资源。比如 sleep() ，wait()，join() 等方法的调用。
B – Blocked – 线程被阻塞，与条件等待不同，线程被阻塞一般不是线程主动进行的，而是由于当前线程需要的资源正在被其他线程占用，因此不得不等待资源释放以后才能继续执行，例如 synchronized 代码块。



---------------------------------------------------------------------------------------------------------------------
## 远程Debugging工具：IDEA、eclipse


开启远程debug
JAVA_OPT="${JAVA_OPT} -Xdebug -Xrunjdwp:transport=dt_socket,address=9555,server=y,suspend=n"
JDK1.5之后：export JPDA_OPTS="-agentlib:jdwp=transport=dt_socket,address=1043,server=y,suspend=n"
DEBUG="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=2345"

java9以上和java8以下
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5006
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5006


DEBUG选项参数的意思：
-XDebug 启用调试
-Xrunjdwp 加载JDWP的JPDA参考执行实例。
transport  用于在调试程序和 VM 使用的进程之间通讯。
dt_socket 套接字传输。
server=y/n VM是否需要作为调试服务器执行。
address=2345调试服务器监听的端口号。
suspend=y/n 是否在调试客户端建立连接之后启动 VM 。

关于suspend多说一句，如果设置为y，它会阻塞程序运行，直到有客户端连接到对应的监听端口(这里是9527)，程序才真正开始执行。我们有时候会抱怨程序一闪而过，还没来得及在本地加载上代码程序就执行完了，这种情况就可以使用suspend参数。

还有一个细节是-jar参数不能写到-Xug参数前，像这样无法启用调试：
java -jar lib/Main.jar -Xdebug -Xrunjdwp:transport=dt_socket,address=9527,server=y,suspend=y



远程Debugging原理：就是java探针agent：引入jdwp这个Agent 的

远程JVM调试怎么工作的
一切源于被称作 Agents 的东西。
运行着各种编译过的 .class 文件的JVM， 有一种特性，可以允许外部的库（Java或C++写的libraries）在运行时注入到 JVM 中。这些外部的库就称作 Agents, 他们有能力修改运行中 .class 文件的内容。
这些 Agents 拥有的这些 JVM 的功能权限， 是在 JVM 内运行的 Java Code 所无法获取的， 他们能用来做一些有趣的事情，比如修改运行中的源码， 性能分析等。 像 JRebel 工具就是用了这些功能达到魔术般的效果。

传递一个 Agent Lib 给 JVM, 通过添加 agentlib:libname[=options] 格式的启动参数即可办到。像上面的远程调试我们用的就是 **-agentlib:jdwp=... **来引入 jdwp 这个 Agent 的。

jdwp 是一个 JVM 特定的 JDWP（Java Debug Wire Protocol） 可选实现，用来定义调试者与运行JVM之间的通讯，它的是通过 JVM 本地库的 jdwp.so 或者 jdwp.dll 支持实现的。

它到底是怎么工作的呢？
简单来说， jdwp agent 会建立运行应用的 JVM 和调试者（本地或者远程）之间的桥梁。既然他是一个Agent Library, 它就有能力拦截运行的代码。
在 JVM 架构里， debugging 功能在 JVM 本身的内部是找不到的，它是一种抽象到外部工具的方式（也称作调试者 debugger）。这些调试工具或者运行在 JVM 的本地 或者在远程。这是一种解耦，模块化的架构。



---------------------------------------------------------------------------------------------------------------------
https://www.jianshu.com/p/25e94a1399a0
https://www.jianshu.com/p/fe37a62ba155
https://www.jianshu.com/p/978522f88ad0
https://www.ibm.com/developerworks/cn/java/j-mxbeans/index.html

## 远程监控工具：JMX（Java Management Extensions）

远程监控工具：JMX（Java Management Extensions），即JAVA管理扩展，用来监视和管理JVM以及其运行的操作系统。9个MXBean

参考 [JMX介绍](JMX.md)


命令行和界面化：

1:JPS : 列出正在运行的JVM进程
2:TOP : 查看进程资源占用情况
3:JINFO : 查看配置信息

4:JSTAT : 查看各种GC指标,例如查看GC情况 jstat -gcutil pid

5:JMAP ：内存映射工具
6:JHAT : jvm堆快照分析工具
7:JSTACK : java堆栈跟踪工具


界面：
jconsole对内存监控做的更好，可以详细的看到各个区域内存分布和GC情况。
jvisualvm分析功能更强大，能改分析系统运行情况，分析内存泄漏和方法执行。


远程监控工具：JMX（Java Management Extensions），即JAVA管理扩展，用来监视和管理JVM以及其运行的操作系统。9个MXBean

从JAVA 5开始，JDK提供了一些JVM检测的API，这就是有名的java.lang.management 包，包里提供了许多MXBean的接口类，可以很方便的获取到JVM的内存、GC、线程、锁、class、甚至操作系统层面的各种信息

9个MXBean：获取ManagementFactory.getXXX

1、参数信息
OperatingSystemMXBean：系统状态信息
RuntimeMXBean：JVM启动参数

2、类和线程信息
CompilationMXBean：Java虚拟机编译相关，编译器名称等
ClassLoadingMXBean：类加载情况
ThreadMXBean：线程状态
GarbageCollectorMXBeans：GC状态

3、内存信息
MemoryManagerMXBeans：内存管理器的信息
MemoryMXBean：总内存内存使用状态
MemoryPoolMXBeans：各个内存区使用状态


特别的LoggingMXBean，LogManager.getXXX获取


---------------------------------------------------------------------------------------------------------------------
## 理解JVM的safepoint


https://blog.csdn.net/iter_zc/article/details/41847887
https://blog.csdn.net/ITer_ZC/article/details/41892567
https://www.sczyh30.com/posts/Java/jvm-gc-safepoint-condition/
https://www.jianshu.com/p/c79c5e02ebe6


safepoint 安全点顾名思义是指一些特定的位置，当线程运行到这些位置时，线程的一些状态可以被确定(the thread's representation of it's Java machine state is well described)，比如记录OopMap的状态，从而确定GC Root的信息，使JVM可以安全的进行一些操作，比如开始GC。

safepoint指的特定位置主要有:
1. 循环的末尾 (防止大循环的时候一直不进入safepoint，而其他线程在等待它进入safepoint)
2. 方法返回前
3. 调用方法的call之后
4. 抛出异常的位置


之所以选择这些位置作为safepoint的插入点，主要的考虑是“避免程序长时间运行而不进入safepoint”，比如GC的时候必须要等到Java线程都进入到safepoint的时候VMThread才能开始执行GC，如果程序长时间运行而没有进入safepoint，那么GC也无法开始，JVM可能进入到Freezen假死状态。在stackoverflow上有人提到过一个问题，由于BigInteger的pow执行时JVM没有插入safepoint,导致大量运算时线程一直无法进入safepoint，而GC线程也在等待这个Java线程进入safepoint才能开始GC，结果JVM就Freezen了。


JVM在很多场景下使用到safepoint, 最常见的场景就是GC的时候。对一个Java线程来说，它要么处在safepoint,要么不在safepoint。
1. Garbage collection pauses
2. Code deoptimization
3. Flushing code cache
4. Class redefinition (e.g. hot swap or instrumentation)
5. Biased lock revocation
6. Various debug operation (e.g. deadlock check or stacktrace dump)


GC的标记阶段需要stop the world，让所有Java线程挂起，这样JVM才可以安全地来标记对象。safepoint可以用来实现让所有Java线程挂起的需求。这是一种"主动式"(Voluntary Suspension)的实现。JVM有两种执行方式：解释型和编译型(JIT)，JVM要保证这两种执行方式下safepoint都能工作。

在JIT执行方式下，JIT编译的时候直接把safepoint的检查代码加入了生成的本地代码，当JVM需要让Java线程进入safepoint的时候，只需要设置一个标志位，让Java线程运行到safepoint的时候主动检查这个标志位，如果标志被设置，那么线程停顿，如果没有被设置，那么继续执行。

在解释器执行方式下，JVM会设置一个2字节的dispatch tables,解释器执行的时候会经常去检查这个dispatch tables，当有safepoint请求的时候，就会让线程去进行safepoint检查。

聊聊JVM（五）从JVM角度理解线程 说了JVM中的线程类型，其中提到VMThread。VMThread会一直等待直到VMOperationQueue中有操作请求出现，比如GC请求。而VMThread要开始工作必须要等到所有的Java线程进入到safepoint。JVM维护了一个数据结构，记录了所有的线程，所以它可以快速检查所有线程的状态。当有GC请求时，所有进入到safepoint的Java线程会在一个Thread_Lock锁阻塞，直到当JVM操作完成后，VM释放Thread_Lock，阻塞的Java线程才能继续运行。

GC stop the world的时候，所有运行Java code的线程被阻塞，如果运行native code线程不去和Java代码交互，那么这些线程不需要阻塞。VM操作相关的线程也不会被阻塞。


safepoint只能处理正在运行的线程，它们可以主动运行到safepoint。而一些Sleep或者被blocked的线程不能主动运行到safepoint。这些线程也需要在GC的时候被标记检查，JVM引入了safe region的概念。safe region是指一块区域，这块区域中的引用都不会被修改，比如线程被阻塞了，那么它的线程堆栈中的引用是不会被修改的，JVM可以安全地进行标记。线程进入到safe region的时候先标识自己进入了safe region，等它被唤醒准备离开safe region的时候，先检查能否离开，如果GC已经完成，那么可以离开，否则就在safe region呆在。这可以理解，因为如果GC还没完成，那么这些在safe region中的线程也是被stop the world所影响的线程的一部分，如果让他们可以正常执行了，可能会影响标记的结果


可以设置JVM参数 -XX:+PrintSafepointStatistics –XX:PrintSafepointStatisticsCount=1 来输出safepoint的统计信息



---------------------------------------------------------------------------------------------------------------------
