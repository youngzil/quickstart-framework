/**
 * 项目名称：quickstart-reflect 
 * 文件名：ClassTest.java
 * 版本信息：
 * 日期：2018年11月12日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.reflect.jdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年11月12日 下午11:27:38
 * @since 1.0
 */
public class ClassTest {

    public static void main(String[] args) {

        // 1、使用Class的静态方法
        try {
            Class clazz = Class.forName("org.quickstart.reflect.jdk.ClassTest");
            System.out.println(clazz);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 2、直接获取一个对象的Class
        Class<ClassTest> c = ClassTest.class;
        Class<String> s = String.class;
        Class<Integer> i = int.class;
        System.out.println(c);

        // 3、调用某个对象的getClass方法
        ArrayList list = new ArrayList();
        Class<?> listClass = list.getClass();
        System.out.println(listClass);

        // 判断是否为某个类的实例，一般我们使用instanceof，也可以使用Class.isInstance(obj)。
        StringBuilder sb = new StringBuilder();
        Class<?> clazz = sb.getClass();
        System.out.println(clazz.isInstance(sb));
        System.out.println(clazz.isInstance(sb));

        // 用反射来生成对象的方式主要有两种。
        // 使用Class.newInstance方法,这个方法最终调用的是无参数的构造函数，所以如果对象没有无参数的构造函数就会报错了。
        // 使用newInstance必须要保证：1、这个 类已经加载；2、这个类已经连接了。
        // newInstance()实际上是把new这个方式分解为两步，即首先调用Class加载方法加载某个类，然后实例化。当然构造方法不能是私有的。
        Class<ClassTest> clazz2 = ClassTest.class;
        try {
            ClassTest ct = clazz2.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // - 先通过Class对象获取指定的Constructor对象，再调用Constructor对象的newInstance()方法来创建实例。
        // 这种方法可以用指定的构造器构造类的实例。当然构造方法不能是私有的。
        Class<String> strClass = String.class;
        try {
            Constructor constructor = strClass.getConstructor(String.class);
            Object o = constructor.newInstance("378");
            System.out.println(o);
        } catch (NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 获取构造器信息（Constructor）
        // 如上

        // 获取类的方法（Method）

        // 获取类的成员变量信息（Field）

    }

    public void testGenericType() throws SecurityException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<String> genericTypeValue = new ArrayList<String>();
        List nullGenericType;
        Class<ClassTest> clazz = ClassTest.class;

        // 如果类属性的类型带有类型参数，如List<T>
        // 那么想获取类型T时用field.getGenericType();方法,然后转型为参数化类型[ParameterizedType]
        Field genericTypeField1 = clazz.getDeclaredField("genericTypeValue");
        Field genericTypeField2 = clazz.getDeclaredField("nullGenericType");

        ParameterizedType genericType1 = (ParameterizedType) genericTypeField1.getGenericType();
        //         nullGenericType并没有参数类型，强制转换为(ParameterizedType)会抛异常！
        //         只能转换为(Class<?>)或通过getType()获得类型
        //         ParameterizedType genericType2 = (ParameterizedType)genericTypeField2.getGenericType();
        Class<?> type1 = genericTypeField1.getType();// type1为List<String>的类型！
        Class<?> Type2 = (Class<?>) genericTypeField2.getGenericType();
        Class<?> Type2_1 = genericTypeField2.getType();

        // 通过参数化类型[ParameterizedType]获得声明的参数类型的数组
        Type[] types1 = genericType1.getActualTypeArguments();
        Class<?> typeValue1 = (Class<?>) types1[0];
        System.out.println("typeValue1:" + typeValue1);// class test.String
        System.out.println("typeValue2:" + Type2);// interface java.util.List
        System.out.println("typeValue2_1:" + Type2_1); // interface java.util.List

        if (typeValue1.equals(String.class))// true
            System.out.println("typeValue1.equals(String.class)?" + typeValue1.equals(String.class));
        if (Type2.equals(List.class)) // true
            System.out.println("Type2.equals(List.class)?" + Type2.equals(List.class));

        // 创建包含参数类型的类型的对象[异常！类型声明为接口List，而却要创建ArrayList]
        //         ArrayList<String> newInstance = (ArrayList<String>) type1.newInstance();
        //         newInstance.add("123");

    }

}
