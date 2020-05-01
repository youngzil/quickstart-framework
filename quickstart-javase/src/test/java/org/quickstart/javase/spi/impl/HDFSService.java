/**
 * 项目名称：quickstart-javase 
 * 文件名：HDFSService.java
 * 版本信息：
 * 日期：2017年8月22日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.spi.impl;

import org.quickstart.javase.spi.IService;

/**
 * HDFSService
 * 
 * @author：youngzil@163.com
 * @2017年8月22日 下午4:01:43
 * @since 1.0
 */
public class HDFSService implements IService {

    @Override
    public String sayHello() {
        return "Hello HDFSService";
    }

    @Override
    public String getScheme() {
        return "hdfs";
    }

}
