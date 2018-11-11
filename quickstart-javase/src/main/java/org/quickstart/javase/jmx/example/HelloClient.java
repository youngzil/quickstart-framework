/**
 * 项目名称：quickstart-javase 
 * 文件名：Client.java
 * 版本信息：
 * 日期：2018年6月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jmx.example;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * Client
 * 
 * http://www.cnblogs.com/dongguacai/p/5900507.html
 * 
 * @author：youngzil@163.com
 * @2018年6月10日 下午11:38:45
 * @since 1.0
 */
public class HelloClient {
    public static void main(String[] args) throws IOException, MalformedObjectNameException, InstanceNotFoundException, AttributeNotFoundException, InvalidAttributeValueException, MBeanException,
            ReflectionException, IntrospectionException {
        String domainName = "MyMBean";
        int rmiPort = 1099;
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + rmiPort + "/jmxrmi");
        // 可以类比HelloAgent.java中的那句：
        // JMXConnectorServer jmxConnector = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        // 1、打印Domains信息 print domains
        System.out.println("Domains:------------------");
        String domains[] = mbsc.getDomains();
        for (int i = 0; i < domains.length; i++) {
            System.out.println("\tDomain[" + i + "] = " + domains[i]);
        }

        // 2、打印ObjectName信息 ObjectName of MBean
        System.out.println("all ObjectName:--------------");
        Set<ObjectInstance> set = mbsc.queryMBeans(null, null);
        for (Iterator<ObjectInstance> it = set.iterator(); it.hasNext();) {
            ObjectInstance oi = it.next();
            System.out.println("\t" + oi.getObjectName());
        }

        // 3、打印MBean数量 MBean count
        System.out.println("MBean count = " + mbsc.getMBeanCount());

        // ObjectName的名称与前面注册时候的保持一致
        ObjectName mBeanName = new ObjectName(domainName + ":name=HelloWorld"); // 多个属性使用,分隔

        // 4、打印MBean信息 get mbean information
        MBeanInfo info = mbsc.getMBeanInfo(mBeanName);
        System.out.println("Hello Class: " + info.getClassName());

        // 属性
        for (int i = 0; i < info.getAttributes().length; i++) {
            System.out.println("Hello Attribute:" + info.getAttributes()[i].getName());
        }

        // 方法
        for (int i = 0; i < info.getOperations().length; i++) {
            System.out.println("Hello Operation:" + info.getOperations()[i].getName());
        }

        // 5、操作MBean属性 process attribute
        // 设置指定Mbean的特定属性值
        // 这里的setAttribute、getAttribute操作只能针对bean的属性
        // 例如对getGreeting或者setGreeting进行操作，只能使用Name，需要去除方法的前缀
        mbsc.setAttribute(mBeanName, new Attribute("Greeting", "zzh"));// 注意这里是Greeting而不是greeting
        System.out.println("Greeting = " + mbsc.getAttribute(mBeanName, "Greeting"));

        // 接下去是执行Hello中的printHello方法，分别通过代理和rmi的方式执行
        // 6、操作MBean方法 via proxy
        HelloWorldMBean proxy = MBeanServerInvocationHandler.newProxyInstance(mbsc, mBeanName, HelloWorldMBean.class, false);
        proxy.printGreeting();
        // proxy.printHello("jizhi boy");
        // via rmi

        // invoke调用bean的方法，只针对非设置属性的方法
        // 例如invoke不能对getGreeting方法进行调用
        mbsc.invoke(mBeanName, "printGreeting", null, null);
        mbsc.invoke(mBeanName, "printHello", new String[] {"jizhi gril"}, new String[] {String.class.getName()});
        mbsc.invoke(mBeanName, "printHello", new String[] {"I'll connect to JMX Server via client2"}, new String[] {"java.lang.String"});

        jmxc.close();
    }
}
