1、SLF4J--自动绑定实现类原理

---------------------------------------------------------------------------------------------------------------------

SLF4J--自动绑定实现类原理

自动扫描项目下的org.slf4j.impl.StaticLoggerBinder，这个类是实现框架中的，然后从这个里面获取实际的logger，然后调用打印


private static final Logger logger = LoggerFactory.getLogger(TestLog4j.class);

在getLogger-----》getILoggerFactory-----》performInitialization-----》bind()-----》findPossibleStaticLoggerBinderPathSet-----》加载项目中的org/slf4j/impl/StaticLoggerBinder.class，如果加载到多个，就报Class path contains multiple SLF4J bindings


参考
https://blog.csdn.net/szzt_lingpeng/article/details/81353639

---------------------------------------------------------------------------------------------------------------------




---------------------------------------------------------------------------------------------------------------------





---------------------------------------------------------------------------------------------------------------------




