/**
 * 项目名称：quickstart-yaml 
 * 文件名：Student.java
 * 版本信息：
 * 日期：2017年11月22日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.yaml.snakeyaml;

import java.util.ArrayList;
import java.util.List;

/**
 * Student 学生类
 * 
 * @author：youngzil@163.com
 * @2017年11月22日 下午7:20:24
 * @since 1.0
 */
public class Student {

    private int id;

    private String name;

    private int age;

    // 一个学生有多个电话号码
    List<Tel> tels = new ArrayList<Tel>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Tel> getTels() {
        return tels;
    }

    public void setTels(List<Tel> tels) {
        this.tels = tels;
    }

}
