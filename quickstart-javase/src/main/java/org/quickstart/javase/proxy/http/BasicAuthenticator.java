/**
 * 项目名称：quickstart-javase 
 * 文件名：BasicAuthenticator.java
 * 版本信息：
 * 日期：2018年1月21日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.proxy.http;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * BasicAuthenticator 
 *  
 * @author：youngzil@163.com
 * @2018年1月21日 下午4:00:21 
 * @since 1.0
 */
/**
 * 实现sun.net的代理验证
 * 
 * @author tzz
 * @createDate 2015年7月23日
 * 
 */
public class BasicAuthenticator extends Authenticator {
    String userName;
    String password;

    public BasicAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Called when password authorization is needed. Subclasses should override the default implementation, which returns null.
     * 
     * @return The PasswordAuthentication collected from the user, or null if none is provided.
     */
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        // System.out.println("DEBUG === use global authentication of password");
        return new PasswordAuthentication(userName, password.toCharArray());
    }
}
