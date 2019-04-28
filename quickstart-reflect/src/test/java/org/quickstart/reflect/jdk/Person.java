/**
 * 项目名称：quickstart-reflect 
 * 文件名：Person.java
 * 版本信息：
 * 日期：2018年11月13日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.reflect.jdk;

import lombok.Getter;
import lombok.Setter;

/**
 * Person
 * 
 * @author：youngzil@163.com
 * @2018年11月13日 上午9:23:51
 * @since 1.0
 */
@Setter
@Getter
public class Person {

    public String name;
    private int age;
    private String msg = "hello wrold";

    public Person() {}

    private Person(String name) {
        this.name = name;
        System.out.println(name);
    }

    public void fun() {
        System.out.println("fun");
    }

    public void fun(String name, int age) {
        System.out.println("我叫" + name + ",今年" + age + "岁");
    }

}
