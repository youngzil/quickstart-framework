/**
 * 项目名称：quickstart-javase 
 * 文件名：HelloListener.java
 * 版本信息：
 * 日期：2018年6月11日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jmx.example;

import javax.management.Notification;
import javax.management.NotificationListener;

/**
 * HelloListener
 * 
 * @author：youngzil@163.com
 * @2018年6月11日 下午6:56:46
 * @since 1.0
 */
public class HelloListener implements NotificationListener {

    public void handleNotification(Notification notification, Object handback) {
        if (handback instanceof HelloWorld) {
            HelloWorld hello = (HelloWorld) handback;
            hello.printHello(notification.getMessage());
        }
    }

}
