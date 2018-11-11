/**
 * 项目名称：quickstart-javase 
 * 文件名：MXBeanServer.java
 * 版本信息：
 * 日期：2018年6月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jmx.example;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * MXBeanServer 
 *  
 * @author：youngzil@163.com
 * @2018年6月11日 下午9:59:20 
 * @since 1.0
 */
public class MXBeanServer {
    
    public static void main(String[] args) throws Exception {  
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();  
          
        ObjectName mbeanName = new ObjectName("me.clarenceau.jmx.mxbean:type=Hello");  
          
        MXHello mbean = new MXHello("ClarenceAu", 23, "ozhencong@gmail.com");  
        mbean.addBook(new Book("Thinking in Java", 99.0));  
        mbean.addBook(new Book("Design Pattern", 59.0));  
          
        server.registerMBean(mbean, mbeanName);  
          
        System.out.println("Wait ...");  
        System.in.read();
    }  

}
