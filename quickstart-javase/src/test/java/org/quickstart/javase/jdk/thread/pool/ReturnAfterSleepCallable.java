package org.quickstart.javase.jdk.thread.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/25 18:07
 * @version v1.0
 */
public class ReturnAfterSleepCallable implements Callable<Integer> {
    private int sleepSeconds;

    private int returnValue;

    public ReturnAfterSleepCallable(int sleepSeconds, int returnValue) {
        this.sleepSeconds = sleepSeconds;
        this.returnValue = returnValue;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("begin to execute.");

        TimeUnit.SECONDS.sleep(sleepSeconds);

        System.out.println("end to execute.");

        return returnValue;
    }
}