/**
 * 项目名称：quickstart-javase 
 * 文件名：ServerHandler.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.network.io.bio;

/**
 * ServerHandler 
 *  
 * @author：youngzil@163.com
 * @2018年5月10日 上午11:00:29 
 * @since 1.0
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端线程
 * 
 * @author yangtao__anxpp.com 用于处理一个客户端的Socket链路
 */
public class ServerHandler implements Runnable {
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String expression;
            String result;
            while (true) {
                // 通过BufferedReader读取一行
                // 如果已经读到输入流尾部，返回null,退出循环
                // 如果得到非空值，就尝试计算结果并返回
                if ((expression = in.readLine()) == null)
                    break;
                System.out.println("Server服务器收到消息：" + expression);
                try {
                    result = Calculator.cal(expression).toString();
                } catch (Exception e) {
                    result = "计算错误：" + e.getMessage();
                }
                out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 一些必要的清理工作
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
