/**
 * 项目名称：quickstart-javase 
 * 文件名：DeadLockApp.java
 * 版本信息：
 * 日期：2017年7月26日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example.ThreadDeadLock;

/**
 * DeadLockApp 死锁程序入口
 * 
 * @author：youngzil@163.com
 * @2017年7月26日 下午7:43:56
 * @version 2.0
 */
public class DeadLockApp {
    public static void main(String[] args) {

        /**
         * 死锁演示线程初始化
         */
        ResourceManager resourceManager = new ResourceManager();
        CustomizeThread customizedThread0 = new CustomizeThread(resourceManager, 1, 2);
        CustomizeThread customizedThread1 = new CustomizeThread(resourceManager, 2, 4);

        /**
         * 死锁演示线程启动
         */
        customizedThread0.start();
        customizedThread1.start();
    }
}
