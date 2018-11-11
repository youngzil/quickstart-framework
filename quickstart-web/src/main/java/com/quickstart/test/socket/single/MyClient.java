/**
 * 项目名称：quickstart-web 
 * 文件名：MyClient.java
 * 版本信息：
 * 日期：2016年12月29日
 * Copyright asiainfo Corporation 2016
 * 版权所有 *
 */
package com.quickstart.test.socket.single;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * MyClient
 * 
 * @author：youngzil@163.com
 * @2016年12月29日 下午3:11:24
 * @version 1.0
 */
public class MyClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 9000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String msg = reader.readLine();
            out.println(msg);
            out.flush();
            if (msg.equals("bye")) {
                break;
            }
            System.out.println(in.readLine());
        }
        socket.close();
    }
}
