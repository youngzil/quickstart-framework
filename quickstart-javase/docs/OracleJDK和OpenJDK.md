- [Java版本、JSR规范和JCP社区流程概述](#Java版本、JSR规范和JCP社区流程概述)
- [Java的三个分支：JavaSE、JavaEE、JavaME](#Java的三个分支：JavaSE、JavaEE、JavaME)
- [JDK、JRE、JVM区别与联系](#JDK、JRE、JVM区别与联系)
- [OpenJDK与OracleJDK的区别](#OpenJDK与OracleJDK的区别)


---------------------------------------------------------------------------------------------------------------------
## Java版本、JSR规范和JCP社区流程概述

JCP（Java Community Process Program）是管理java技术的官方组织，JCP制定的技术规范称为JSR规范。
JSR（Java Specification Requests）：Java规范请求可以增强任何Java技术或引入新技术。JSR规范是java技术的标准，也就是说JSR规范规定了java体系有哪些技术
RI（Reference Implementation）
TCK（Technology Compatibility Kit）：技术兼容性工具包 （TCK）作為技術相容測試工具箱 - 一组测试，用于检查特定 JSR 的合规性
JCK(Java相容性测试)



JSR规范总共416条，分为6种状态：
- Final（最终）、Maintenance（维护）、Withdrawn（撤回） 、Rejected（拒绝）、Dormant（休眠）、Active（活动中）
- 生效中的是“final”、“Maintenance”和“Active”状态的规范。其中JavaSE规范65个，包含撤回的规范7个，JavaEE规范54个，包含撤回的3个规范。

JSR规范官网链接：[Java Community Process Program](https://www.jcp.org/en/jsr/all)



Java由Sun創造，為了讓對Java興趣的廠商、組織、開發者與使用者參與定義Java未來的功能與特性，Sun公司於1998年組成了JCP（Java Community Process），這是一個開放性國際組織，目的是讓Java演進由Sun非正式地主導，成為全世界數以百計代表成員公開監督的過程。

任何想要提議加入Java的功能或特性，必須以JSR（Java Specification Requests）正式文件的方式提交，JSR必須經過JCP執行委員會（Executive Committee）投票通過，方可成為最終標準文件，有興趣的廠商或組織可以根據JSR實現產品。

若JSR成為最終文件後，必須根據JSR實作出免費且開發原始碼的參考實現，稱為RI（Reference Implementation），並提供TCK（Technology Compatibility Kit）作為技術相容測試工具箱，方便其它想根據JSR實現產品的廠商或組織參考與測試相容性。




[JDK 規範與實作](https://openhome.cc/Gossip/Java/JDKSpecsImpl.html)  
[JCK和Java相容性](https://www.itread01.com/p/589645.html)  
[JSR规范系列（1）——Java版本、JSR规范和JCP社区流程概述](https://blog.csdn.net/ni_hao_fan/article/details/100011142)  
[]()  
[]()

---------------------------------------------------------------------------------------------------------------------
## Java的三个分支：JavaSE、JavaEE、JavaME

Java分为三个不同版本：
- Java标准版（Java SE）；
- Java企业版（Java EE）；
- Java Micro Edition（Java ME）。


Java SE(Java Platform,Standard Edition)，应该先说这个，因为这个是标准版本。
Java EE (Java Platform，Enterprise Edition)，java 的企业版本
Java ME(Java Platform，Micro Edition)，java的微型版本。


Java分为三个版本：Java SE(标准版)、Java EE(企业版)、Java ME(微型版)。其中JavaSE就是大家学JavaEE和JavaME的基础，换而言之学Java先从JavaSE开始，JavaSE 包含了支持 Java Web 服务开发的类，JavaEE是企业最常用的用于企业级开发应用的，Java ME主要是移动段的开发应用。


甲骨文正式宣布将 Java EE 移交给 Eclipse 基金会
Java EE 已经正式更名为 Jakarta EE（雅加达）

Jakarta EE 规范将由 Jakarta EE 工作组定义并由规范委员会批准。 JCP 将仅负责 Java SE 和 Java ME 规范。Eclipse Enterprise for Java（EE4J）顶级项目将发布 Eclipse Glassfish 作为 Java EE 8 兼容实现。



提及JavaEE，很多人都知道它是社区驱动的企业软件标准。JavaEE是利用Java Community Process开发的，每个版本都集成了符合业界需求的新特性，提供了一个丰富的企业软件平台，提高了应用可移植性，提高了开发人员的工作效率。
据Oracle 的 JavaEE 布道师 David Delabassee 透露，Oracle之所以要开源 Java EE，主要是想让它变得更敏捷，以适应快速发展的行业和技术需求。而实际上，尽管 JCP(Java Community Process)也在这方面做出了一些努力，但其随后推出的新兴技术，如NoSQL、容器、微服务和无服务器架构等都未包含在JavaEE中。



JavaEE
https://github.com/javaee/



https://blog.csdn.net/cowcomic/article/details/82922933
https://www.zhihu.com/question/19646618



### Java标准版（Java SE）

在 Java标准版 （Java SE的）是运行Java应用程序的最低要求。此版本为Java Enterprise Edition提供了坚实的基础 ，因此我将首先定义它的一些组件：
- Java虚拟机 （JVM）
- Java类库 （JCL）
- Java运行时环境 （JRE）
- Java开发工具包 （JDK）



### Java企业版（Java EE）

在 Java企业版 （Java EE的）创建扩展 的Java SE 通过添加一组定义企业应用程序常用的功能规格。这一版的最新版本 包含超过40个规格 ，可帮助开发人员创建应用程序 通过Web服务进行通信， 面向对象的数据转换为实体关系模型， 处理事务的谈话，以及 等等。

以下列表列举了Java EE的一些最重要和最常用的功能 ：
- Java Persistence API （JPA）：用于访问，持久化和管理Java对象与关系数据库之间的数据的规范
- JavaServer Faces （JSF）：用于为Web应用程序构建基于组件的用户界面的规范
- JavaServer Pages （JSP）：一种帮助软件开发人员基于HTML创建动态生成的Web页面的技术
- 用于RESTful Web服务的Java API （JAX-RS）：一种在创建RESTful Web服务时提供支持的规范
- Enterprise Java Beans （EJB）：用于开发封装应用程序业务逻辑的组件的规范
- 上下文和依赖注入 （CDI）：一种允许开发人员 在Java应用程序上应用控制反转的技术


#### Java企业版供应商

在撰写本文时，有 8个不同的供应商认证了他们的Java EE实现。在这些供应商中，其中两个是免费和开源的： GlassFish Server开源版 和 WildFly。



---------------------------------------------------------------------------------------------------------------------
## JDK、JRE、JVM区别与联系

JRE： Java Runtime Environment
JDK：Java Development Kit


JDK是 Java 语言的软件开发工具包(SDK)。在JDK的安装目录下有一个jre目录，里面有两个文件夹bin和lib，在这里可以认为bin里的就是jvm，lib中则是jvm工作所需要的类库，而jvm和 lib合起来就称为jre。

JDK包含JRE，而JRE包 含JVM。



### JDK介绍

JDK(Java Development Kit) 是整个JAVA的核心，包括了Java运行环境（Java Runtime Envirnment），一堆Java工具（javac/java/jdb等）和Java基础的类库（即Java API 包括rt.jar）。

JDK是java开发工具包，基本上每个学java的人都会先在机器 上装一个JDK，那他都包含哪几部分呢？在目录下面有 六个文件夹、一个src类库源码压缩包、和其他几个声明文件。其中，真正在运行java时起作用的 是以下四个文件夹：bin、include、lib、 jre。



### JRE介绍

JRE（Java Runtime Environment，Java运行环境），包含JVM标准实现及Java核心类库。JRE是Java运行环境，并不是一个开发环境，所以没有包含任何开发工具（如编译器和调试器）

JRE是指java运行环境。光有JVM还不能成class的 执行，因为在解释class的时候JVM需要调用解释所需要的类库lib。 （jre里有运行.class的java.exe）

JRE （ Java Runtime Environment ），是运行 Java 程序必不可少的（除非用其他一些编译环境编译成.exe可执行文件……），JRE的 地位就象一台PC机一样，我们写好的Win64应用程序需要操作系统帮 我们运行，同样的，我们编写的Java程序也必须要JRE才能运行。



### JVM

JVM（Java Virtual Machine），即java虚拟机, java运行时的环境，JVM是一种用于计算设备的规范，它是一个虚构出来的计算机，是通过在实际的计算机上仿真模拟各种计算机功能来实现的。针对java用户，也就是拥有可运行的.class文件包（jar或者war）的用户。里面主要包含了jvm和java运行时基本类库（rt.jar）。rt.jar可以简单粗暴地理解为：它就是java源码编译成的jar包。Java虚拟机在执行字节码时，把字节码解释成具体平台上的机器指令执行。这就是Java的能够“一次编译，到处运行”的原因。



[浅谈JDK、JRE、JVM区别与联系](https://blog.csdn.net/ancientear/article/details/79483592)
[JRE 和 JDK 的区别详解（附JVM简单说明）](https://blog.csdn.net/kingscoming/article/details/78860702)


### JDK种类

好像只有OpenJDK与OracleJDK，其他的都不算是，比如adoptopenjdk

[除了Oracle JDK，我们还有哪些选择？](https://segmentfault.com/a/1190000016526240)



### JVM的分类

SUN公司有对JVM的规范，只要符合JVM规范大家都可以在此基础上开发出自己的虚拟机。事实上，我们现在最常用的HotSpot VM就不是SUN公司开发的。HotSpot VM是对JVM相关JSR(Java Specification Requests，Java规范)的一个RI（Reference Implementation，参考实现）。


Oracle / Sun JDK、OpenJDK的各种变种（例如IcedTea、Zulu），用的都是相同核心的HotSpot VM。


主要有：
- HotSpot VM
- J9 VM
- Zing VM

其他的：
3、Sun Classic VM.
4、Exact VM.
5、JRockit VM.

其他的VM还有很多很多，比如Dalvik VM、Microsoft JVM、Azul VM、Liquid VM、Zing VM等等。



[九神带你入门JVM（上）](https://segmentfault.com/a/1190000038260872)  
[目前主流的 Java 虚拟机有哪些?](https://www.zhihu.com/question/29265430)  



---------------------------------------------------------------------------------------------------------------------

## OpenJDK与OracleJDK的区别

首先要明白JDK的发布模型。一年多以前，伴随着JDK9的发布，JDK就开启了新的发布模式（如下图所示）。JDK分为OracleJDK、OpenJDK。
OpenJDK是Sun在2006年末把Java开源而形成的项目，这里的“开源”是通常意义上的源码开放形式，即源码是可被复用的

OpenJDK
OpenJDK的lience是GPLv2+CPE，可以免费使用。从JDK9开始，OpenJDK每6个月发布一次，也就是每年的3月份、9月份各发布一次，称为feature release。JDK9就是在2017年9月份过GA的。

除了feature release之外，OpenJDK每个季度会提供一个update release。分别在1月份、4月份、7月份和10月份。所以每个feature release之后，都会有两个update release。


OracleJDK
使用OracleJDK需要commercial license，这个不是免费的。OracleJDK每3年发布一次LTS（Long Term Support）版本，Support的期限是8年。2018年9月发布的JDK11是第一个LTS版本，support到2026年9月。同样，OracleJDK每年也有4个update release。

因为OracleJDK每3年发布一次LTS版本，所以下一次LTS将在2021年9月份发布。


OpenJDK与OracleJDK的区别
之前有一些commercial features没有包含在OpenJDK中，例如：
Java Flight Recorder:  http://openjdk.java.net/jeps/328
Java Mission Control:  http://openjdk.java.net/projects/jmc/
Application Class-Data Sharing:  http://openjdk.java.net/jeps/310
ZGC:  http://openjdk.java.net/jeps/333

但是现在这些commercial features已经贡献给了OpenJDK社区，所以从JDK11开始，OpenJDK与OracleJDK基本完全一样。但是它们之间仍然有一些差异，具体查看下面的文章：
https://blogs.oracle.com/java-platform-group/oracle-jdk-releases-for-java-11-and-later


区别与联系
- 授权协议的不同
- OpenJDK源代码不完整
- 部分源代码用开源代码替换
- openjdk只包含最精简的JDK
- 不能使用Java商标



参考的资料
OpenJDK和JDK区别https://fgh2011.iteye.com/blog/1771649
OpenJDK和Sun/OracleJDK 区别 与联系http://www.cnblogs.com/zengkefu/p/5633342.html
OpenJDK和SunJDK有啥区别？https://www.zhihu.com/question/19646618


JDK11收费吗？
OpenJDK是免费的。对于想要不断体验新特性的developer来说，是理想的选择。
OracleJDK不是免费的。对于企业用户来说，可能不是太愿意频繁升级，那么就选择OracleJDK。

References
https://www.oracle.com/technetwork/java/javase/eol-135779.html
https://www.oracle.com/technetwork/java/javase/downloads/index.html
https://blogs.oracle.com/java-platform-group/oracle-jdk-releases-for-java-11-and-later
https://www.infoq.com/news/2017/09/Java6Month



java8
Oracle jdk：授权使用BCL协议
Open jdk：授权使用GPL协议或BCL协议，如果选择BCL协议就跟Oracle版没什么区别了，但不需要收费

java11
Oracle jdk：授权使用BCL协议
Open jdk：授权使用GPL协议



BCL协议
你不能改JDK，你基于JDK做出的内容是受保护的，同时根据你是个人版还是商业版来决定能否用来商用
GPL协议
开源，JDK随便改，也能随便用，但是如果用基于这个协议的JDK开发出来的内容，也必须是GPL协议的，也就是开源的



获取JDK源码
首先确定要使用的JDK版本，OpenJDK 6和OpenJDK 7都是开源的，源码都可以在它们的主页（http://openjdk.java.net/）上找到，OpenJDK 6的源码其实是从OpenJDK 7的某个基线中引出的，然后剥离掉JDK 1.7相关的代码，从而得到一份可以通过TCK 6的JDK 1.6实现，因此直接编译OpenJDK 7会更加“原汁原味”一些，其实这两个版本的编译过程差异并不大。

获取源码有两种方式：一种是通过Mercurial代码版本管理工具从Repository中直接取得源码（Repository地址：http://hg.openjdk.java.net/jdk7/jdk7），这是最直接的方式，从版本管理中看变更轨迹比看任何Release Note都来得实在，不过坏处自然是太麻烦了一些，尤其是Mercurial远不如SVN、ClearCase或CVS之类的版本控制工具那样普及；

另外一种就是直接下载官方打包好的源码包了，可以从Source Releases页面（地址：http://download.java.net/openjdk/jdk7/）取得打包好的源码，一般来说大概一个月左右会更新一次，虽然不够及时，但的确方便了许多。笔者下载的是OpenJDK 7 Early Access Source Build b121版，2010年12月9日发布的，大概81.7MB，解压后约308MB。

https://my.oschina.net/u/2518341/blog/1931088



---------------------------------------------------------------------------------------------------------------------


