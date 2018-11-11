/**
 * 项目名称：quickstart-web 
 * 文件名：User.java
 * 版本信息：
 * 日期：2016年12月29日
 * Copyright asiainfo Corporation 2016
 * 版权所有 *
 */
package com.quickstart.test.socket.object;

/**
 * User
 * 
 * @author：youngzil@163.com
 * @2016年12月29日 下午3:28:40
 * @version 1.0
 */
public class User implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String password;

    public User() {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
