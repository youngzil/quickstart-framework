package org.quickstart.javase.jdk.thread;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/4/25 14:53
 */
public class MyThread4 extends Thread {
    public void run(){
        super.run();

        try {
            System.out.println("线程开始。。。");
            Thread.sleep(200000);
            System.out.println("线程结束。");
        } catch (InterruptedException e) {
            System.out.println("在沉睡中被停止, 进入catch， 调用isInterrupted()方法的结果是：" + this.isInterrupted());
            e.printStackTrace();
        }

    }
}
