```
Servlet3规范demo代码
https://github.com/zhangkaitao/servlet3-showcase


在Servlet 3.0规范推出之后，允许在Servlet代码中使用声明式语法来代替web.xml中的描述信息，这才让web.xml瘦身下来。
注解
web应用中，使用了注解的类只有被放到WEB-INF/classes目录中或WEB-INF/lib目录下的jar中注解才会被Web容器处理。web.xml配置文件的元素的metadata-complete默认为false，表示Web容器必须检查类的注解和web fragments，否则忽略注解和web fragments。
@WebServlet注解用于在Web项目定义Servlet，必须指定urlPatterns或value属性，默认的name属性为全限定类名，@WebServlet注解的类必须继承javax.servlet.http.HttpServlet类；
@WebFilter注解用于在Web项目定义Filter，必须指定urlPatterns，servletNames或value属性，默认的filterName属性为全限定类名，@ WebFilter注解的类必须实现 javax.servlet.Filter；
@WebInitParam注解用于指定传递到Servlet或Filter的初始化参数，它是WebServlet和WebFilter注解的一个属性；
@WebListener注解用于定义Web应用的各种监听器，@WebListener注解的类必须实现以下接口中的其中一个： 
javax.servlet.ServletContextListener 
javax.servlet.ServletContextAttributeListener 
javax.servlet.ServletRequestListener 
javax.servlet.ServletRequestAttributeListener 
javax.servlet.http.HttpSessionListener 
javax.servlet.http.HttpSessionAttributeListener 
javax.servlet.http.HttpSessionIdListener；
@MultipartConfig注解用于指定Servlet请求期望是mime/multipart类型。



Servlet规范定义了一个API标准，这一标准的实现通常称为Servlet容器，比如开源的Tomcat、JBoss。web容器更准确的说应该叫web服务器，它是来管理和部署 web应用的。还有一种服务器叫做应用服务器，它的功能比web服务器要强大的多，因为它可以部署EJB应用，可以实现容器管理的事务，一般的应用服务器 有weblogic和websphere等，它们都是商业服务器，功能强大但都是收费的。web容器最典型的就是tomcat了,Tomcat是web容 器也是servlet容器。所谓容器（服务器、中间件等），就是提供一些底层的、业务无关的基本功能，为真正的Servlet提供服务。简单来说：容器负责根据请求的信息找到对应的Servlet，传递Request和Response参数，调用Servlet的service方法，完成请求的响应。

web.xml的加载顺序与它们在 web.xml 文件中的先后顺序无关，web.xml 的加载顺序是：context- param -> listener -> filter -> servlet ，而同个类型之间的实际程序调用的时候的顺序是根据 对应的 mapping 的顺序进行调用的。
filter-mapping 必须出现在 filter 之后，否则当解析到 filter-mapping 时，它所对应的 filter- name 还未定义。
web 容器启动时初始化每个 filter 时，是按照 filter 配置节出现的顺序来初始化的，当请求资源匹配多 个 filter-mapping 时，filter 拦截资源是按照 filter-mapping 配置节出现的顺序来依次调 用 doFilter() 方法的。  
servlet容器需要在应用项目启动时，给应用项目初始化一个ServletContext作为公共环境容器存放公共信息。ServletContext中的信息都是由容器提供的。通常是配置web.xml
容器将<context-param></context-param>转化为键值对,并交给ServletContext.


Java监听器Listener使用说明
1、什么是Java监听器
监听器也叫Listener，是Servlet的监听器，它可以监听客户端的请求、服务端的操作等。通过监听器，可以自动激发一些操作，比如监听在线的用户的数量。

2、Listener接口分类
1.1> ServletContextListener监听ServletContext对象
1.2> ServletContextAttributeListener监听对ServletContext属性的操作，比如增加、删除、修改

2.1> HttpSessionListener监听Session对象
2.2> HttpSessionActivationListener监听HTTP会话的active和passivate情况，passivate是指非活动的session被写入持久设备（比如硬盘），active相反。
2.3> HttpSessionAttributeListener监听Session中的属性操作

3.1> ServletRequestListener监听Request对象
3.2> ServletRequestAttributeListener监听Requset中的属性操作

一个filter必须实现javax.servlet.Filter。
三个方法
1. void setFilterConfig(FilterConfig config) //设置filter 的配置对象；
2. Filter Config getFilterConfig() //返回filter的配置对象；
3. void doFilter(ServletRequest req,ServletResponse res,FilterChain chain) //执行filter的工作

Filter的生命周期
public void init(FilterConfig filterConfig) throws ServletException;//初始化
和我们编写的Servlet程序一样，Filter的创建和销毁由WEB服务器负责。 web 应用程序启动时，web 服务器将创建Filter 的实例对象，并调用其init方法，读取web.xml配置，完成对象的初始化功能，从而为后续的用户请求作好拦截的准备工作（filter对象只会创建一次，init方法也只会执行一次）。开发人员通过init方法的参数，可获得代表当前filter配置信息的FilterConfig对象。

public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException;//拦截请求
这个方法完成实际的过滤操作。当客户请求访问与过滤器关联的URL的时候，Servlet过滤器将先执行doFilter方法。FilterChain参数用于访问后续过滤器。

public void destroy();//销毁
Filter对象创建后会驻留在内存，当web应用移除或服务器停止时才销毁。在Web容器卸载 Filter 对象之前被调用。该方法在Filter的生命周期中仅执行一次。在这个方法中，可以释放过滤器使用的资源。

FilterConfig接口
用户在配置filter时，可以使用为filter配置一些初始化参数，当web容器实例化Filter对象，调用其init方法时，会把封装了filter初始化参数的filterConfig对象传递进来。因此开发人员在编写filter时，通过filterConfig对象的方法，就可获得以下内容：
String getFilterName();//得到filter的名称。 
String getInitParameter(String name);//返回在部署描述中指定名称的初始化参数的值。如果不存在返回null. 
Enumeration getInitParameterNames();//返回过滤器的所有初始化参数的名字的枚举集合。 
public ServletContext getServletContext();//返回Servlet上下文对象的引用。

servlet接口方法：
void init(ServletConfig config)
void service(ServletRequest req, ServletResponse res)
void destroy();
ServletConfig getServletConfig();
String getServletInfo();

Servlet使用配置文件或者类注解@WebServlet(name = "MyFirstServlet", urlPatterns = {"/MyFirstServlet"})

web.xml中可以定义两种参数：
    一个是全局参数(ServletContext)，通过<context-param></context-param>
    一个是filter、servlet参数，通过在filter、servlet中声明 <init-param>
                                          <param-name>param1</param-name>
                                          <param-value>avalible in servlet init()</param-value>   
                                    </init-param> 
    第一种参数在servlet里面可以通过getServletContext().getInitParameter("context/param")得到
    第二种参数只能在servlet的init()方法中通过this.getInitParameter("param1")取得

Servlet容器
       Servlet：属于Java EE重要技术规范，构建了"接收请求--调用servlet程序处理--返回响应"基本模型。
       Servlet程序：Java提供了开发Servlet程序的API，该API可以说Servlet容器的一部分，它对接应用程序与Servlet容器；
       Servlet容器：就是实现了Servlet技术规范的部署环境，它可以部署运行Servlet程序。
       和所有的Java程序一样，servlet运行在JVM中。引入servlet容器是为了处理复杂的HTTP请求。Servlet容器负责servlet的创建、执行和销毁。
Java WEB容器
       WEB容器：可以部署多个WEB应用程序的环境。
       Java WEB容器：实现了Java EE规定的WEB应用技术规范的的部署环境。
       Java EE WEB应用技术规范：Servlet、JSP（JavaServer Pages）、Java WebSocket等。
       所以，完整的Java WEB容器包含Servlet容器。
Java EE容器
       Java EE容器：实现了Java EE技术规范的部署环境。
       Java EE技术规范：除了上面说的Servlet、JSP等Java EE WEB应用技术规范，还包括EJB（Enterprise JavaBeans）等许多技术规范。
       所以，完整的Java EE容器包含Java WEB容器（Servlet容器）、EJB容器等。

Apache、Nginx、IIS是目前最主流的三个Web服务器。
Tomcat、Jetty、WebLogic、Websphere、JBoss都是Java（EE） WEB应用服务器。


参考文章
http://blog.csdn.net/seelye/article/details/8469575
http://blog.csdn.net/tjiyu/article/details/53148174
http://blog.csdn.net/snarlfuture/article/details/18473761
http://blog.csdn.net/witewater/article/details/54862982

Java监听器Listener使用说明:监听各种事件的Initialized、create、Destoryed事件
http://blog.csdn.net/meng2602956882/article/details/13511587

Java中的Filter过滤器：init、doFilter、destroy方法
http://blog.csdn.net/hejingyuan6/article/details/36633631
https://www.cnblogs.com/coderland/p/5902878.html

Servlet讲解：init、service、destroy方法
http://www.importnew.com/14621.html

Servlet规范总结学习
http://blog.csdn.net/wangyangzhizhou/article/details/52753696
http://blog.csdn.net/zerohuan/article/details/50533966?ref=myread



