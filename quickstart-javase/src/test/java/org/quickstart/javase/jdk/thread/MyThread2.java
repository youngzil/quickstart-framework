package org.quickstart.javase.jdk.thread;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/4/25 14:49
 */
public class MyThread2 extends Thread {
    public void run(){
        super.run();
        for(int i=0; i<500000; i++){
            if(this.interrupted()) {
                System.out.println("线程已经终止， for循环不再执行");
                break;
            }
            System.out.println("i="+(i+1));
        }
        System.out.println("这是for循环外面的语句，也会被执行");
    }

    public static void main(String args[]){
        Thread thread = new MyThread();
        thread.start();
        try {
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

