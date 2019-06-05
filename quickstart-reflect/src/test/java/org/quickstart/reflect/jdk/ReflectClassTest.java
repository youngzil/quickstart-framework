/**
 * 项目名称：quickstart-reflect 
 * 文件名：ReflectClassTest.java
 * 版本信息：
 * 日期：2018年11月13日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.reflect.jdk;

/**
 * ReflectClassTest 
 *  
 * @author：youngzil@163.com
 * @2018年11月13日 上午12:00:31 
 * @since 1.0
 */
public class ReflectClassTest {
    
    public static void main(String[] args) throws ClassNotFoundException {
        Class c1 = ReflectClassTest.class; //这说明任何一个类都有一个隐含的静态成员变量class，这种方式是通过获取类的静态成员变量class得到的
        Class c2 = new ReflectClassTest().getClass();// code1是Code的一个对象，这种方式是通过一个类的对象的getClass()方法获得的 
        Class c3 = Class.forName("org.quickstart.reflect.jdk.ReflectClassTest"); //这种方法是Class类调用forName方法，通过一个类的全量限定名获得

    }

}
