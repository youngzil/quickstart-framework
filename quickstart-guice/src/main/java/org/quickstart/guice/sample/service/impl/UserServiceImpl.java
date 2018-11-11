/**
 * 项目名称：quickstart-guice 
 * 文件名：UserServiceImpl.java
 * 版本信息：
 * 日期：2018年5月21日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.guice.sample.service.impl;

import org.quickstart.guice.sample.service.UserService;

/**
 * UserServiceImpl 
 *  
 * @author：youngzil@163.com
 * @2018年5月21日 下午5:07:44 
 * @since 1.0
 */
public class UserServiceImpl implements UserService {
    @Override
    public void process() {
        System.out.println("我需要做一些业务逻辑");
    }
}
