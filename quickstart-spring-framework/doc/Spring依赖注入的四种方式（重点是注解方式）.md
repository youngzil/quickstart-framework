在Spring容器中为一个bean配置依赖注入有三种方式：
· 使用属性的setter方法注入  这是最常用的方式；<property name="userDao" ref="userDao">
· 使用构造器注入；<constructor-arg index="0" type="com.aptech.dao.PersonDAO"ref="personDao"></constructor-arg>  
· 使用Filed注入（用于注解方式）:在代码中加入@Resource或者@Autowired
· 依赖注入—自动装配:<bean id="***" class="***" autowire="byType">,只需要配置一个autowire属性即可完成自动装配，不用再配置文件中写<property>,但是在类中还是要生成依赖对象的setter方法。

在Java代码中可以使用@Autowired或@Resource注解方式进行Spring的依赖注入。两者的区别是：@Autowired默认按类型装配，@Resource默认按名称装配，当找不到与名称匹配的bean时，才会按类型装配。







参考
https://blog.csdn.net/cheetahlover/article/details/51600991



