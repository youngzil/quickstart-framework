/**
 * 项目名称：quickstart-javase 
 * 文件名：GenericTest.java
 * 版本信息：
 * 日期：2018年11月22日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * GenericTest
 * 
 * @author：youngzil@163.com
 * @2018年11月22日 下午8:26:13
 * @since 1.0
 */
public class GenericTest {

    public static void main(String[] args) {
        // test();
        // test2();
        // test3();
        // test4();

    }

    private static void test() {
        List list = new ArrayList();
        list.add("qqyumidi");
        list.add("corn");
        list.add(100);

        for (int i = 0; i < list.size(); i++) {
            String name = (String) list.get(i); // 1
            System.out.println("name:" + name);
        }
    }

    private static void test2() {

        List<String> list = new ArrayList<String>();
        list.add("qqyumidi");
        list.add("corn");
        // list.add(100); // 1 提示编译错误

        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i); // 2
            System.out.println("name:" + name);
        }

    }

    private static void test3() {
        Box<String> name2 = new Box<String>("corn");
        System.out.println("name:" + name2.getData());

        Box<String> name = new Box<String>("corn");
        Box<Integer> age = new Box<Integer>(712);

        System.out.println("name class:" + name.getClass()); // com.qqyumidi.Box
        System.out.println("age class:" + age.getClass()); // com.qqyumidi.Box
        System.out.println(name.getClass() == age.getClass()); // true

    }

    private static void test4() {
        Box<Integer> a = new Box<Integer>(712);
        // Box<Number> b = a; // 1 报错
        Box<Float> f = new Box<Float>(3.14f);
        // b.setData(f); // 2

    }

    private static void test5() {
        
        Box<String> name = new Box<String>("corn");
        Box<Integer> age = new Box<Integer>(712);
        Box<Number> number = new Box<Number>(314);

        getData2(name);
        getData2(age);
        getData2(number);

    }

    public static void getData(Box<Number> data) {
        System.out.println("data :" + data.getData());
    }
    
    public static void getData2(Box<?> data) {
        System.out.println("data :" + data.getData());
    }

}


class Box<T> {

    private T data;

    public Box() {

    }

    public Box(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

}


class Box2<T extends String> {

    private T data;

    public Box2() {

    }

    public Box2(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

}


class BoxSon extends Box<String> {

}
