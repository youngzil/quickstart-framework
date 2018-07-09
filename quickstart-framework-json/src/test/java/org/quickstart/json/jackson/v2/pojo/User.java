/**
 * 项目名称：quickstart-json 
 * 文件名：User.java
 * 版本信息：
 * 日期：2017年12月14日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jackson.v2.pojo;

import java.util.Date;

/**
 * User
 * 
 * @author：yangzl@asiainfo.com
 * @2017年12月14日 下午10:59:38
 * @since 1.0
 */
public class User {

    private String name;
    private Integer age;
    private Date birthday;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
