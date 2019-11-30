/**
 * 项目名称：quickstart-javase 
 * 文件名：Task1.java
 * 版本信息：
 * 日期：2017年7月23日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.time.timer;

/**
 * Task1 
 *  
 * @author：youngzil@163.com
 * @2017年7月23日 下午8:35:28 
 * @version 2.0
 */
/**
 * 普通thread 这是最常见的，创建一个thread，然后让它在while循环里一直运行着， 通过sleep方法来达到定时任务的效果。这样可以快速简单的实现，代码如下：
 * 
 * @author GT
 * 
 */
public class Task1 {
    public static void main(String[] args) {
        // run in a second
        final long timeInterval = 1000;
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    // ------- code for task to run
                    System.out.println("Hello !!");
                    // ------- ends here
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
