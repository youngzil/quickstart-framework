package com.rensanning.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job2 implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Job 2 is runing！ " + new Date());
    }
}
