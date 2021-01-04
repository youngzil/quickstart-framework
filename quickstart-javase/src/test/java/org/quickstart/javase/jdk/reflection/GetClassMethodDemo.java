package org.quickstart.javase.jdk.reflection;

import java.lang.reflect.InvocationTargetException;

//获取class的方式
public class GetClassMethodDemo {
    public static void main(String[] args)
        throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException,
        InstantiationException, NoSuchFieldException {
        //第一种，直接通过类名.class
        Class<User> userClass = User.class;
        User user1 = userClass.getConstructor(String.class, int.class, int.class).newInstance("张三", 1, 1);
        System.out.println(user1);
        //第二种，通过对象.getClass
        User user = new User("a", 1, 1);
        Class<? extends User> aClass = user.getClass();

        //第三种
        Class<?> aClass1 = Class.forName("com.javaEE.qucikstart.jdk.reflect.User");
    }
}
