/**
 * 项目名称：quickstart-reflect 
 * 文件名：ReflectDemo.java
 * 版本信息：
 * 日期：2018年11月13日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.reflect.jdk;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * ReflectDemo
 * 
 * @author：youngzil@163.com
 * @2018年11月13日 上午12:01:52
 * @since 1.0
 */
public class ReflectDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        // 第一种：Class c1 = Code.class;
        Class class1 = ReflectDemo.class;
        System.out.println(class1.getName());

        // 第二种：Class c2 = code1.getClass();
        ReflectDemo demo2 = new ReflectDemo();
        Class c2 = demo2.getClass();
        System.out.println(c2.getName());

        // 第三种：Class c3 = Class.forName("com.trigl.reflect.Code");
        Class class3 = Class.forName("org.quickstart.reflect.jdk.ReflectDemo");
        System.out.println(class3.getName());

        try {
            Class c = Class.forName("org.quickstart.reflect.jdk.Person"); // 先生成class
            Object o = c.newInstance(); // newInstance可以初始化一个实例
            Method method = c.getMethod("fun", String.class, int.class);// 获取方法
            method.invoke(o, "tengj", 10); // 通过invoke调用该方法，参数第一个为实例对象，后面为具体参数值

            // 两个参数分别是方法名和方法参数类的类类型列表。
            // public Method getDeclaredMethod(String name, Class<?>... parameterTypes) // 得到该类所有的方法，不包括父类的
            // public Method getMethod(String name, Class<?>... parameterTypes) // 得到该类所有的public方法，包括父类的

            // 具体使用
            Method[] methods = c.getDeclaredMethods();// 获取class对象的所有声明方法,不包括父类的
            Method[] allMethods = c.getMethods();// 获取class对象的所有public方法 包括父类的方法
            // Method method = class1.getMethod("info", String.class);//返回次Class对象对应类的、带指定形参列表的public方法
            // Method declaredMethod = class1.getDeclaredMethod("info", String.class);//返回次Class对象对应类的、带指定形参列表的方法
            for (Method m : methods) {
                String methodName = m.getName();
                System.out.println(methodName);
            }

            // 成员变量中都包括什么：成员变量类型+成员变量名
            // public Field getDeclaredField(String name) // 获得该类自身声明的所有变量，不包括其父类的变量
            // public Field getField(String name) // 获得该类自所有的public成员变量，包括其父类变量

            // 具体实现，获取成员变量
            Field[] allFields = c.getDeclaredFields();// 获取class对象的所有属性
            Field[] publicFields = c.getFields();// 获取class对象的public属性
            Field ageField = c.getDeclaredField("age");// 获取class指定属性
            Field desField = c.getField("name");// 获取class指定的public属性

            Field field = c.getDeclaredField("msg"); // 因为msg变量是private的，所以不能用getField方法
            Object object = c.newInstance();
            field.setAccessible(true);// 设置是否允许访问，因为该变量是private的，所以要手动设置允许访问，如果msg是public的就不需要这行了。
            Object msg = field.get(object);
            System.out.println(msg);

            for (Field field2 : allFields) {
                System.out.println(field2.getName());
            }

            // 获取构造函数，构造函数中都包括什么：构造函数参数
            // public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) // 获得该类所有的构造器，不包括其父类的构造器
            // public Constructor<T> getConstructor(Class<?>... parameterTypes) // 获得该类所以public构造器，包括父类

            // 具体
            Constructor<?>[] allConstructors = c.getDeclaredConstructors();// 获取class对象的所有声明构造函数
            Constructor<?>[] publicConstructors = c.getConstructors();// 获取class对象public构造函数
            Constructor<?> constructor = c.getDeclaredConstructor(String.class);// 获取指定声明构造函数
            Constructor publicConstructor = c.getConstructor(String.class);// 获取指定声明的public构造函数

            // Class的newInstance方法，只能创建只包含无参数的构造函数的类
            c.newInstance();

            // 如果某类只有带参数的构造函数，那么就要使用下面方式，先获取构造函数，再调用Constructor对象的newInstance()方法来创建实例
            // 获取构造函数
            Constructor constructor2 = c.getDeclaredConstructor(String.class);
            constructor2.setAccessible(true);// 设置是否允许访问，因为该构造器是private的，所以要手动设置允许访问，如果构造器是public的就不需要这行了。
            constructor2.newInstance("tengj");

            for (Constructor constructor3 : allConstructors) {
                System.out.println(constructor3);
            }

            // 注解需要用到的
            Annotation[] annotations = (Annotation[]) class1.getAnnotations();// 获取class对象的所有注解
            Annotation annotation = (Annotation) class1.getAnnotation(Deprecated.class);// 获取class对象指定注解
            Type genericSuperclass = class1.getGenericSuperclass();// 获取class对象的直接超类的
            Type[] interfaceTypes = class1.getGenericInterfaces();// 获取class对象的所有接口的type集合
            // getSuperclass()：获取某类的父类
            // getInterfaces()：获取某类实现的接口

            // 获取class对象的信息
            boolean isPrimitive = class1.isPrimitive();// 判断是否是基础类型
            boolean isArray = class1.isArray();// 判断是否是集合类
            boolean isAnnotation = class1.isAnnotation();// 判断是否是注解类
            boolean isInterface = class1.isInterface();// 判断是否是接口类
            boolean isEnum = class1.isEnum();// 判断是否是枚举类
            boolean isAnonymousClass = class1.isAnonymousClass();// 判断是否是匿名内部类
            boolean isAnnotationPresent = class1.isAnnotationPresent(Deprecated.class);// 判断是否被某个注解类修饰
            String className = class1.getName();// 获取class名字 包含包名路径
            Package aPackage = class1.getPackage();// 获取class的包信息
            String simpleName = class1.getSimpleName();// 获取class类名
            int modifiers = class1.getModifiers();// 获取class访问权限
            Class<?>[] declaredClasses = class1.getDeclaredClasses();// 内部类
            Class<?> declaringClass = class1.getDeclaringClass();// 外部类

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
