/**
 * 项目名称：quickstart-guice 
 * 文件名：MyAppTest.java
 * 版本信息：
 * 日期：2018年5月21日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.guice.sample;

import org.junit.BeforeClass;
import org.junit.Test;
import org.quickstart.guice.sample.service.Application;
import org.quickstart.guice.sample.service.ApplicationProvider;
import org.quickstart.guice.sample.service.UserService;
import org.quickstart.guice.sample.service.UserServiceProvides;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * MyAppTest
 * 
 * @author：youngzil@163.com
 * @2018年5月21日 下午5:10:31
 * @since 1.0
 */
public class MyAppTest {
    private static Injector injector;

    @BeforeClass
    public static void init() {
        // 调用Guice.createInjector方法传入配置类来创建一个注入器，然后使用注入器的getInstance方法获取目标类，Guice会按照配置帮我们注入所有依赖。
        injector = Guice.createInjector(new MyAppModule());
    }

    @Test
    public void testMyApp() {
        Application myApp = injector.getInstance(Application.class);
        myApp.work();
        
        UserService userService = injector.getInstance(UserService.class);//链式绑定,返回的是最终的
        userService.process();
        
        UserServiceProvides userService2 = injector.getInstance(UserServiceProvides.class);//链式绑定,返回的是最终的
        userService2.process();
        
        ApplicationProvider myAppProvider = injector.getInstance(ApplicationProvider.class);
        myAppProvider.work();
        
    }
}
