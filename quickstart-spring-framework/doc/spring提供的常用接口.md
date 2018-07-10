BeanPostProcessor：通过实现BeanPostProcessor接口允许用户对新建的Bean进行修改。
BeanPostProcessor接口的作用是在Spring容器完成Bean实例化前后可以添加一些自己的逻辑处理，我们可以定义一个或者多个BeanPostProcessor接口的实现。
之前的InitializingBean、DisposableBean、FactoryBean包括init-method和destory-method，针对的都是某个Bean控制其初始化的操作，而似乎没有一种办法可以针对每个Bean的生成前后做一些逻辑操作，PostProcessor则帮助我们做到了这一点，先看一个简单的
应用中自定义的Bean，可以实现这个接口，并覆盖这两个方法来控制Bean的初始化过程，即在Bean的初始化之前做一件事，即调用postProcessBeforeInitialization方法，也可以在Bean的初始化之后做一件事，即调用postProcessAfterInitialization方法。
BeanPostProcessor。
BeanPostProcess接口有两个方法，都可以见名知意：
1、postProcessBeforeInitialization：可以对Bean在实例化之前添加一些逻辑处理，Bean初始化的前置处理器 
2、postProcessAfterInitialization：可以对bean在实例化之后添加一些逻辑处理，Bean初始化的后置处理器  


BeanFactoryPostProcessor
接下来看另外一个PostProcessor----BeanFactoryPostProcessor。
Spring允许在Bean创建之前，读取Bean的元属性，并根据自己的需求对元属性进行改变，比如将Bean的scope从singleton改变为prototype，最典型的应用应当是PropertyPlaceholderConfigurer，替换xml文件中的占位符，替换为properties文件中相应的key对应的value，这将会在下篇文章中专门讲解PropertyPlaceholderConfigurer的作用及其原理。
BeanFactoryPostProcessor就可以帮助我们实现上述的功能
1、BeanFactoryPostProcessor的执行优先级高于BeanPostProcessor
2、BeanFactoryPostProcessor的postProcessBeanFactory()方法只会执行一次
注意到postProcessBeanFactory方法是带了参数ConfigurableListableBeanFactory的，这就和我之前说的可以使用BeanFactoryPostProcessor来改变Bean的属性相对应起来了。ConfigurableListableBeanFactory功能非常丰富，最基本的，它携带了每个Bean的基本信息


InstantiationAwareBeanPostProcessor
最后写一个叫做InstantiationAwareBeanPostProcessor的PostProcessor。
InstantiationAwareBeanPostProcessor又代表了Spring的另外一段生命周期：实例化。先区别一下Spring Bean的实例化和初始化两个阶段的主要作用：
1、实例化----实例化的过程是一个创建Bean的过程，即调用Bean的构造函数，单例的Bean放入单例池中
2、初始化----初始化的过程是一个赋值的过程，即调用Bean的setter，设置Bean的属性
之前的BeanPostProcessor作用于过程（2）前后，现在的InstantiationAwareBeanPostProcessor则作用于过程（1）前后
1、Bean构造出来之前调用postProcessBeforeInstantiation()方法
2、Bean构造出来之后调用postProcessAfterInstantiation()方法
不过通常来讲，我们不会直接实现InstantiationAwareBeanPostProcessor接口，而是会采用继承InstantiationAwareBeanPostProcessorAdapter这个抽象类的方式来使用。


InitializingBean：通过实现InitializingBean接口可以在BeanFactory 设置所有的属性后作出进一步的反应可以实现该接口，提供了一个唯一的方法afterPropertiesSet()，Bean属性都设置完毕后调用afterPropertiesSet()方法做一些初始化的工作
当需要在bean的全部属性设置成功后做些特殊的处理，可以让该bean实现InitializingBean接口。       效果等同于bean的init-method属性的使用或者@PostContsuct注解的使用。       三种方式的执行顺序：先注解，然后执行InitializingBean接口中定义的方法，最后执行init-method属性指定的方法。


DisposableBean：提供了一个唯一的方法destory()，在Bean生命周期结束前调用destory()方法做一些收尾工作
当需要在bean销毁之前做些特殊的处理，可以让该bean实现DisposableBean接口。       效果等同于bean的destroy-method属性的使用或者@PreDestory注解的使用。       三种方式的执行顺序：先注解，然后执行DisposableBean接口中定义的方法，最后执行destroy-method属性指定的方法


BeanNameAware、ApplicationContextAware和BeanFactoryAware、BeanClassLoaderAware
这三个接口放在一起写，是因为它们是一组的，作用相似。
"Aware"的意思是"感知到的"，那么这三个接口的意思也不难理解：
1、实现BeanNameAware接口的Bean，在Bean加载的过程中可以获取到该Bean的id
2、实现ApplicationContextAware接口的Bean，在Bean加载的过程中可以获取到Spring的ApplicationContext，这个尤其重要，ApplicationContext是Spring应用上下文，从ApplicationContext中可以获取包括任意的Bean在内的大量Spring容器内容和信息
3、实现BeanFactoryAware接口的Bean，在Bean加载的过程中可以获取到加载该Bean的BeanFactory
ApplicationContextAware：通过实现ApplicationContextAware接口，在ApplicationContext运行的时候被通知并注入ApplicationContext上下文。
BeanNameAware：通过实现BeanNameAware接口可以知道在BeanFactory中设置的名字时可以实现该接口。
这样就可以获取到该Bean在Spring容器中的名字，原理就是上述invokeAwareMethods方法中，判断了如果bean实现了BeanNameAware接口，就会调用该Bean覆盖的BeanNameAware接口的setBeanName方法，这样MyBean中就获取到了该Bean在Spring容器中的名字。
BeanClassLoaderAware接口和BeanFactoryAware接口同理，可以分别获取Bean的类装载器和bean工厂



BeanFactory
　　BeanFactory，以Factory结尾，表示它是一个工厂类(接口)，用于管理Bean的一个工厂。在Spring中，BeanFactory是IOC容器的核心接口，它的职责包括：实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。
　　Spring为我们提供了许多易用的BeanFactory实现，XmlBeanFactory就是常用的一个，该实现将以XML方式描述组成应用的对象及对象间的依赖关系。XmlBeanFactory类将持有此XML配置元数据，并用它来构建一个完全可配置的系统或应用。
　　实例化容器
1 Resource resource = new FileSystemResource("beans.xml");
2 BeanFactory factory = new XmlBeanFactory(resource);

1 ClassPathResource resource = new ClassPathResource("beans.xml");
2 BeanFactory factory = new XmlBeanFactory(resource);

1 ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml", "applicationContext-part2.xml"});
3 BeanFactory factory = (BeanFactory) context;

 基本就是这些了，接着使用getBean(String beanName)方法就可以取得bean的实例；BeanFactory提供的方法及其简单，仅提供了六种方法供客户调用：
　　boolean containsBean(String beanName) 判断工厂中是否包含给定名称的bean定义，若有则返回true
　　Object getBean(String) 返回给定名称注册的bean实例。根据bean的配置情况，如果是singleton模式将返回一个共享实例，否则将返回一个新建的实例，如果没有找到指定bean,该方法可能会抛出异常
　　Object getBean(String, Class) 返回以给定名称注册的bean实例，并转换为给定class类型
　　Class getType(String name) 返回给定名称的bean的Class,如果没有找到指定的bean实例，则排除NoSuchBeanDefinitionException异常
　　boolean isSingleton(String) 判断给定名称的bean定义是否为单例模式
　　String[] getAliases(String name) 返回给定bean名称的所有别名


FactoryBean
　　以Bean结尾，表示它是一个Bean，不同于普通Bean的是：它是实现了FactoryBean<T>接口的Bean，根据该Bean的ID从BeanFactory中获取的实际上是FactoryBean的getObject()返回的对象，而不是FactoryBean本身，如果要获取FactoryBean对象，请在id前面加一个&符号来获取。也就是getBean("bean")和getBean("&bean")的区别
　　例如自己实现一个FactoryBean，功能：用来代理一个对象，对该对象的所有方法做一个拦截，在调用前后都输出一行LOG，模仿ProxyFactoryBean的功能。
getBean("bean")和getBean("&bean")的区别：
前者是bean对象，后者是获取这个bean的FactoryBean对象，可以通过FactoryBean.getObject来获取bean对象


ApplicationContext、ApplicationEvent、ApplicationListener
什么是ApplicationContext? 
它是Spring的核心，Context我们通常解释为上下文环境，但是理解成容器会更好些。 
ApplicationContext则是应用的容器。
Spring把Bean（object）放在容器中，需要用就通过get方法取出来。

ApplicationEvent
是个抽象类，里面只有一个构造函数和一个长整型的timestamp。

ApplicationListener
是一个接口，里面只有一个onApplicationEvent方法。
所以自己的类在实现该接口的时候，要实装该方法。

如果在上下文中部署一个实现了ApplicationListener接口的bean,
那么每当在一个ApplicationEvent发布到 ApplicationContext时，
这个bean得到通知。其实这就是标准的Oberver设计模式。


BeanDefinitionParser接口：配置文件解析，唯一方法parse()


NamespaceHandlerSupport类：配置解析注入，一般重写init()方法



PropertyPlaceholderConfigurer类
 用于读取Java属性文件中的属性，然后插入到BeanFactory的定义中。
  PropertyPlaceholderConfigurer的另一种精简配置方式（context命名空间）：
<context:property-placeholder location="classpath:jdbc.properties, classpath:mails.properties"/>  
 Java属性文件内容：
                   jdbc.driverClassName=oracle.jdbc.driver.OracleDriver
                   jdbc.url=jdbc:oracle:thin:@localhost:1521:orcl
                   jdbc.username=qycd
                   jdbc.password=qycd
 除了可以读取Java属性文件中的属性外，还可以读取系统属性和系统环境变量的值。
 读取系统环境变量的值：${JAVA_HOME}
                  读取系统属性的值：${user.dir}
        
                  
PropertyOverrideConfigurer类
 用于读取Java属性文件中的属性，并覆盖XML配置文件中的定义，即PropertyOverrideConfigurer允许XML配置文件中有默认的配置信息。
 Java属性文件的格式：
                      beanName.property=value
                      beanName是属性占位符企图覆盖的bean名，property是企图覆盖的数姓名。                  
                  
                  
ResourceBundleMessageSource类
 提供国际化支持，bean的名字必须为messageSource。此处，必须存在一个名为jdbc的属性文件。
<bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">  
    <property name="basenames">  
        <list>  
            <value>jdbc</value>  
        </list>  
    </property>  
</bean> 
 jdbc.properties属性文件的内容：
welcome={0}, welcome to guangzhou!  
AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("applicationContext.xml");  
ctx.getMessage("welcome", new String[]{"张三"}, "", Locale.CHINA); 


CustomEditorConfigurer类
     CustomEditorConfigurer可以读取实现java.beans.PropertyEditor接口的类，将字符串转为指定的类型。更方便的可以使用PropertyEditorSupport。PropertyEditorSupport实现PropertyEditor接口，必须重新定义setAsText。
自定义属性编辑器继承PropertyEditorSupport类，重写setAsText方法。








