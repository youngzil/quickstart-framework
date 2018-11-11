/**
 * 项目名称：quickstart-guice 
 * 文件名：MyApp.java
 * 版本信息：
 * 日期：2018年5月21日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.guice.sample.service.impl;

import org.quickstart.guice.sample.service.Application;
import org.quickstart.guice.sample.service.LogService;
import org.quickstart.guice.sample.service.UserService;

import com.google.inject.Inject;

/**
 * MyApp 
 *  
 * @author：youngzil@163.com
 * @2018年5月21日 下午5:09:07 
 * @since 1.0
 */
public class MyApp implements Application {
    private UserService userService;
    private LogService logService;

    @Inject
    public MyApp(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    @Override
    public void work() {
        userService.process();
        logService.log("程序正常运行");
    }
}
