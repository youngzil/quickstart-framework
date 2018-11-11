/**
 * 项目名称：quickstart-xml 
 * 文件名：User.java
 * 版本信息：
 * 日期：2018年5月20日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package com.quickstart.xml.xstream.annotation;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Getter;
import lombok.Setter;

/**
 * User
 * 
 * @author：youngzil@163.com
 * @2018年5月20日 下午10:59:38
 * @since 1.0
 */
@Setter
@Getter
@XStreamAlias("user")
public class User {

    @XStreamAlias("username")
    private String userName;

    @XStreamAlias("email")
    private String email;

    public User() {}

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String toString() {
        return "User:{userName=" + this.userName + ",email=" + this.email + "}";
    }

}
