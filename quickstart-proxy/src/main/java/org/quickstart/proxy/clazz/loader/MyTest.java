/**
 * 项目名称：quickstart-proxy 
 * 文件名：MyTest.java
 * 版本信息：
 * 日期：2018年8月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.clazz.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * MyTest
 * 
 * @author：youngzil@163.com
 * @2018年8月11日 下午10:52:09
 * @since 1.0
 */
public class MyTest {

    public static final String commonClazzPath = "/target/classes/org/quickstart/proxy/clazz/loader/Programmer.class";
    public static final String asmClazzPath = "/src/main/java/org/quickstart/proxy/statics/asm/Programmer.class";
    public static final String javassistClazzBaseDir = "/src/main/java";
    public static final String javassistClazzPath = "/src/main/java/org/quickstart/proxy/statics/javassist/Programmer.class";

//    public static final String clazzPath = MyTest.commonClazzPath;
//    public static final String clazzPath = MyTest.asmClazzPath;
    public static final String clazzPath = MyTest.javassistClazzPath;

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {

        // 读取本地的class文件内的字节码，转换成字节码数组
        File file = new File(".");
        System.out.println(file.getCanonicalPath());
        InputStream input = new FileInputStream(file.getCanonicalPath() + clazzPath);
        byte[] result = new byte[1024];

        int count = input.read(result);
        // 使用自定义的类加载器将 byte字节码数组转换为对应的class对象
        MyClassLoader loader = new MyClassLoader();
        Class clazz = loader.defineMyClass(result, 0, count);
        // 测试加载是否成功，打印class 对象的名称
        System.out.println(clazz.getCanonicalName());

        // 实例化一个Programmer对象
        Object o = clazz.newInstance();
        // 调用Programmer的code方法
        try {
            clazz.getMethod("code", null).invoke(o, null);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

}
