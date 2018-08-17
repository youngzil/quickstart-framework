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


