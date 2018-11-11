/**
 * 项目名称：quickstart-proxy 
 * 文件名：Student.java
 * 版本信息：
 * 日期：2018年4月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.statics.javassist;

/**
 * Student
 * 
 * @author：youngzil@163.com
 * @2018年4月17日 下午6:17:37
 * @since 1.0
 */
public class Student {

    private Long id;
    private String name = "default";
    private int age;

    public Student() {
        name = "me";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void execute() {
        System.out.println(name);
        System.out.println("execute ok");
    }

}
