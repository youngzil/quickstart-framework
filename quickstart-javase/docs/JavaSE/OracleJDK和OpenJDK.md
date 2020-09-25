1、OpenJDK与OracleJDK的区别
2、Java的三个分支：JavaSE、JavaEE、JavaME



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
  授权协议的不同
  OpenJDK源代码不完整 
    部分源代码用开源代码替换
    openjdk只包含最精简的JDK
    不能使用Java商标
    
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
       获取源码有两种方式：一种是通过Mercurial代码版本管理工具从Repository中直接取得源码（Repository地址：http://hg.openjdk.java.net/jdk7/jdk7），这是最直接的方式，从版本管理中看变更轨迹比看任何Release Note都来得实在，不过坏处自然是太麻烦了一些，尤其是Mercurial远不如SVN、ClearCase或CVS之类的版本控制工具那样普及；另外一种就是直接下载官方打包好的源码包了，可以从Source Releases页面（地址：http://download.java.net/openjdk/jdk7/）取得打包好的源码，一般来说大概一个月左右会更新一次，虽然不够及时，但的确方便了许多。笔者下载的是OpenJDK 7 Early Access Source Build b121版，2010年12月9日发布的，大概81.7MB，解压后约308MB。

https://my.oschina.net/u/2518341/blog/1931088





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














