package com.rensanning.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job10 implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Job 10 is runing！ " + new Date());
    }
}
