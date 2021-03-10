/**
 * 项目名称：quickstart-javase 文件名：MDCTest.java 版本信息： 日期：2017年9月18日 Copyright yangzl Corporation 2017 版权所有 *
 */
package org.quickstart.logging.logback;

import org.slf4j.MDC;

/**
 * MDCTest
 *
 * @author：youngzil@163.com
 * @2017年9月18日 下午9:11:34
 * @since 1.0
 */
public class MDCTest extends Thread {
    
    private int i;

    public MDCTest() {
    }

    public MDCTest(int i) {
        this.i = i;

    }

    public void run() {

        System.out.println("线程序号--》" + i);
        MDC.put("username", i + "");
        for (int j = 0; j < 20; j++) {
            System.out.println("aaa" + i);

            new Thread(new Runnable() {

                @Override
                public void run() {
                    System.out.println(i + "的子线程的值为：" + MDC.get("username"));
                    MDC.put("username", "-u"); // 未生效，不知道为什么
                }
            }).start();

            if (j == 10) {
                try {
                    this.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("run thread: " + i + "     " + MDC.get("username"));
    }

    public static void main(String args[]) throws InterruptedException {
        MDCTest t1 = new MDCTest(1);
        t1.start();
        MDCTest t2 = new MDCTest(2);
        t2.start();
    }

}
