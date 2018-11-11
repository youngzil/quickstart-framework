/**
 * 项目名称：quickstart-guice 
 * 文件名：LogServiceImpl.java
 * 版本信息：
 * 日期：2018年5月21日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.guice.sample.service.impl;

import org.quickstart.guice.sample.service.LogService;

/**
 * LogServiceImpl 
 *  
 * @author：youngzil@163.com
 * @2018年5月21日 下午5:08:28 
 * @since 1.0
 */
public class LogServiceImpl implements LogService {
    @Override
    public void log(String msg) {
        System.out.println("------LOG:" + msg);
    }
}
