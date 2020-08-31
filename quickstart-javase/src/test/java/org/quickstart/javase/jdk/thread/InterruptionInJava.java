package org.quickstart.javase.jdk.thread;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/30 22:45
 * @version v1.0
 */
public class InterruptionInJava implements Runnable {
    private volatile static boolean on = false;

    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new InterruptionInJava(), "InterruptionInJava");
        //start thread
        testThread.start();
        Thread.sleep(1000);
        InterruptionInJava.on = true;
        testThread.interrupt();

        System.out.println("main end");

    }

    @Override
    public void run() {
        while (!on) {
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                System.out.println("caught exception right now: " + e);
            }
        }
    }
}
