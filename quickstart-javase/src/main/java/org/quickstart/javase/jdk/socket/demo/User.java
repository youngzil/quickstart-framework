/**
 * 项目名称：quickstart-javase 
 * 文件名：User.java
 * 版本信息：
 * 日期：2017年8月10日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.socket.demo;

/**
 * User
 * 
 * @author：youngzil@163.com
 * @2017年8月10日 下午11:34:59
 * @since 1.0
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
