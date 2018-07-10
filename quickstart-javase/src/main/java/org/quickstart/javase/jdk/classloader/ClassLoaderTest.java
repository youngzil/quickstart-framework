/**
 * 项目名称：quickstart-javase 
 * 文件名：ClassLoaderTest.java
 * 版本信息：
 * 日期：2017年7月30日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * ClassLoaderTest BootStrap ClassLoader、ExtClassLoader、AppClassLoader都是加载指定路径下的jar包。如果我们要突破这种限制，实现自己某些特殊的需求，我们就得自定义ClassLoader，自已指定加载的路径，可以是磁盘、内存、网络或者其它。
 * 
 * @author：yangzl@asiainfo.com
 * @2017年7月30日 下午2:31:26
 * @version 2.0
 */

public class ClassLoaderTest {

    public static void main(String[] args) {

        /*
         * BootStrap
         * ClassLoader：称为启动类加载器，是Java类加载层次中最顶层的类加载器，负责加载JDK中的核心类库，如：rt.jar、
         * resources.jar、charsets.jar等，可通过如下程序获得该类加载器从哪些地方加载了相关的jar或class文件：
         */
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }

        // 其实上述结果也是通过查找sun.boot.class.path这个系统属性所得知的。
        String bootstrapClassPath = System.getProperty("sun.boot.class.path");
        System.out.println("bootstrapClassCloaderPath=" + bootstrapClassPath);

        String extClassPath = System.getProperty("java.ext.dirs");
        System.out.println("Extention ClassLoader  path = " + extClassPath);

        String appClassPath = System.getProperty("java.class.path");
        System.out.println("AppClassLoader path = " + appClassPath);

        System.out.println("打印类加载器ClassLoaderTest start");
        ClassLoader loader = ClassLoaderTest.class.getClassLoader(); // 获得加载ClassLoaderTest.class这个类的类加载器
        while (loader != null) {
            System.out.println(loader);
            loader = loader.getParent(); // 获得父类加载器的引用
        }
        System.out.println(loader);

        System.out.println("远程加载class start");
        try {
            /*
             * ClassLoader loader = ClassLoaderTest.class.getClassLoader();
             * //获得ClassLoaderTest这个类的类加载器 while(loader != null) {
             * System.out.println(loader); loader = loader.getParent();
             * //获得父加载器的引用 } System.out.println(loader);
             */

            String rootUrl = "http://localhost:8080/httpweb/classes";
            NetworkClassLoader networkClassLoader = new NetworkClassLoader(rootUrl);
            String classname = "org.classloader.simple.NetClassLoaderTest";
            Class clazz = networkClassLoader.loadClass(classname);
            System.out.println(clazz.getClassLoader());

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("远程加载class end");

        System.out.println("本地磁盘加载class start");
        // 创建自定义classloader对象。
        DiskClassLoader diskLoader = new DiskClassLoader("D:\\lib");
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
        System.out.println("本地磁盘加载class end");

    }

}
