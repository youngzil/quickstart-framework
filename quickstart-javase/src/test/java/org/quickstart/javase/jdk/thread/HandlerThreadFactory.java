package org.quickstart.javase.jdk.thread;

import java.util.concurrent.ThreadFactory;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/9/3 19:40
 * @version v1.0
 */
public class HandlerThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread");
        Thread t = new Thread(r);
        System.out.println("created " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("ex = " + t.getUncaughtExceptionHandler());
        return t;
    }
}
