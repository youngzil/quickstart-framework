package org.quickstart.javase.stop.watch;

import org.springframework.util.StopWatch;
/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/6/30 00:32
 */

/**
 * @author Crunchify.com Use StopWatch to expose total running time of any Java Threads
 */

public class CrunchifySpringFrameworkStopWatch {

    public static void main(String[] args) {

        // StopWatch is a simple stop watch, allowing for timing of a number of tasks, exposing total running time and running time for each
        // named task.
        StopWatch crunchifyWatch = new StopWatch("CrunchifyThreads");
        CrunchifySpringFrameworkStopWatch crunchifyThread = new CrunchifySpringFrameworkStopWatch();

        crunchifyWatch.start("CrunchifyThread-1");
        crunchifyThread.performTask1();
        crunchifyWatch.stop();

        crunchifyWatch.start("CrunchifyThread-2");
        crunchifyThread.performTask2();
        crunchifyWatch.stop();
        System.out.println("CrunchifyThreads took total: " + crunchifyWatch.getTotalTimeSeconds() + " seconds");

        // prettyPrint() return a string with a table describing all tasks performed. For custom reporting, call getTaskInfo() and use the
        // task info directly.
        System.out.println("\n1. prettyPrint Result: " + crunchifyWatch.prettyPrint());

        // Return a short description of the total running time.
        System.out.println("2. Short Summary: " + crunchifyWatch.shortSummary());

        // Return the number of tasks timed.
        System.out.println("3. Total Task Count: " + crunchifyWatch.getTaskCount());

        // Return the name of this task.
        System.out.println("4. Last Task Name: " + crunchifyWatch.getLastTaskInfo().getTaskName());
    }

    private void performTask1() {

        Runnable myRunnable = new Runnable() {

            public void run() {
                System.out.println("Crunchify Task 1 running");
            }
        };

        Thread crunchifyThread = new Thread(myRunnable);
        crunchifyThread.start();

    }

    private void performTask2() {

        System.out.println("Crunchify Task 2 running \n");
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(2000);
                System.out.println("Running Loop # " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("");
    }

}
