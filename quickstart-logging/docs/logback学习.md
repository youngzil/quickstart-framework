1、logback分为三个模块：logback-core，logback-classic和logback-access。
2、配置文件的基本结构：以<configuration>开头，后面有零个或多个<appender>元素，有零个或多个<logger>元素，有最多一个<root>元素。
3、Logback默认寻找配置的步骤

---------------------------------------------------------------------------------------------------------------------


https://logback.qos.ch/
https://github.com/qos-ch/logback



Logback项目
Logback旨在作为流行的log4j项目的后续版本，在log4j离开的地方启动。

Logback的架构足够通用，以便在不同情况下应用。目前，logback分为三个模块：logback-core，logback-classic和logback-access。

logback-core模块为其他两个模块奠定了基础。logback-classic模块可以被同化为log4j的显着改进版本。此外，logback-classic本身实现了SLF4J API，因此您可以在logback和其他日志框架（如log4j或java.util.logging（JUL））之间来回切换。

logback-access模块​​与Servlet容器（如Tomcat和Jetty）集成，以提供HTTP访问日志功能。请注意，您可以在logback-core之上轻松构建自己的模块。



功能：
Filters（过滤器）有些时候，需要诊断一个问题，需要打出日志。在log4j，只有降低日志级别，不过这样会打出大量的日志，会影响应用性能。在Logback，你可以继续 保持那个日志级别而除掉某种特殊情况，如alice这个用户登录，她的日志将打在DEBUG级别而其他用户可以继续打在WARN级别。要实现这个功能只需加4行XML配置。可以参考MDCFIlter 。



logback的配置介绍

Logger、appender及layout
　　Logger作为日志的记录器，把它关联到应用的对应的context上后，主要用于存放日志对象，也可以定义日志类型、级别。
　　Appender主要用于指定日志输出的目的地，目的地可以是控制台、文件、远程套接字服务器、 MySQL、PostreSQL、 Oracle和其他数据库、 JMS和远程UNIX Syslog守护进程等。 
　　Layout 负责把事件转换成字符串，格式化的日志信息的输出。

Logback 配置文件的语法非常灵活。正因为灵活，所以无法用 DTD 或 XML schema 进行定义。尽管如此，可以这样描述配置文件的基本结构：以<configuration>开头，后面有零个或多个<appender>元素，有零个或多个<logger>元素，有最多一个<root>元素。

Logback默认寻找配置的步骤
1、尝试在 classpath下查找文件logback-test.xml；
2、查找logback.groovy
3、如果文件不存在，则查找文件logback.xml；
4、如果两个文件都不存在，logback用BasicConfigurator自动对自己进行配置，这会导致记录输出到控制台。

logback的默认配置

如果配置文件 logback-test.xml 和 logback.xml 都不存在，那么 logback 默认地会调用BasicConfigurator ，创建一个最小化配置。最小化配置由一个关联到根 logger 的ConsoleAppender 组成。输出用模式为%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n 的 PatternLayoutEncoder 进行格式化。root logger 默认级别是 DEBUG。

子节点<loger>：用来设置某一个包或具体的某一个类的日志打印级别、以及指定<appender>。<loger>仅有一个name属性，一个可选的level和一个可选的addtivity属性。


参考
https://www.cnblogs.com/warking/p/5710303.html



