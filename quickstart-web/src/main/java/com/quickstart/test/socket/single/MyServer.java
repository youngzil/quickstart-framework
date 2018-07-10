/**
 * 项目名称：quickstart-web 
 * 文件名：MyServer.java
 * 版本信息：
 * 日期：2016年12月29日
 * Copyright asiainfo Corporation 2016
 * 版权所有 *
 */
package com.quickstart.test.socket.single;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * MyServer
 * 
 * @author：yangzl@asiainfo.com
 * @2016年12月29日 下午3:10:34
 * @version 1.0
 */
public class MyServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9000);
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
