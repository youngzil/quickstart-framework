/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：ApplicationContextTest.java
 * 版本信息：
 * 日期：2018年1月16日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * ApplicationContextTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月16日 下午9:54:34
 * @since 1.0
 */
public class ApplicationContextTest {

    public static void main(String[] args) {

        // 方法一：ClassPathXmlApplicationContext --从classpath路径加载配置文件，创建Bean对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"dubbo-demo-provider.xml"});
        // ClassName clazz =(ClassName)ctx.getBean("beanName");

        // 方法二：FileSystemXmlApplicationContext --从指定的目录中加载
        // 说明：这种方式适用于采用Spring框架的独立应用程序，需要程序通过配置文件手工初始化Spring的情况。
        ApplicationContext ctx2 = new FileSystemXmlApplicationContext("src/applicationContext.xml");
        // ClassName clazz =(ClassName)ctx2.getBean("beanName");

        // 方法三：Spring提供的工具类WebApplicationContextUtils获取ApplicationContext对象(通过ServletContext对象获得ApplicationContext对象，然后根据它获得需要的类实例)
        // HttpSession session =request.getSession();
        // ServletContext context = session.getServletContext(); //arg0.getSession().getServletContext();
        // ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        // ClassName clazz =(ClassName)ctx.getBean("beanName");

        /*方法二：通过Spring提供的工具类获取ApplicationContext对象
        代码：
        import org.springframework.web.context.support.WebApplicationContextUtils;
        ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(ServletContext sc);
        ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(ServletContext sc);
        ac1.getBean("beanId");
        ac2.getBean("beanId");
        说明：
        这种方式适合于采用Spring框架的B/S系统，通过ServletContext对象获取ApplicationContext对象，然后在通过它获取需要的类实例。
        上面两个工具方式的区别是，前者在获取失败时抛出异常，后者返回null。*/

        // 虽 然，spring提供了后三种方法可以实现在普通的类中继承或实现相应的类或接口来获取spring 的ApplicationContext对象，但是在使用是一定要注意实现了这些类或接口的普通java类一定要在Spring 的配置文件application-context.xml文件中进行配置。否则获取的ApplicationContext对象将为null。

        /*方法四：继承自抽象类ApplicationObjectSupport
        说明：抽象类ApplicationObjectSupport提供getApplicationContext()方法，可以方便的获取到ApplicationContext。
        Spring初始化时，会通过该抽象类的setApplicationContext(ApplicationContext context)方法将ApplicationContext 对象注入。
        例如：
        import org.springframework.context.support.ApplicationObjectSupport;
        public class ContextOne extends ApplicationObjectSupport
        {
            ......
        }
        ........
        ContextOne one = new ContextOne();
          one.getApplicationContext();*/

        /*方法五：继承自抽象类WebApplicationObjectSupport
        说明：类似上面方法，调用getWebApplicationContext()获取WebApplicationContext
        例如：
        import org.springframework.web.context.support.WebApplicationObjectSupport;
        public class ContextOne extends WebApplicationObjectSupport {
            .......
        }
        ........
        ContextOne one = new ContextOne();
          one.getApplicationContext();*/

        /*方法六：实现接口ApplicationContextAware
        http://blog.csdn.net/kaiwii/article/details/6872642
        当一个类实现了ApplicationContextAware接口后，这个类就可以获得Spring配置文件中的所引用到的bean对象。
        说明：实现该接口的setApplicationContext(ApplicationContext context)方法，并保存ApplicationContext 对象。
        Spring初始化时，会通过该方法将ApplicationContext对象注入。
        例如：
        package com.auth.util;
        import org.springframework.beans.BeansException;
        import org.springframework.context.ApplicationContext;
        import org.springframework.context.ApplicationContextAware;
        //        通过接口ApplicationContextAware获得spring上下文
        public class Context implements ApplicationContextAware {
         private static ApplicationContext ctx;
        //设置ApplicationContext对象
         public void setApplicationContext(ApplicationContext context)
           throws BeansException {
          // TODO Auto-generated method stub
           ctx=context;
         }
         //通过beanName获得实例
         public static Object getBean(String beanName)
         {
          return ctx.getBean(beanName);
         }
        }*/

        /*最后提供一种不依赖于servlet,不需要注入的方式
        注意一点，在服务器启动时，Spring容器初始化时，不能通过以下方法获取Spring 容器，如需细节可以观看源码org.springframework.web.context.ContextLoader
        import org.springframework.web.context.ContextLoader; 
        import org.springframework.web.context.WebApplicationContext; 
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext(); 
          wac.getBean(beanID); */

    }

}
