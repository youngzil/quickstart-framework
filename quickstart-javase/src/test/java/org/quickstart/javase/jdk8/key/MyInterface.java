/**
 * 项目名称：quickstart-javase 
 * 文件名：MyInterface.java
 * 版本信息：
 * 日期：2017年8月7日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk8.key;

/**
 * MyInterface
 * 
 * @author：youngzil@163.com
 * @2017年8月7日 下午9:57:02
 * @version 2.0
 */
public interface MyInterface {
    default void api() {
        System.out.println("i am interface default implementation");
    }

    static void myStaticMethod() {
        System.out.println("i am static test");
    }
}
