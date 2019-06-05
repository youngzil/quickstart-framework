/**
 * 项目名称：quickstart-proxy 
 * 文件名：UserManagerTest.java
 * 版本信息：
 * 日期：2018年4月17日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.statics.jdk;

/**
 * UserManagerTest
 * 
 * @author：youngzil@163.com
 * @2018年4月17日 下午5:42:50
 * @since 1.0
 */
public class UserManagerTest {

    public static void main(String[] args) {
        UserManager userManager = new UserManagerImplProxy(new UserManagerImpl());
        userManager.addUser("001", "于亮");
    }

}
