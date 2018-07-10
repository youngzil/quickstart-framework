/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：EmailListener.java
 * 版本信息：
 * 日期：2018年1月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.application;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * EmailListener
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月17日 上午10:31:21
 * @since 1.0
 */
public class EmailListener implements ApplicationListener {

    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof EmailEvent) {
            EmailEvent emailEvent = (EmailEvent) event;
            emailEvent.print();
            System.out.println("the source is:" + emailEvent.getSource());
            System.out.println("the address is:" + emailEvent.address);
            System.out.println("the email's context is:" + emailEvent.text);
        }

    }

}
