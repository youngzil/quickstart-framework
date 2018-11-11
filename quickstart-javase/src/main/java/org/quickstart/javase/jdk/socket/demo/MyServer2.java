/**
 * 项目名称：quickstart-javase 文件名：MyServer2.java 版本信息： 日期：2017年8月10日 Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.socket.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * MyServer2 当Server没接受到一个Client连接请求之后，都把处理流程放到一个独立的线程里去运行，然后等待下一个Client连接请求，这样就不会阻塞Server端接收请求了。每个独立运行的程序在使用完Socket对象之后要将其关闭。
 * 
 * @author：youngzil@163.com
 * @2017年8月10日 下午11:31:02
 * @since 1.0
 */
public class MyServer2 {
    public static void main(String[] args) throws IOException {

        // 测试，首先运行MyServer类，然后运行两个MyClient类，然后分别在每个MyClient的提示符下输入字符串，就可以看到Server可以分别接收处理每个Client的请求了。

        ServerSocket server = new ServerSocket(10000);

        while (true) {
            Socket socket = server.accept();
            invoke(socket);
        }
    }

    private static void invoke(final Socket client) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                BufferedReader in = null;
                PrintWriter out = null;
                try {
                    in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    out = new PrintWriter(client.getOutputStream());

                    while (true) {
                        String msg = in.readLine();
                        System.out.println(msg);
                        out.println("Server received " + msg);
                        out.flush();
                        if (msg.equals("bye")) {
                            break;
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (Exception e) {
                    }
                    try {
                        out.close();
                    } catch (Exception e) {
                    }
                    try {
                        client.close();
                    } catch (Exception e) {
                    }
                }
            }
        }).start();
    }
}
