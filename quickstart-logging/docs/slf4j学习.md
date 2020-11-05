1、SLF4J--自动绑定实现类原理
2、MDC记录日志上下文

---------------------------------------------------------------------------------------------------------------------

SLF4J--自动绑定实现类原理

自动扫描项目下的org.slf4j.impl.StaticLoggerBinder，这个类是实现框架中的，然后从这个里面获取实际的logger，然后调用打印


private static final Logger logger = LoggerFactory.getLogger(TestLog4j.class);

在getLogger-----》getILoggerFactory-----》performInitialization-----》bind()-----》findPossibleStaticLoggerBinderPathSet-----》加载项目中的org/slf4j/impl/StaticLoggerBinder.class，如果加载到多个，就报Class path contains multiple SLF4J bindings


参考
https://blog.csdn.net/szzt_lingpeng/article/details/81353639

---------------------------------------------------------------------------------------------------------------------

MDC记录日志上下文

MDC ( Mapped Diagnostic Contexts )，映射的诊断上下文，顾名思义，其目的是为了便于我们诊断线上问题而出现的方法工具类。虽然，Slf4j 是用来适配其他的日志具体实现包的，但是针对 MDC功能，目前只有logback 以及 log4j 支持，或者说由于该功能的重要性，slf4j 专门为logback系列包装接口提供外部调用(玩笑～：）)。

logback 和 log4j 的作者为同一人，所以这里统称logback系列。


参考
https://ketao1989.github.io/2015/04/29/LogBack-Implemention-And-Slf4j-Mdc/
https://www.baeldung.com/mdc-in-log4j-2-logback


参考代码
https://github.com/eugenp/tutorials/tree/master/logging-modules/log-mdc

---------------------------------------------------------------------------------------------------------------------




