package com.rensanning.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job5 implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Job 5 is runing！ " + new Date());
        // Throw exception for testing
        throw new JobExecutionException("Testing Exception");
    }
}
