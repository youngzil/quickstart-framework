/**
 * 项目名称：quickstart-quartz 
 * 文件名：MyJob.java
 * 版本信息：
 * 日期：2018年5月21日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.quartz;

import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * MyJob 
 *  
 * @author：youngzil@163.com
 * @2018年5月21日 下午1:00:52 
 * @since 1.0
 */
public class MyJob implements Job {  
    @Override  
    public void execute(JobExecutionContext context) throws JobExecutionException {  
        System.out.println("任务正在执行，执行时间: " + Calendar.getInstance().getTime());  
    }  
}  