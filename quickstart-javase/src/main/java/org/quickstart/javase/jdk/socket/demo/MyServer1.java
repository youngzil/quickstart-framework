/**
 * 项目名称：quickstart-javase 
 * 文件名：MyServer.java
 * 版本信息：
 * 日期：2017年8月10日
 * Copyright asiainfo Corporation 2017
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
 * MyServer Server只能接受一个Client请求，当第一个Client连接后就占据了这个位置，后续Client不能再继续连接，
 * 
 * @author：yangzl@asiainfo.com
 * @2017年8月10日 下午11:20:40
 * @since 1.0
 */
public class MyServer1 {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10000);
        Socket socket = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        while (true) {
            String msg = in.readLine();
            System.out.println(msg);
            out.println("Server received " + msg);
            out.flush();
            if (msg.equals("bye")) {
                break;
            }
        }
        socket.close();
    }
}
