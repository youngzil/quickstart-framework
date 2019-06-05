/**
 * 项目名称：quickstart-javase 
 * 文件名：TimerTest.java
 * 版本信息：
 * 日期：2017年7月23日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerTest
 * 
 * @author：youngzil@163.com
 * @2017年7月23日 下午8:31:42
 * @version 2.0
 */
public class TimerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.schedule(new OneTask(1), 5000);// 5秒后启动任务

        OneTask secondTask = new OneTask(2);
        timer.schedule(secondTask, 1000, 3000);// 1秒后启动任务,以后每隔3秒执行一次线程

        Date date = new Date();
        timer.schedule(new OneTask(3), new Date(date.getTime() + 1000));// 以date为参数，指定某个时间点执行线程

        // timer.cancel();
        // secondTask.cancel();
        System.out.println("end in main thread...");
    }

}


class OneTask extends TimerTask {

    private int id;

    public OneTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("线程" + id + ":  正在 执行。。");
        // System.gc();
    }
}
