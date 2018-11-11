/**
 * 项目名称：quickstart-web 
 * 文件名：MyServer.java
 * 版本信息：
 * 日期：2016年12月29日
 * Copyright asiainfo Corporation 2016
 * 版权所有 *
 */
package com.quickstart.test.socket.multithread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

/**
 * MyServer
 * 
 * @author：youngzil@163.com
 * @2016年12月29日 下午3:21:07
 * @version 1.0
 */
public class MyServer {
    public static void main(String[] args) throws IOException {
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
