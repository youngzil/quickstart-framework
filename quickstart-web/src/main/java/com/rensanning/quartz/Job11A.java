package com.rensanning.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job11A implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            System.out.println("Job11 A is runing！ " + new Date());
        } catch (Exception e) {

            try {
                // sleep for 1 mins
                Thread.sleep(60000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            JobExecutionException e2 = new JobExecutionException(e);
            // fire it again
            e2.setRefireImmediately(true);
            throw e2;
        }
    }

}
