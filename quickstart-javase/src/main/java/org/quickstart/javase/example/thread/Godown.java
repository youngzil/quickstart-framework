/**
 * 项目名称：quickstart-javase 
 * 文件名：Godown.java
 * 版本信息：
 * 日期：2017年7月26日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example.thread;

/**
 * Godown 生产者－消费者模型之仓库
 * 
 * @author：youngzil@163.com
 * @2017年7月26日 下午7:36:30
 * @version 2.0
 */
public class Godown {

    private final int max_size = 100;// 最大库存容量
    private int curNum;// 现有库存量

    /**
     * 
     * 创建一个新的实例 Godown.
     * 
     * @param curNum
     */
    public Godown(int curNum) {
        this.curNum = curNum;
    }

    /**
     * 生产指定数目的产品
     */
    public synchronized void produce(int needNum) {
        while (true) {
            /**
             * 如果不需要生产，进入等待状态
             */
            while (this.curNum + needNum > this.max_size) {
                System.out.println(Thread.currentThread().getName() + "要生产的产品数量" + needNum + "已经超出剩余库存容量" + (this.max_size - this.curNum) + "，暂时不能进行生产任务！");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            /**
             * 满足了生产条件，进行产品生产
             */
            this.curNum += needNum;
            System.out.println(Thread.currentThread().getName() + "已经生产了" + needNum + "，现存库存量是为：" + this.curNum);

            /**
             * 唤醒在此对象监视器上等待的所有线程
             */
            this.notifyAll();
        }
    }

    /**
     * 消费指定数目的产品
     */
    public synchronized void consume(int needNum) {
        while (true) {
            /**
             * 如果不可以消费，进入等待状态
             */
            while (this.curNum < needNum) {
                System.out.println(Thread.currentThread().getName() + "要消费的产品数量" + needNum + "已经超出剩余库存量" + this.curNum + "，暂时不能进行消费！");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            /**
             * 满足了消费条件，进行产品消费
             */
            this.curNum -= needNum;
            System.out.println(Thread.currentThread().getName() + "已经消费了" + needNum + "，现存库存量是为：" + this.curNum);

            /**
             * 唤醒在此对象监视器上等待的所有线程
             */
            this.notifyAll();
        }
    }
}
