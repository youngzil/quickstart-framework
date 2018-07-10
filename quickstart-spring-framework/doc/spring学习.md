

===============================================================================================================
https://blog.csdn.net/qq924862077/article/category/6754158


1、Spring Bean对象
2、Bean对象循环依赖问题处理
3、Spring Bean的生命周期和常用接口类
4、spring中有两种类型的Bean，FactoryBean实现原理：
5、BeanFactory和FactoryBean
6、Spring配置解析文件ApplicationContext.xml
7、自定义命名空间处理器NamespaceHandler和Spring自定义XML标签



BeanDefinitionHolder：
BeanDefinitionHolder，简单来说其就是一个BeanDefinition的持有者，其定义了一下变量，并对以下变量提供get和set操作
private final BeanDefinition beanDefinition;  
private final String beanName;  
private final String[] aliases; 
BeanDefinitionHolder实现BeanMetadataElement接口
public class BeanDefinitionHolder implements BeanMetadataElement {}

BeanDefinition：
简单来说BeanDefinition接口及其实现类就是bean的所有配置信息的一个数据集合，从类名也可以看出其就是一个bean的定义说明。
BeanDefinition的3个实现类：GenericBeanDefinition、RootBeanDefinition / ChildBeanDefinition
通常来说，使用GenericBeanDefinition类为了注册一个用户可见的bean定义(后置处理器可能会操作它， 甚至可能重新配置父母的名字)。如果父/子关系是预设的建议使用RootBeanDefinition / ChildBeanDefinition。



在spring2.0之前bean只有2种作用域即：singleton(单例)、non-singleton（也称prototype）, Spring2.0以后，增加了session、request、global session三种专用于Web应用程序上下文的Bean

singleton配置实例：
<bean id="role" class="spring.chapter2.maryGame.Role" scope="singleton"/>
或者
<bean id="role" class="spring.chapter2.maryGame.Role" singleton="true"/>

prototype配置实例：
<bean id="role" class="spring.chapter2.maryGame.Role" scope="prototype"/>
或者
<beanid="role" class="spring.chapter2.maryGame.Role" singleton="false"/>

Bean对象循环依赖问题处理：
1、Spring不支持原型bean的循环依赖。对于原型bean的初始化过程中不论是通过构造器参数循环依赖还是通过setXxx方法产生循环依赖，Spring都会直接报错处理BeanCurrentlyInCreationException
2、单例bean 构造器参数循环依赖：Spring在创建构造器循环依赖时其实就是循环初始化操作 A-> B -> A  当A要被初始化第二次时就直接抛出异常BeanCurrentlyInCreationException
3、单例bean通过setXxx或者@Autowired进行循环依赖：简单来说就是对象通过构造函数初始化之后就暴露到容器中，这样就不会存在循环初始化对象的情况了
Spring通过setXxx或者@Autowired方法解决循环依赖其实是通过提前暴露一个ObjectFactory对象来完成的，简单来说ClassA在调用构造器完成对象初始化之后，在调用ClassA的setClassB方法之前就把ClassA实例化的对象通过ObjectFactory提前暴露到Spring容器中。



Spring Bean的生命周期：https://blog.csdn.net/qq924862077/article/details/75043985
Bean的完整生命周期经历了各种方法调用，这些方法可以划分为以下几类：
1、Bean自身的方法　　：　　这个包括了Bean本身调用的方法和通过配置文件中<bean>的init-method和destroy-method指定的方法
2、Bean级生命周期接口方法　　：　　这个包括了BeanNameAware、BeanFactoryAware、InitializingBean和DiposableBean这些接口的方法
3、容器级生命周期接口方法　　：　　这个包括了InstantiationAwareBeanPostProcessor 和 BeanPostProcessor 这两个接口实现，一般称它们的实现类为“后处理器”。还有MergedBeanDefinitionPostProcessor（extends BeanPostProcessor）、BeanFactoryPostProcessor
4、工厂后处理器接口方法　　：　　这个包括了AspectJWeavingEnabler, ConfigurationClassPostProcessor, CustomAutowireConfigurer等等非常有用的工厂后处理器　　接口的方法。工厂后处理器也是容器级的。在应用上下文装配配置文件之后立即调用。



BeanFactoryPostProcessor--提供了对BeanFactory进行操作的处理
BeanPostProcessor--在bean初始化前后做一些额外的操作

起始执行点在AbstractApplicationContext中，invokeBeanFactoryPostProcessors(beanFactory)执行是在初始化Bean实体方法finishBeanFactoryInitialization(beanFactory)之前，这样就可以在初始化bean之前可以对一些bean做一些额外的处理操作。

BeanPostProcessor接口的作用是在Spring容器完成Bean实例化前后可以添加一些自己的逻辑处理，我们可以定义一个或者多个BeanPostProcessor接口的实现。
BeanPostProcessor接口提供了两个方法：
1、postProcessBeforeInitialization  可以对Bean在实例化之前添加一些逻辑处理
2、postProcessAfterInitialization  可以对bean在实例化之后添加一些逻辑处理



spring中有两种类型的Bean，FactoryBean实现原理：
FactoryBean方法：getObject、getObjectType、isSingleton
spring中有两种类型的Bean：一种是普通的JavaBean；另一种就是工厂Bean（FactoryBean），这两种Bean都受Spring的IoC容器管理，但它们之间却有一些区别。
普通的JavaBean不再多说，我们将其定义好，然后在配置文件中定义并配置其依赖关系，就可以通过IoC容器的getBean获取到。
FactoryBean跟普通Bean不同，它是实现了FactoryBean<T>接口的Bean，通过BeanFactory类的getBean方法直接获取到的并不是该FactoryBean的实例，而是该FactoryBean中方法getObject返回的对象。但我们可以通过其它途径获取到该FactoryBean的实例，方法就是在通过getBean方法获取实例时在参数name前面加上“&”符号即可。
Spring对FactoryBean的实现机制是当你获取一个Bean时，如果获取的Bean的类型是FactoryBean，并且其name中并没有&则调用bean的getObject方法获取FactoryBean实现类中提供bean，否则就是直接返回普通的bean类型。



BeanFactory及其子类是Spring IOC容器中最重要的一个类，BeanFactory由类名可以看出其是一个Bean工厂类，其实它确实是一个Bean工厂类，完成Bean的初始化操作。Bean的初始化操作还是一个比较麻烦的操作，首先根据spring注入配置将bean初始化为单例或者原型，其次需要根据Bean的属性配置来完成Bean参数的注入配置，还有要解决单例模式下Bean的循环依赖的问题，原型模式下bean的循环依赖会直接报错。

BeanFactory 是Spring bean容器的根接口.提供获取bean,是否包含bean,是否单例与原型,获取bean类型,bean 别名的api.
BeanFactory的方法： getBean、containsBean、isSingleton、isPrototype、getType、getAliases、
-- AutowireCapableBeanFactory 添加集成其他框架功能.如果集成WebWork则可以使用Spring对Actions等进行管理.
-- HierarchicalBeanFactory 提供父容器的访问功能
--ConfigurableBeanFactory 如名,提供factory的配置功能,眼花缭乱好多api
-- ConfigurableListableBeanFactory 集大成者,提供解析,修改bean定义,并与初始化单例.
-- ListableBeanFactory 提供容器内bean实例的枚举功能.这边不会考虑父容器内的实例.

简单来说Spring对bean的初始化操作就是根据反射机制通过构造函数进行初始化的。
简单来说Spring对属性值的注入方法还是比较简单的，通过Java提供的反射机制，对于有setXxx方法的变量直接通过反射调用方法注入参数值；对于没有setXxx方法的变量则通过Field方法，直接对变量进行赋值操作。



BeanFactory的方法： getBean、containsBean、isSingleton、isPrototype、getType、getAliases、
FactoryBean方法：getObject、getObjectType、isSingleton

BeanFactory，以Factory结尾，表示它是一个工厂类(接口)，用于管理Bean的一个工厂。在Spring中，BeanFactory是IOC容器的核心接口，它的职责包括：实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。
Spring为我们提供了许多易用的BeanFactory实现，XmlBeanFactory就是常用的一个，该实现将以XML方式描述组成应用的对象及对象间的依赖关系。XmlBeanFactory类将持有此XML配置元数据，并用它来构建一个完全可配置的系统或应用。

FactoryBean以Bean结尾，表示它是一个Bean，不同于普通Bean的是：它是实现了FactoryBean<T>接口的Bean，根据该Bean的ID从BeanFactory中获取的实际上是FactoryBean的getObject()返回的对象，而不是FactoryBean本身，如果要获取FactoryBean对象，请在id前面加一个&符号来获取。
例如自己实现一个FactoryBean，功能：用来代理一个对象，对该对象的所有方法做一个拦截，在调用前后都输出一行LOG，模仿ProxyFactoryBean的功能。



Spring配置解析文件ApplicationContext.xml：
ApplicationContext（实现类ClassPathXmlApplicationContext和FileSystemXmlApplicationContext等）
ApplicationContext--》BeanFactory--》XmlBeanDefinitionReader（解析成Document对象）--》BeanDefinitionDocumentReader（转为输入流）--》BeanDefinitionParserDelegate（解析生成BeanDefinitionHolder，其实bean元素的解析的结果是一个BeanDefinition对象，结果是将BeanDefinition注册到BeanFactory中）
1、简单来说在ApplicationContext中所做的操作是初始化了一个BeanFactory和XmlBeanDefinitionReader，
2、其中XmlBeanDefinitionReader是用来解析Spring的xml配置文件的，BeanDefinitionReader所做的处理操作是将配置的ApplicationContext.xml解析成为Document对象，
3、接下来会有BeanDefinitionDocumentReader来对Document进行解析，BeanDefinitionDocumentReader中也没有对xml中的bean元素进行处理操作，其真正的处理操作是在BeanDefinitionParserDelegate中进行处理的，
4、对bean标签解析，最终还是在BeanDefinitionParserDelegate中对元素进行处理解析生成BeanDefinitionHolder，其实bean元素的解析的结果是一个BeanDefinition对象，其包含了所有的bean的属性设置，processBeanDefinition中的BeanDefinitionReaderUtils的处理结果是将BeanDefinition注册到BeanFactory中。
5、首先BeanDefinitionParserDelegate中定义了其会解析xml的spring元素标签，并且还有说明一点的是Spring解析xml的标签是通过命名空间Namespace来决定的，BeanDefinitionParserDelegate中定义了如下命名空间，只会支持解析这个命名空间中的标签元素。
public static final String BEANS_NAMESPACE_URI = "http://www.springframework.org/schema/beans";

Spring配置文件的解析处理操作是在BeanDefinitionParserDelegate中完成，当然也是只是完成了对Beans这个命名空间中的元素的解析处理操作，对于其他的命名空间是通过其他解析器进行解析操作的，
BeanDefinitionParserDelegate做的处理操作就是将bean的各种标签解析成BeanDefinition对象，并组装成BeanDefinitionHolder返回。



自定义命名空间处理器NamespaceHandler和Spring自定义XML标签

===============================================================================================================


java和spring中的上下文Context
参考
http://blog.csdn.net/u013147600/article/details/49616427

在java web中主要的就是ServletContext
一个 WEB 运用程序只有一个 ServletContext 实例, 它是在容器(包括 JBoss, Tomcat 等)完全启动 WEB 项目之前被创建, 生命周期伴随整个 WEB 运用。
 当在编写一个 Servlet 类的时候, 首先是要去继承一个抽象类 HttpServlet, 然后可以直接通过 getServletContext() 方法来获得 ServletContext 对象。
 这是因为 HttpServlet 类中实现了 ServletConfig 接口, 而 ServletConfig 接口中维护了一个 ServletContext 的对象的引用。
 利用 ServletContext 能够获得 WEB 运用的配置信息, 实现在多个 Servlet 之间共享数据等。
 
 
 Spring中主要是ApplicationContext，是BeanFactory的子接口
 
 
 Spring提供的常用的接口和类，在Context的各个阶段进行扩展，都是在Context初始化的时候，在构建beanFactory的时候注入的prepareBeanFactory(beanFactory);，在内部定义了很多Map来保存这些bean。
 
 InstantiationAwareBeanPostProcessor：作用在bean的实例化前后
 BeanFactoryPostProcessor：在Bean创建之前，读取Bean的元属性，并根据自己的需求对元属性进行改变，比如将Bean的scope从singleton改变为prototype
 BeanPostProcessor：作用在bean的初始化前后，通过实现BeanPostProcessor接口允许用户对新建的Bean进行修改。
InitializingBean：通过实现InitializingBean接口可以在BeanFactory 设置所有的属性后作出进一步的反应可以实现该接口
 DisposableBean：提供了一个唯一的方法destory()，在Bean生命周期结束前调用destory()方法做一些收尾工作
 
 BeanNameAware：在Bean加载的过程中可以获取到该Bean的id
BeanFactoryAware：在Bean加载的过程中可以获取到加载该Bean的BeanFactory
 ApplicationContextAware接口的Bean，在Bean加载的过程中可以获取到Spring的ApplicationContext
 BeanClassLoaderAware：获取Bean的类装载器
 
 FactoryBean：它是实现了FactoryBean<T>接口的Bean，根据该Bean的ID从BeanFactory中获取对象，
 
 BeanDefinitionParser接口：配置文件解析，唯一方法parse()
NamespaceHandlerSupport类：配置解析注入，一般重写init()方法

PropertyPlaceholderConfigurer类：用于读取Java属性文件中的属性，然后插入到BeanFactory的定义中
PropertyOverrideConfigurer类：用于读取Java属性文件中的属性，并覆盖XML配置文件中的定义，即PropertyOverrideConfigurer允许XML配置文件中有默认的配置信息
 CustomEditorConfigurer类：可以读取实现java.beans.PropertyEditor接口的类，将字符串转为指定的类型。
 ResourceBundleMessageSource类：提供国际化支持，bean的名字必须为messageSource。此处，必须存在一个名为jdbc的属性文件。
 
 
 
 Spring本质上来说，就是首先解析配置文件或者注解等方式的bean定义，然后实例化，放在beanFactory中，上下文对象ApplicationContext就是BeanFactory接口的子接口，BeanFactory是顶级接口。
 registry阶段可以理解为实例化阶段，构造函数调用构造对象，如AnnotationConfigApplicationContext的this()和register(annotatedClasses)方法
 refresh()阶段可以理解为初始化阶段，属性和对象的赋值构建
 
 
 
 bean初始化过程：
 refresh()--》finishBeanFactoryInitialization(beanFactory);--》beanFactory.preInstantiateSingletons();--》循环beanNames（变量beanDefinitionNames）--》
 
 上述变量变量beanDefinitionNames的值是在AnnotationConfigApplicationContext初始化时候register进来的
 使用 ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);启动时候，在构造方法中
 1、this();--构建AnnotatedBeanDefinitionReader和ClassPathBeanDefinitionScanner，在这两个类中registry所有的bean定义，传递的参数就是对象AnnotationConfigApplicationContext（是BeanDefinitionRegistry接口的实现），类似dubbo利用NamespaceHandlerSupport类初始化解析配置文件一样，利用BeanDefinitionRegistry解析bean定义，只是这里利用AnnotatedBeanDefinitionReader和ClassPathBeanDefinitionScanner
2、register(annotatedClasses);--registry构造函数的参数的class
3、refresh();--执行bean的扩展方法等，如上面 Spring提供的常用的接口和类，在BeanFactory、ApplicationContext、bean的各个阶段执行对应的扩展方法。初始化（先实例化，后初始化，目前是初始化阶段）所有的非懒加载的实例，顺带有注册功能，如invokeBeanFactoryPostProcessors(beanFactory);和finishBeanFactoryInitialization(beanFactory);等
 
 
 
 