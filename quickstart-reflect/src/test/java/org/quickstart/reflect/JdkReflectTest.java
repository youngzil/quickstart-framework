/**
 * 项目名称：quickstart-reflect 
 * 文件名：JdkReflectTest.java
 * 版本信息：
 * 日期：2018年11月12日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.reflect;

import java.lang.reflect.Method;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * JdkReflectTest
 * 
 * @author：youngzil@163.com
 * @2018年11月12日 下午9:42:09
 * @since 1.0
 */
public class JdkReflectTest {

    public static void main(String[] args) throws Exception {
        testInstance1();
        testInstance2();
        testVariable1();
        testVariable2();
        testCallmethod1();
        testCallmethod2();
        testCallmethod3();
    }

    public static void testInstance1() { // 普通实例化对象
        int i = 0;
        long start = System.currentTimeMillis();
        while (i < 1000000) {
            i++;
            new DemoClass();
        }
        long end = System.currentTimeMillis();
        System.err.println((end - start) + " MillSeconds");
    }

    public static void testInstance2() throws Exception { // 反射实例化对象
        int i = 0;
        long start = System.currentTimeMillis();
        while (i < 1000000) {
            i++;
            DemoClass.class.newInstance();
        }
        long end = System.currentTimeMillis();
        System.err.println((end - start) + " MillSeconds");
    }

    public static void testVariable1() { // 普通获取变量
        int i = 0;
        DemoClass dc = new DemoClass();
        String s;
        long start = System.currentTimeMillis();
        while (i < 1000000) {
            i++;
            s = dc.firstName;
        }
        long end = System.currentTimeMillis();
        System.err.println((end - start) + " MillSeconds");
    }

    public static void testVariable2() throws Exception { // 反射获取变量
        int i = 0;
        DemoClass dc = new DemoClass();
        String s;
        long start = System.currentTimeMillis();
        while (i < 1000000) {
            i++;
            s = (String) DemoClass.class.getField("firstName").get(dc);
        }
        long end = System.currentTimeMillis();
        System.err.println((end - start) + " MillSeconds");
    }

    public static void testCallmethod1() { // 普通调用方法
        int i = 0;
        DemoClass dc = new DemoClass();
        long start = System.currentTimeMillis();
        while (i < 1000000) {
            i++;
            dc.getBirthDay();
        }
        long end = System.currentTimeMillis();
        System.err.println((end - start) + " MillSeconds");
    }

    public static void testCallmethod2() throws Exception { // 反射调用方法
        int i = 0;
        DemoClass dc = new DemoClass();
        long start = System.currentTimeMillis();
        while (i < 1000000) {
            i++;
            DemoClass.class.getMethod("getBirthDay").invoke(dc);
        }
        long end = System.currentTimeMillis();
        System.err.println((end - start) + " MillSeconds");
    }

    public static void testCallmethod3() throws Exception { // 反射调用方法
        int i = 0;
        DemoClass dc = new DemoClass();
        long start = System.currentTimeMillis();
        while (i < 1000000) {
            i++;
            // 由于JDK的安全检查耗时较多.所以通过setAccessible(true)的方式关闭安全检查就可以达到提升反射速度的目的
            Method m = DemoClass.class.getMethod("getBirthDay");
            m.setAccessible(true);
            m.invoke(dc);
        }
        long end = System.currentTimeMillis();
        System.err.println((end - start) + " MillSeconds");
    }

}


@Getter
@Setter
class DemoClass {
    public String firstName;
    private String endName;
    private Date birthDay;
    private String email;
    private String phone;
    private DemoClass parent;
}
