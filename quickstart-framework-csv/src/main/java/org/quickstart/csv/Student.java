/**
 * 项目名称：quickstart-csv 
 * 文件名：Student.java
 * 版本信息：
 * 日期：2018年5月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.csv;

/**
 * Student 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年5月22日 下午7:37:01 
 * @since 1.0
 */
public class Student {
    public Student() {

    }
    public Student(String id, String name, String gender, String major) {
        super();
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.major = major;
    }

    private String id;
    private String name;
    private String gender;
    private String major;

    public String getID() {
        return id;
    }
    public void setID(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return id + ',' + name + ',' + gender + ',' + major;
    }
}
