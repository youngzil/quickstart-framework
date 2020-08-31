package org.quickstart.javase.jdk.thread;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/30 22:39
 * @version v1.0
 */
public class InterruptTest implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new InterruptTest(), "InterruptTest");
        //start thread
        testThread.start();
        Thread.sleep(1000);
        //interrupt thread
        testThread.interrupt();

        System.out.println("main end");

    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Yes,I am interruted,but I am still running");
                return;
            } else {
                System.out.println("not yet interrupted");
            }
        }
    }
}
