/**
 * 项目名称：quickstart-proxy 
 * 文件名：CglibProxyTest.java
 * 版本信息：
 * 日期：2018年4月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.dynamic.cglib;

import org.quickstart.proxy.statics.jdk.UserManager;
import org.quickstart.proxy.statics.jdk.UserManagerImpl;

import net.sf.cglib.proxy.Enhancer;

/**
 * CglibProxyTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月17日 下午6:01:02
 * @since 1.0
 */
public class CglibProxyTest {

    public static void main(String[] args) {

        CglibProxy cglibProxy = new CglibProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserManagerImpl.class);
        enhancer.setCallback(cglibProxy);

        UserManager userManager = (UserManager) enhancer.create();
        String name = userManager.findUser("0001");
        System.out.println("CglibProxyTest.main-->>" + name);
    }

}
