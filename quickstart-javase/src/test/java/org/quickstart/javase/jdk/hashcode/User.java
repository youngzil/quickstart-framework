/**
 * 项目名称：quickstart-javase 
 * 文件名：User.java
 * 版本信息：
 * 日期：2018年1月24日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.hashcode;

import lombok.Data;

import java.util.Objects;

/**
 * User
 * 
 * http://blog.csdn.net/zzg1229059735/article/details/51498310
 * 
 * @author：youngzil@163.com
 * @2018年1月24日 下午10:07:19
 * @since 1.0
 */
@Data
public class User {

    private String name;
    private int age;
    private String passport;

    // getters and setters, constructor

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name) && Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, passport);
    }

}
