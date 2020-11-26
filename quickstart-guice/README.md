Guice

[Guice Github](https://github.com/google/guice)  
[Guice文档](https://github.com/google/guice/wiki)  


Guice (pronounced 'juice') is a lightweight dependency injection framework for Java 6 and above, brought to you by Google. 

Guice（发音为“ juice”）是Google提供给您的Java 6及更高版本的轻量级依赖注入框架。


术语
- Guice：整个框架的门面
- Injector：一个依赖的管理上下文
- Binder：一个接口和实现的绑定
- Module：一组Binder
- Provider：bean的提供者
- Key：Binder中对应一个Provider
- Scope：Provider的作用域
- Stage：运行方式（为了不同的要求）



Guice和它的扩展提供了很多作用域，有单例Singleton，Session作用域SessionScoped，Request请求作用域RequestScoped等等。  
我们可以根据需要选择合适的作用域。 

如果一个类型上存在多个冲突的作用域，Guice会使用bind()方法中指定的作用域。如果不想使用注解的作用域，可以在bind()方法中将对象绑定为Scopes.NO_SCOPE。
 
使用：
- 1、可以在实现类上应用@Singleton注解。
- 2、在配置类中指定。bind(TransactionLog.class).to(InMemoryTransactionLog.class).in(Singleton.class);
- 3、在@Provides方法中也可以指定单例。
  @Provides @Singleton
  @Provides @Singleton
  TransactionLog provideTransactionLog() {
    ...
  }



依赖绑定方式：
- 1、简单绑定，链式绑定（绑定后然后可以通过@Inject注解依赖绑定）
- 2、注解绑定@Inject（构造器，有@Named过滤 或 自定义注解过滤）
- 3、@Provides绑定【复杂的对象构建】



作用域：  
默认情况下Guice会在每次注入的时候创建一个新对象。  
如果希望创建一个单例依赖的话，  
- 1、可以在实现类上应用@Singleton注解。
- 2、或者也可以在配置类中指定。
- 3、在@Provides方法中也可以指定单例。



参考  
https://cloud.tencent.com/developer/article/1605665
https://www.jianshu.com/p/a648322dc680
https://blog.csdn.net/u011054333/article/details/57179999
https://juejin.im/post/5a375e156fb9a0452a3c6b96
[深入剖析Guice（Google依赖注入框架）](https://blog.csdn.net/zhaowen25/article/details/52927193)  
[Google Guice 一个轻量级的依赖注入框架](https://www.jianshu.com/p/7fba7b43146a)  
[Guice 的工作原理](https://www.codenong.com/cs105864553/)  



JSR-330标准：javax.inject-1.jar
JSR-330是一项Java EE标准，指定了Java的依赖注入标准。Spring、Guice和Weld等很多框架都支持JSR-330。下面这个表格来自于Guice文档，我们可以看到JSR-330和Guice注解基本上可以互换。
JSR-330 javax.inject	Guice com.google.inject	
@Inject	@Inject	在某些约束下可互换
@Named	@Named	可互换
@Qualifier	@BindingAnnotation	可互换
@Scope	@ScopeAnnotation	可互换
@Singleton	@Singleton	可互换
Provider	Provider	Guice的Provider继承了JSR-330的Provider

[@Inject和@Autowired以及@Resource区别](https://blog.csdn.net/u012734441/article/details/51706504)  
- 1、@Inject，这是jsr330中的规范，通过‘AutowiredAnnotationBeanPostProcessor’ 类实现的依赖注入。
- 2、@Autowired：是Spring提供的注解，通过‘AutowiredAnnotationBeanPostProcessor’ 类实现的依赖注入，与@inject二者具有可互换性。
- 3、@Resource：这是JSR250 (Common Annotations for Java) 规范的实现，‘@Resource’通过 ‘CommonAnnotationBeanPostProcessor’ 类实现依赖注入。


三个注解的相异之处
@Autowired和@Inject基本是一样的，因为两者都是使用AutowiredAnnotationBeanPostProcessor来处理依赖注入。但是@Resource是个例外，它使用的是CommonAnnotationBeanPostProcessor来处理依赖注入。当然，两者都是BeanPostProcessor。


总结
个人在使用上，更偏重使用@Inject，这是jsr330规范的实现，而@Autowired是spring的实现，如果不用spring一般用不上这个，而@Resource则是jsr250的实现，这是多年前的规范。




AOP Alliance (Java/J2EE AOP standards)
AOP联盟项目是几个对AOP和Java感兴趣的软件工程人员之间的联合开源项目。
Aop Alliance项目是许多对Aop和java有浓厚兴趣的软件开发人员联合成立的开源项目，其提供的源码都是完全免费的(Public Domain).

[AOP Alliance官方网站](http://aopalliance.sourceforge.net/)

参考  
[aopalliance解析(一) 概述](https://blog.csdn.net/zhang_shufeng/article/details/38052007)




JSR-305：供检查软件缺陷用的注解：jsr305-1.3.9.jar

诸如FindBugs、IntelliJ、Checkstyle和PMD这样的静态分析工具在Java开发中得到了广泛应用。这些工具都很强大，但是有一些共同的问题它们都很难解决。在API的设计中，有一些决策是不言而喻的，比如何时值可以为null，或者何时数字值不能为负。完备的API会将这些设计细节记录在JavaDoc之中，但是分析工具却无法发现类似细节，从而有可能将其忽略或是导致错误的检测结果。

为了解决这些问题，有些静态分析工具开发人员试图使用注解来定义相关细节。比如FindBugs和IntelliJ都定义了自己的注解，以表示方法何时返回null。不过，这两个工具使用的注解有细微不同，也因此有了标准化的需求。由FindBugs创建人Bill Pugh带领制定的JSR-305标准，试图创建一套标准注解供分析工具使用，同时希望允许开发人员根据自己的需要添加额外的注解。当前提案中包括供判断是否为空、正负号、开发语言和线程等方面的众多注解。


<dependency>
    <groupId>com.google.code.findbugs</groupId>
    <artifactId>annotations</artifactId>
    <version>3.0.1</version>
</dependency>



参考  
[JSR-305：供检查软件缺陷用的注解](http://www.infoq.com/cn/news/2008/07/jsr-305-update)











[Guice Aop 与 Hasor Aop 原理及其实现](https://my.oschina.net/ta8210/blog/178369)  


