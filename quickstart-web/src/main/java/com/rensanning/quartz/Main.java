package com.rensanning.quartz;

import java.util.Date;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.KeyMatcher;

public class Main {

    public static void main(String[] args) throws Exception {

        // Quartz是一个完全由java编写的开源作业调度框架。由James House创建并最初于2001年春天被加入sourceforge工程。
        // Quartz 2.1.7
        // http://quartz-scheduler.org/downloads/catalog

        // 开始时间 startAt
        test1();

        // 简单触发器 withSchedule
        // SimpleScheduleBuilder.simpleSchedule()
        // 可以设置间隔、重复次数
        test2();

        // Cron触发器 CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
        // 使用Unix cron表达式
        test3();

        // 结束时间 endAt
        test4();

        // 作业监听器 addJobListener
        test5();

        // 启动多个作业 scheduleJob
        test6();

        // 列举所有作业 getJobKeys
        test7();

        // 手动触发作业 triggerJob
        test8();

        // 传递参数 usingJobData
        test9();

        // 取消/删除作业
        test10();

        // 作业出错时自动再执行
        test11();

        // 与Spring等框架的集成
        // 略...

    }

    private static void test1() throws Exception {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        Date runTime = DateBuilder.evenSecondDate(new Date());

        JobDetail job = JobBuilder.newJob(Job1.class).withIdentity("job1", "group1").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
        sched.scheduleJob(job, trigger);
        // sched.start();

        if (sched.isStarted()) {
            return;
        }
        // 启动监视器
        sched.start();
    }

    private static void test2() throws Exception {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = JobBuilder.newJob(Job2.class).withIdentity("job2", "group2").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();
        sched.scheduleJob(job, trigger);
        sched.start();
    }

    private static void test3() throws Exception {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = JobBuilder.newJob(Job3.class).withIdentity("job3", "group3").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger3", "group3").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
        sched.scheduleJob(job, trigger);
        sched.start();
    }

    private static void test4() throws Exception {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        Date runTime = DateBuilder.evenSecondDate(new Date());
        Date endTime = DateBuilder.evenMinuteDate(new Date());
        JobDetail job = JobBuilder.newJob(Job4.class).withIdentity("job4", "group4").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger4", "group4").startAt(runTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .endAt(endTime).build();
        sched.scheduleJob(job, trigger);
        sched.start();
    }

    private static void test5() throws Exception {
        JobKey jobKey = new JobKey("dummyJobName", "group5");
        JobDetail job = JobBuilder.newJob(Job5.class).withIdentity(jobKey).build();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName", "group5").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        // Listener attached to jobKey
        scheduler.getListenerManager().addJobListener(new Job5Listener(), KeyMatcher.keyEquals(jobKey));

        // Listener attached to group named "group 1" only.
        // scheduler.getListenerManager().addJobListener(
        // new HelloJobListener(), GroupMatcher.jobGroupEquals("group1")
        // );

        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

    private static void test6() throws Exception {
        JobKey jobKeyA = new JobKey("job6A", "group6");
        JobDetail jobA = JobBuilder.newJob(Job6A.class).withIdentity(jobKeyA).build();

        JobKey jobKeyB = new JobKey("job6B", "group6");
        JobDetail jobB = JobBuilder.newJob(Job6B.class).withIdentity(jobKeyB).build();

        JobKey jobKeyC = new JobKey("job6C", "group6");
        JobDetail jobC = JobBuilder.newJob(Job6C.class).withIdentity(jobKeyC).build();

        Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName1", "group6").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();

        Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName2", "group6").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();

        Trigger trigger3 = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName3", "group6").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        scheduler.start();
        scheduler.scheduleJob(jobA, trigger1);
        scheduler.scheduleJob(jobB, trigger2);
        scheduler.scheduleJob(jobC, trigger3);
    }

    private static void test7() throws Exception {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        for (String groupName : scheduler.getJobGroupNames()) {
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                String jobName = jobKey.getName();
                String jobGroup = jobKey.getGroup();
                List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                Date nextFireTime = triggers.get(0).getNextFireTime();
                System.out.println("[jobName] : " + jobName + " [groupName] : " + jobGroup + " - " + nextFireTime);
            }
        }
    }

    private static void test8() throws Exception {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        Date runTime = DateBuilder.evenSecondDate(new Date());
        JobDetail job = JobBuilder.newJob(Job8.class).withIdentity("job8", "group8").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger8", "group8").startAt(runTime).build();
        sched.scheduleJob(job, trigger);
        sched.start();

        sched.triggerJob(new JobKey("job8", "group8"));
        sched.triggerJob(new JobKey("job8", "group8"));
    }

    private static void test9() throws Exception {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        Date runTime = DateBuilder.evenSecondDate(new Date());
        JobDetail job = JobBuilder.newJob(Job9.class).withIdentity("job9", "group9").usingJobData("jobSays", "Hello Quartz!").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger9", "group9").startAt(runTime).build();
        sched.scheduleJob(job, trigger);
        sched.start();
    }

    private static void test10() throws Exception {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = JobBuilder.newJob(Job10.class).withIdentity("job10", "group10").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger10", "group10").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();
        sched.scheduleJob(job, trigger);
        sched.start();

        // removes the given trigger
        sched.unscheduleJob(new TriggerKey("job10", "group10"));
        // removes all triggers to the given job
        sched.deleteJob(new JobKey("job10", "group10"));
    }

    private static void test11() throws Exception {
        JobKey jobKey11A = new JobKey("job6A", "group6");
        JobDetail job11A = JobBuilder.newJob(Job11A.class).withIdentity(jobKey11A).build();

        JobKey jobKey11B = new JobKey("Job11B", "group6");
        JobDetail job11B = JobBuilder.newJob(Job11B.class).withIdentity(jobKey11B).build();

        Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName1", "group6").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        scheduler.start();
        scheduler.scheduleJob(job11A, trigger1);
        scheduler.scheduleJob(job11B, trigger1);
    }

}
