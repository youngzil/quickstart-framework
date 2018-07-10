package com.quickstart.test.task;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.quickstart.test.task.timertask.TimerTask1;
import com.rensanning.quartz.Job1;

public class TaskManage extends HttpServlet {

    /**
    * 
    */
    private static final long serialVersionUID = -8163920027219324473L;
    /**
     * 无延迟
     */
    public static final long NO_DELAY = 0;
    /**
     * 定时器
     */
    private Timer timer;

    // 周期，单位ms，周期为24小时
    private static final long PERIOD_TIME = 24 * 60 * 60 * 1000;

    public TaskManage() {
        super();
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void init() throws ServletException {
        // 定义定时器
        timer = new Timer("定时任务", true);
        // 启动更新任务,每销售执行一次
        try {
            timer.schedule(new TimerTask1(), NO_DELAY, PERIOD_TIME);

            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler sched = sf.getScheduler();
            Date runTime = DateBuilder.evenSecondDate(new Date());

            JobDetail job = JobBuilder.newJob(Job1.class).withIdentity("job1", "group1").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
            sched.scheduleJob(job, trigger);
            sched.start();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        super.destroy();
    }

}
