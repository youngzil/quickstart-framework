/**
 * 项目名称：quickstart-javase 
 * 文件名：AtomicReferenceTest.java
 * 版本信息：
 * 日期：2017年7月25日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.juc.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReferenceTest
 * 
 * @author：yangzl@asiainfo.com
 * @2017年7月25日 上午9:36:23
 * @version 2.0
 */
public class AtomicReferenceTest {

    public static void main(String[] args) {

        // 创建两个Person对象，它们的id分别是101和102。
        Person p1 = new Person(101);
        Person p2 = new Person(102);
        // 新建AtomicReference对象，初始化它的值为p1对象
        AtomicReference ar = new AtomicReference(p1);
        // 通过CAS设置ar。如果ar的值为p1的话，则将其设置为p2。
        ar.compareAndSet(p1, p2);

        Person p3 = (Person) ar.get();
        System.out.println("p3 is " + p3);
        System.out.println("p3.equals(p1)=" + p3.equals(p1));
    }
}


class Person {
    volatile long id;

    public Person(long id) {
        this.id = id;
    }

    public String toString() {
        return "id:" + id;
    }
}
