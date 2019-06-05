/**
 * 项目名称：quickstart-javase 
 * 文件名：Client.java
 * 版本信息：
 * 日期：2018年1月19日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.proxy;

/**
 * Client
 * 
 * @author：youngzil@163.com
 * @2018年1月19日 下午10:41:48
 * @since 1.0
 */
public class ClientTest {

    public static void main(String[] args) {

        UserManager userManager = (UserManager) new CGLibProxy().createProxyObject(new UserManagerImpl());
        System.out.println("-----------CGLibProxy-------------");
        userManager.addUser("tom", "root");
        System.out.println("-----------JDKProxy-------------");
        JDKProxy jdkPrpxy = new JDKProxy();
        UserManager userManagerJDK = (UserManager) jdkPrpxy.newProxy(new UserManagerImpl());
        userManagerJDK.addUser("tom", "root");
    }

}
