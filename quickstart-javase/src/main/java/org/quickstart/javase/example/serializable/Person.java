/**
 * 项目名称：quickstart-javase 
 * 文件名：Person.java
 * 版本信息：
 * 日期：2017年7月26日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example.serializable;

import java.io.Serializable;

/**
 * Person
 * 
 * @author：youngzil@163.com
 * @2017年7月26日 上午11:22:37
 * @version 2.0
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    public int id;
    public String name;
    // private String age;
    private transient String age;

    public Person(int id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "Person: " + id + " " + name + "" + age;
    }
}
