/**
 * 项目名称：quickstart-json 
 * 文件名：AccountBean.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jackson.v1.entity;

/**
 * AccountBean
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午11:09:57
 * @since 1.0
 */
public class AccountBean {

    private int id;
    private String name;
    private String email;
    private String address;
    private Birthday birthday;

    // getter、setter

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

    @Override
    public String toString() {
        return this.name + "#" + this.id + "#" + this.address + "#" + this.birthday + "#" + this.email;
    }

}
