/**
 * 项目名称：msgframe-client 
 * 文件名：Task1.java
 * 版本信息：
 * 日期：2017年12月18日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.thread.scheduledthread;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Task1
 * 
 * @author：youngzil@163.com
 * @2017年12月18日 下午5:13:26
 * @since 1.0
 */
public class Task1 implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("Task1 start running: " + new Date());
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int number = random.nextInt(base.length());
        sb.append(base.charAt(number));
        System.out.println("Task1 end running: " + new Date());
        return base;
    }

}
