package com.quickstart.test.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class Hello2 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException {
        Class<?> demo = null;
        try {
            demo = Class.forName("com.yang.test.reflect.Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Person per = null;
        try {
            per = (Person) demo.newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        per.setName("Rollen");
        per.setAge(20);
        System.out.println(per);

        Person per1 = null;
        Person per2 = null;
        Person per3 = null;
        Person per4 = null;
        // 取得全部的构造函数
        Constructor<?> cons[] = demo.getConstructors();
        for (int i = 0; i < cons.length; i++) {
            System.out.println("构造方法：  " + cons[i]);
        }

        try {
            per1 = (Person) cons[3].newInstance();
            per2 = (Person) cons[0].newInstance("Rollen");
            per3 = (Person) cons[1].newInstance(20);
            per4 = (Person) cons[2].newInstance("Rollen", 20);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(per1);
        System.out.println(per2);
        System.out.println(per3);
        System.out.println(per4);

        try {
            demo = Class.forName("java.lang.String");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 保存所有的接口
        Class<?> intes[] = demo.getInterfaces();
        for (int i = 0; i < intes.length; i++) {
            System.out.println("实现的接口   " + intes[i].getName());
        }

        try {
            demo = Class.forName("java.lang.String");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 取得父类
        Class<?> temp = demo.getSuperclass();
        System.out.println("继承的父类为：   " + temp.getName());

        try {
            demo = Class.forName("com.yang.test.reflect.Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Method method[] = demo.getMethods();
        for (int i = 0; i < method.length; ++i) {
            Class<?> returnType = method[i].getReturnType();
            Class<?> para[] = method[i].getParameterTypes();
            int temp1 = method[i].getModifiers();
            System.out.print(Modifier.toString(temp1) + " ");
            System.out.print(returnType.getName() + "  ");
            System.out.print(method[i].getName() + " ");
            System.out.print("(");
            for (int j = 0; j < para.length; ++j) {
                System.out.print(para[j].getName() + " " + "arg" + j);
                if (j < para.length - 1) {
                    System.out.print(",");
                }
            }
            Class<?> exce[] = method[i].getExceptionTypes();
            if (exce.length > 0) {
                System.out.print(") throws ");
                for (int k = 0; k < exce.length; ++k) {
                    System.out.print(exce[k].getName() + " ");
                    if (k < exce.length - 1) {
                        System.out.print(",");
                    }
                }
            } else {
                System.out.print(")");
            }
            System.out.println();
        }

        try {
            demo = Class.forName("com.yang.test.reflect.Person");
            demo = Class.forName("java.lang.String");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===============本类属性========================");
        // 取得本类的全部属性
        Field[] field = demo.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            // 权限修饰符
            int mo = field[i].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = field[i].getType();
            System.out.println(priv + " " + type.getName() + " " + field[i].getName() + ";");
        }
        System.out.println("===============实现的接口或者父类的属性========================");
        // 取得实现的接口或者父类的属性
        Field[] filed1 = demo.getFields();
        for (int j = 0; j < filed1.length; j++) {
            // 权限修饰符
            int mo = filed1[j].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = filed1[j].getType();
            System.out.println(priv + " " + type.getName() + " " + filed1[j].getName() + ";");
        }

        try {
            demo = Class.forName("com.yang.test.reflect.Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // 调用Person类中的sayChina方法
            Method method1 = demo.getMethod("sayChina");
            method1.invoke(demo.newInstance());
            // 调用Person的sayHello方法
            method1 = demo.getMethod("sayHello", String.class, int.class);
            method1.invoke(demo.newInstance(), "Rollen", 20);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Object obj = null;
        try {
            demo = Class.forName("com.yang.test.reflect.Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            obj = demo.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setter(obj, "Sex", "男", String.class);
        getter(obj, "Sex");

        // 通过反射操作属性
        demo = Class.forName("com.yang.test.reflect.Person");
        obj = demo.newInstance();

        Field field3 = demo.getDeclaredField("sex");
        field3.setAccessible(true);
        field3.set(obj, "男");
        System.out.println(field3.get(obj));

        // 通过反射取得并修改数组的信息
        int[] temp2 = {1, 2, 3, 4, 5};
        demo = temp2.getClass().getComponentType();
        System.out.println("数组类型： " + demo.getName());
        System.out.println("数组长度  " + Array.getLength(temp2));
        System.out.println("数组的第一个元素: " + Array.get(temp2, 0));
        Array.set(temp2, 0, 100);
        System.out.println("修改之后数组第一个元素为： " + Array.get(temp2, 0));

        // 获得类加载器
        Person t = new Person();
        System.out.println("类加载器  " + t.getClass().getClassLoader().getClass().getName());

    }

    /**
     * @param obj 操作的对象
     * @param att 操作的属性
     */
    public static void getter(Object obj, String att) {
        try {
            Method method = obj.getClass().getMethod("get" + att);
            System.out.println(method.invoke(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param obj 操作的对象
     * @param att 操作的属性
     * @param value 设置的值
     * @param type 参数的属性
     */
    public static void setter(Object obj, String att, Object value, Class<?> type) {
        try {
            Method method = obj.getClass().getMethod("set" + att, type);
            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Person {

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public Person(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public void sayChina() {
        System.out.println("hello ,china");
    }

    public void sayHello(String name, int age) {
        System.out.println(name + "  " + age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "[" + this.name + "  " + this.age + "]";
    }

    private String name;
    private int age;
}
