/**
 * 项目名称：quickstart-javase 
 * 文件名：ClassLoaderTest.java
 * 版本信息：
 * 日期：2017年7月30日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.classloader.demo1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassLoaderTest
 * 
 * @author：youngzil@163.com
 * @2017年7月30日 下午10:22:41
 * @version 2.0
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        DeClassLoader diskLoader = new DeClassLoader("D:\\lib");
        try {
            // 加载class文件
            Class c = diskLoader.loadClass("com.frank.test.Test");

            if (c != null) {
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say", null);
                    // 通过反射调用Test类的say方法
                    method.invoke(obj, null);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
