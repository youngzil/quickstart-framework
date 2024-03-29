/**
 * 项目名称：quickstart-javase 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.network.io.aio;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.quickstart.javase.network.io.nio.Client;

/**
 * Test
 * 
 * https://blog.csdn.net/anxpp/article/details/51512200
 * 
 * @author：youngzil@163.com
 * @2018年5月10日 下午12:38:39
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
        /*System.out.println("请输入请求消息：");
        Scanner scanner = new Scanner(System.in);
        while (Client.sendMsg(scanner.nextLine()));*/

        // 避免客户端sendMsg时候还未建立连接，抛出java.nio.channels.NotYetConnectedException
        Thread.sleep(100);

        char operators[] = {'+', '-', '*', '/'};
        Random random = new Random(System.currentTimeMillis());
        while (true) {
            // 随机产生算术表达式
            String expression = random.nextInt(10) + "" + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
            Client.sendMsg(expression);
            try {
                // Thread.currentThread().sleep(random.nextInt(1000));
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
