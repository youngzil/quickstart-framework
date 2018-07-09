/**
 * 项目名称：quickstart-xbean 
 * 文件名：Teacher.java
 * 版本信息：
 * 日期：2017年3月19日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.xbean.simple;

import java.io.Serializable;

/**
 * Teacher
 * 
 * @author：yangzl@asiainfo.com
 * @2017年3月19日 下午12:56:43
 * @version 1.0
 */
public class Teacher implements Serializable {

    private static final long serialVersionUID = -8409611806997881614L;

    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
