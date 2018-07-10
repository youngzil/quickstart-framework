首先 classpath是指 WEB-INF文件夹下的classes目录
src不是classpath, WEB-INF/classes,lib才是classpath
WEB-INF/ 是资源目录, 客户端不能直接访问, 

classpath 和 classpath* 区别：
classpath：只会到你指定的class路径中查找文件;
classpath*：不仅包含class路径，还包括jar文件中(class路径)进行查找.

注意： 用classpath*:需要遍历所有的classpath，所以加载速度是很慢的；因此，在规划的时候，应该尽可能规划好资源文件所在的路径，尽量避免使用classpath*。

classpath*的使用：
当项目中有多个classpath路径，并同时加载多个classpath路径下（此种情况多数不会遇到）的文件，*就发挥了作用，如果不加*，则表示仅仅加载第一个classpath路径。

一些使用技巧：
1、从上面使用的场景看，可以在路径上使用通配符*进行模糊查找。比如：
<param-value>classpath:applicationContext-*.xml</param-value>  
2、"**/"表示的是任意目录；"**/applicationContext-*.xml"表示任意目录下的以"applicationContext-"开头的XML文件。  
3、程序部署到tomcat后，src目录下的配置文件会和class文件一样，自动copy到应用的WEB-INF/classes目录下；classpath:与classpath*:的区别在于，前者只会从第一个classpath中加载，而 后者会从所有的classpath中加载。
4、如果要加载的资源，不在当前ClassLoader的路径里，那么用classpath:前缀是找不到的，这种情况下就需要使用classpath*:前缀。
5、在多个classpath中存在同名资源，都需要加载时，那么用classpath:只会加载第一个，这种情况下也需要用classpath*:前缀。


举个简单的例子，在我的web.xml中是这么定义的：classpath*:META-INF/spring/application-context.xml
那么在META-INF/spring这个文件夹底下的所有application-context.xml都会被加载到上下文中，这些包括 META-INF/spring文件夹底下的 application-context.xml，META-INF/spring的子文件夹的application-context.xml以及 jar中的application-context.xml。

如果我在web.xml中定义的是：classpath:META-INF/spring/application-context.xml
那么只有META-INF/spring底下的application-context.xml会被加载到上下文中。


通常我们一般使用这种写法实在web.xml中，比如spring加载bean的上下文时，如下：
<!--系统自动加载文件-->
<!--这里使用的是classpath*:的形式-->
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath*:/spring-context-*.xml</param-value>
</context-param>
<!--配置spring的context监听器  -->
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener
</listener-class>
</listener>




