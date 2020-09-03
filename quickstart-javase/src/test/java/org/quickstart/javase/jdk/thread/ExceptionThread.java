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
public class ExceptionThread implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        try {
            ExecutorService service = Executors.newCachedThreadPool();
            service.execute(new ExceptionThread());
        } catch (Exception e) {
            System.out.println("eeeee");
        }
    }
}
