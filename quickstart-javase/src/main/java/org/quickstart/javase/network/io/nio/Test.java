/**
 * 项目名称：quickstart-javase 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.network.io.nio;

import java.util.Random;
import java.util.Scanner;

/**
 * Test
 * 
 * https://blog.csdn.net/anxpp/article/details/51512200
 * 
 * @author：youngzil@163.com
 * @2018年5月10日 下午12:16:58
 * @since 1.0
 */
public class Test {
    // 测试主方法
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        // 运行服务器
        Server.start();
        // 避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
        // 运行客户端
        Client.start();
        // while (Client.sendMsg(new Scanner(System.in).nextLine()));

        // 避免客户端sendMsg时候还未建立连接，抛出java.nio.channels.NotYetConnectedException
        Thread.sleep(100);

        char operators[] = {'+', '-', '*', '/'};
        Random random = new Random(System.currentTimeMillis());
        while (true) {
            // 随机产生算术表达式
            String expression = random.nextInt(10) + "" + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
            Client.sendMsg(expression);
            try {
                Thread.currentThread().sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
