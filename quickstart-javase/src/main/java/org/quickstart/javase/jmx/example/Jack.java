/**
 * 项目名称：quickstart-javase 
 * 文件名：Jack.java
 * 版本信息：
 * 日期：2018年6月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jmx.example;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * Jack
 * 
 * http://www.cnblogs.com/dongguacai/p/5900507.html
 * 
 * @author：yangzl@asiainfo.com
 * @2018年6月11日 下午6:48:12
 * @since 1.0
 */
public class Jack extends NotificationBroadcasterSupport implements JackMBean {
    private int seq = 0;

    public void hi() {
        // 创建一个信息包
        Notification notify =
                // 通知名称；谁发起的通知；序列号；发起通知时间；发送的消息
                new Notification("jack.hi", this, ++seq, System.currentTimeMillis(), "jack hahah");
        sendNotification(notify);
    }

}
