/**
 * 项目名称：quickstart-guice 
 * 文件名：UserServiceImpl2.java
 * 版本信息：
 * 日期：2018年5月21日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.guice.sample.service.impl;

/**
 * UserServiceImpl2 
 *  
 * @author：youngzil@163.com
 * @2018年5月21日 下午5:34:16 
 * @since 1.0
 */
public class UserServiceImplChild extends UserServiceImpl{
    
    @Override
    public void process() {
        System.out.println("我是UserServiceImplChild，我需要做一些业务逻辑");
    }

}
