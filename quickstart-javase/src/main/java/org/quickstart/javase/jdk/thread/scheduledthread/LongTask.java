/**
 * 项目名称：msgframe-client 
 * 文件名：LongTask.java
 * 版本信息：
 * 日期：2017年12月18日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.thread.scheduledthread;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * LongTask
 * 
 * @author：youngzil@163.com
 * @2017年12月18日 下午5:16:58
 * @since 1.0
 */
public class LongTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("LongTask start running: " + new Date());
        TimeUnit.SECONDS.sleep(30);
        System.out.println("LongTask end running: " + new Date());
        return "success";
    }

}
