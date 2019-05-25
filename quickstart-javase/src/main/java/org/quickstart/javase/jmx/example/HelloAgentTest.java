/**
 * 项目名称：quickstart-javase 
 * 文件名：HelloAgent2.java
 * 版本信息：
 * 日期：2018年6月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jmx.example;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * HelloAgent2
 * 
 * http://www.cnblogs.com/dongguacai/p/5900507.html
 * 
 * https://blog.csdn.net/u013256816/article/details/52800742 
 * 
 * https://blog.csdn.net/fanyingnew/article/details/1546238
 * 
 * @author：youngzil@163.com
 * @2018年6月10日 下午11:30:11
 * @since 1.0
 */
public class HelloAgentTest {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, IOException {
        // 下面这种方式不能再JConsole中使用
        // MBeanServer server = MBeanServerFactory.createMBeanServer();
        // 首先建立一个MBeanServer,MBeanServer用来管理我们的MBean,通常是通过MBeanServer来获取我们MBean的信息，间接
        // 调用MBean的方法，然后生产我们的资源的一个对象。
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        String domainName = "MyMBean";

        // 1、使用jconsole访问，/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/bin/jconsole
        // 为MBean（下面的new Hello()）创建ObjectName实例
        ObjectName helloName = new ObjectName(domainName + ":name=HelloWorld"); // 多个属性使用,分隔
        // 将new Hello()这个对象注册到MBeanServer上去
        HelloWorld hello = new HelloWorld();
        mbs.registerMBean(hello, helloName);
        
        
        Jack jack = new Jack();
        mbs.registerMBean(jack, new ObjectName("jack:name=Jack"));
        jack.addNotificationListener(new HelloListener(), null, hello);
        
        
      //create object name  
        ObjectName dynamicBeanHello = new ObjectName("jmx:name=dynamicBeanHello");  
        //create mbean and register mbean  
        mbs.registerMBean(new HelloDynamic(), dynamicBeanHello);  

        
        // 2、使用web访问：http://localhost:8082/
        // Distributed Layer, 提供了一个HtmlAdaptor。支持Http访问协议，并且有一个不错的HTML界面，这里的Hello就是用这个作为远端管理的界面
        // 事实上HtmlAdaptor是一个简单的HttpServer，它将Http请求转换为JMX Agent的请求

        ObjectName adapterName = new ObjectName(domainName + ":name=htmladapter,port=8082"); // 多个属性使用,分隔
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        mbs.registerMBean(adapter, adapterName);
        adapter.start();

        // 3、客户端通过rmi方式连接JMXConnectorServer，jconsole或者代码
        try {
            int rmiPort = 1099;
            // 这个步骤很重要，注册一个端口，绑定url后用于客户端通过rmi方式连接JMXConnectorServer
            Registry registry = LocateRegistry.createRegistry(rmiPort);// %JAVA_HOME%/bin/rmiregistry.exe 1099
            // URL路径的结尾可以随意指定，但如果需要用Jconsole来进行连接，则必须使用jmxrmi
            // service:jmx:rmi:///jndi/rmi://localhost:1099/MyMBean
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + rmiPort + "/jmxrmi");
            JMXConnectorServer jmxConnector = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
            System.out.println("begin rmi start");
            jmxConnector.start();
            System.out.println("rmi start");

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
