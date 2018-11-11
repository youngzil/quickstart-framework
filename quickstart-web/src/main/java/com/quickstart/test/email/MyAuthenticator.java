/**
 * 项目名称：quickstart-web 
 * 文件名：MyAuthenticator.java
 * 版本信息：
 * 日期：2017年2月18日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package com.quickstart.test.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * MyAuthenticator
 * 
 * @author：youngzil@163.com
 * @2017年2月18日 上午11:18:33
 * @version 1.0
 */
public class MyAuthenticator extends Authenticator {
    String userName = null;
    String password = null;

    public MyAuthenticator() {}

    public MyAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}
