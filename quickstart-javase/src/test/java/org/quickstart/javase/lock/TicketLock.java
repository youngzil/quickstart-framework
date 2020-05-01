/**
 * 项目名称：quickstart-javase 
 * 文件名：TicketLock.java
 * 版本信息：
 * 日期：2017年7月9日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TicketLock Ticket锁主要解决的是访问顺序的问题，主要的问题是在多核cpu上 每次都要查询一个serviceNum 服务号，影响性能（必须要到主内存读取，并阻止其他cpu修改）。 肯定是按照顺序获取锁
 * 
 * @author：youngzil@163.com
 * @2017年7月9日 下午4:00:12
 * @version 1.0
 */
public class TicketLock {
    private static AtomicInteger serviceNum = new AtomicInteger();
    private static AtomicInteger ticketNum = new AtomicInteger();
    private static final ThreadLocal<Integer> LOCAL = new ThreadLocal<Integer>();

    public static void lock() {
        int myticket = ticketNum.getAndIncrement();
        LOCAL.set(myticket);
        while (myticket != serviceNum.get()) {
        }

    }

    public static void unlock() {
        int myticket = LOCAL.get();
        serviceNum.compareAndSet(myticket, myticket + 1);
    }

    public static void main(String[] args) {
        TicketLock.lock();

        System.out.println("hahaha");

        TicketLock.unlock();
    }
}
