/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：MessagePrinter.java
 * 版本信息：
 * 日期：2017年12月11日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.spring.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MessagePrinter
 * 
 * @author：yangzl@asiainfo.com
 * @2017年12月11日 上午8:34:48
 * @since 1.0
 */
@Component
public class MessagePrinter {

    final private MessageService service;

    @Autowired
    public MessagePrinter(MessageService service) {
        this.service = service;
    }

    public void printMessage() {
        System.out.println(this.service.getMessage());
    }
}
