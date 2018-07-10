package com.rensanning.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job11B implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        int count = dataMap.getIntValue("count");

        // allow 5 retries
        if (count >= 5) {
            JobExecutionException e = new JobExecutionException("Retries exceeded");
            // make sure it doesn't run again
            e.setUnscheduleAllTriggers(true);
            throw e;
        }

        try {
            System.out.println("Job11 B is runing！ " + new Date());

            // reset counter back to 0
            dataMap.putAsString("count", 0);
        } catch (Exception e) {
            count++;
            dataMap.putAsString("count", count);
            JobExecutionException e2 = new JobExecutionException(e);

            try {
                // sleep for 1 mins
                Thread.sleep(60000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            // fire it again
            e2.setRefireImmediately(true);
            throw e2;
        }
    }

}
