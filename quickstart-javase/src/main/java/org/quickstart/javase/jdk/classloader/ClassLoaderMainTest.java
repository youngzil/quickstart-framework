package org.quickstart.javase.jdk.classloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/5 13:43
 */

/**
 * 测试 ExtClasspathLoader 和 DiskClassLoader
 */
public class ClassLoaderMainTest {

  private static final String PATH = "/Users/yangzl/git/quickstart-framework/quickstart-javase/src/main/java/org/quickstart/javase/jdk/classloader/Person.java";

  private static final String CLASS_FULL_NAME = "org.quickstart.javase.jdk.classloader.Person";

  public static void main(String[] args) throws Exception {
    System.out.println("测试 DiskClassLoader.");
    testDiskClassLoader();

    System.out.println("---------------------");
    System.out.println("测试 ExtClasspathLoader.");

    testExtClasspathLoader();

  }

  private static void testExtClasspathLoader() throws Exception {
    ExtClasspathLoader.loadClasspath(new File(PATH));

    Object obj = Class.forName(CLASS_FULL_NAME).newInstance();

    System.out.println(obj);
  }

  private static void testDiskClassLoader() {
    // 创建自定义classloader对象
    DiskClassLoader diskLoader = new DiskClassLoader(PATH);
    try {
      // 加载class文件
      Class c = diskLoader.loadClass(CLASS_FULL_NAME);

      if (c != null) {
        try {
          Object obj = c.newInstance();
          Method method = c.getDeclaredMethod("say", null);
          //通过反射调用Test类的say方法
          method.invoke(obj, null);
          System.out.println(obj);
        } catch (InstantiationException | IllegalAccessException
            | NoSuchMethodException
            | SecurityException |
            IllegalArgumentException |
            InvocationTargetException e) {
          e.printStackTrace();
        }
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
