/**
 * 项目名称：quickstart-javase 
 * 文件名：HashSetTest.java
 * 版本信息：
 * 日期：2018年1月24日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.hashcode;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * HashSetTest
 * 
 * @author：youngzil@163.com
 * @2018年1月24日 下午9:35:25
 * @since 1.0
 */
public class HashSetTest {
    public static void main(String[] args) {
        Set<Person> set = new HashSet<Person>();

        Person p1 = new Person(1, "test", 1);
        Person p2 = new Person(2, "test", 2);
        Person p3 = new Person(3, "test", 3);
        Person p4 = new Person(4, "test", 4);

        Person p5 = new Person(5, "test", 1);

        // test1，不重写equals和hashcode的时候
        System.out.println("p1.hashCode()=" + p1.hashCode());
        System.out.println("p5.hashCode()=" + p5.hashCode());

        System.out.println("p1.toString()=" + p1.toString());
        System.out.println("p5.toString()=" + p5.toString());

        System.out.println("p1=p5:" + p1.equals(p5));

        /* 
         在集合框架中的HashSet，HashTable和HashMap都使用哈希表的形式存储数据，而hashCode计算出来的哈希码便是它们的身份证
        a.  在java应用程序运行时，无论何时多次调用同一个对象时的hsahCode()方法，这个对象的hashCode()方法的返回值必须是相同的一个int值
        b.  如果两个对象equals()返回值为true,则他们的hashCode()也必须返回相同的int值
        c.  如果两个对象equals()返回值为false,则他们的hashCode()返回值也必须不同
        */
        // test2 hashCode()返回值相等，equals()返回false
        // test3 和hashCode()返回值不相等，equals()返回值为true；
        // test3 和hashCode()和equals()一致；

        System.out.println("-------------");
        set.add(p1);
        set.add(p5);

        System.out.println("p1.hashCode()=" + p1.hashCode());
        System.out.println("p5.hashCode()=" + p5.hashCode());

        System.out.println("p1.toString()=" + p1.toString());
        System.out.println("p5.toString()=" + p5.toString());

        System.out.println("p1=p5:" + p1.equals(p5));

        System.out.println("set count=" + set.size());

    }

}
