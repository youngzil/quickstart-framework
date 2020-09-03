package org.quickstart.javase.jdk.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/9/3 19:39
 * @version v1.0
 */
public class ExceptionThread2 implements Runnable{

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        System.out.println("eh = " + t.getUncaughtExceptionHandler());

        // 手动抛出异常
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool(new HandlerThreadFactory());
        service.execute(new ExceptionThread2());
    }
}
