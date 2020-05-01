/**
 * 项目名称：quickstart-javase 
 * 文件名：MyInterfaceImpl.java
 * 版本信息：
 * 日期：2017年8月7日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk8.key;

/**
 * MyInterfaceImpl
 * 
 * @author：youngzil@163.com
 * @2017年8月7日 下午9:57:56
 * @version 2.0
 */
public class MyInterfaceImpl implements MyInterface {

    public static void main(String[] args) {
        MyInterface interfaceImpl = new MyInterfaceImpl();
        interfaceImpl.api();
        MyInterface.myStaticMethod();// 只能通过本接口（类访问）
    }

}
