/**
 * 项目名称：quickstart-quartz 
 * 文件名：QuartzTest.java
 * 版本信息：
 * 日期：2018年5月21日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.quartz;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * QuartzTest
 * 
 * @author：youngzil@163.com
 * @2018年5月21日 下午12:52:32
 * @since 1.0
 */
public class QuartzTest {

    public static void main(String[] args) throws Throwable {
        SchedulerFactory factory = new StdSchedulerFactory();
        // 从工厂里面拿到一个scheduler实例
        Scheduler scheduler = factory.getScheduler();
        // 计算任务的开始时间，DateBuilder.evenMinuteDate方法是取下一个整数分钟
        Date runTime = DateBuilder.evenMinuteDate(new Date());
        // 真正执行的任务并不是Job接口的实例，而是用反射的方式实例化的一个JobDetail实例
        JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
        // 定义一个触发器，startAt方法定义了任务应当开始的时间
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
        // 将任务和Trigger放入scheduler
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        try {
            // 等待65秒，保证下一个整数分钟出现，这里注意，如果主线程停止，任务是不会执行的
            Thread.sleep(65L * 1000L);
        } catch (Exception e) {

        }
        // scheduler结束
        scheduler.shutdown(true);
    }

}
