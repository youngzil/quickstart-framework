/**
 * 项目名称：quickstart-javase 
 * 文件名：Producer.java
 * 版本信息：
 * 日期：2017年7月26日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example.thread;

/**
 * Producer 生产者－消费者模型之生产者
 * 
 * @author：youngzil@163.com
 * @2017年7月26日 下午7:37:10
 * @version 2.0
 */
public class Producer extends Thread {

    private int needNum;// 每次要生产产品的数量
    private Godown godown;// 仓库

    /**
     * 
     * 创建一个新的实例 Producer.
     * 
     * @param needNum
     * @param godown
     */
    public Producer(int needNum, Godown godown) {
        this.needNum = needNum;
        this.godown = godown;
    }

    /**
     * 重写 java.lang.Thread 的 run 方法
     */
    public void run() {
        this.godown.produce(this.needNum);
    }
}
