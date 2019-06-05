/**
 * 项目名称：quickstart-proxy 
 * 文件名：ProxyHandlerTest.java
 * 版本信息：
 * 日期：2018年4月17日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.dynamic.jdk;

import org.quickstart.proxy.statics.jdk.UserManager;
import org.quickstart.proxy.statics.jdk.UserManagerImpl;

/**
 * ProxyHandlerTest
 * 
 * @author：youngzil@163.com
 * @2018年4月17日 下午5:47:54
 * @since 1.0
 */
public class ProxyHandlerTest {

    public static void main(String[] args) {

        ProxyHandler proxyHandler = new ProxyHandler();
        UserManager userManager = (UserManager) proxyHandler.newProxyInstance(new UserManagerImpl());

        String name = userManager.findUser("0001");
        System.out.println("client.main-->>" + name);
    }

}
