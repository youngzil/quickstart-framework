/**
 * 项目名称：quickstart-javase 
 * 文件名：ThreadJoinTest.java
 * 版本信息：
 * 日期：2018年12月12日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk;

/**
 * ThreadJoinTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年12月12日 下午3:44:32
 * @since 1.0
 */
public class ThreadJoinTest extends Thread {
    private final String name;

    public ThreadJoinTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100; i++)
            System.err.println(name + "\t" + i);
    }

    public static void main(String[] args) throws Exception {
        ThreadJoinTest t = new ThreadJoinTest("t1");
        ThreadJoinTest t2 = new ThreadJoinTest("t2");
        t.start();
        t.join();
        t2.start();
    }

}
