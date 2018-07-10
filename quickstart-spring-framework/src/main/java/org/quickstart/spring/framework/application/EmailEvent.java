/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：EmailEvent.java
 * 版本信息：
 * 日期：2018年1月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.application;

import org.springframework.context.ApplicationEvent;

/**
 * EmailEvent
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月17日 上午10:30:53
 * @since 1.0
 */
public class EmailEvent extends ApplicationEvent {
    /**
     * <p>
     * Description：
     * </p>
     */
    private static final long serialVersionUID = 1L;
    public String address;
    public String text;

    public EmailEvent(Object source) {
        super(source);
    }

    public EmailEvent(Object source, String address, String text) {
        super(source);
        this.address = address;
        this.text = text;
    }

    public void print() {
        System.out.println("hello spring event!");
    }

}
