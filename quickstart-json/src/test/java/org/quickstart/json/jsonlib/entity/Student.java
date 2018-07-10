/**
 * 项目名称：quickstart-json 
 * 文件名：Student.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jsonlib.entity;

/**
 * Student
 * 
 * @author：yangzl@asiainfo.com
 * @2017年12月13日 下午8:24:05
 * @since 1.0
 */
public class Student {

    private int id;
    private String name;
    private String email;
    private String address;
    private Birthday birthday;

    // setter、getter
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public void setBirthday(Birthday birthday) {
        this.birthday = birthday;
    }

    public String toString() {
        return this.name + "#" + this.id + "#" + this.address + "#" + this.birthday + "#" + this.email;
    }

}
