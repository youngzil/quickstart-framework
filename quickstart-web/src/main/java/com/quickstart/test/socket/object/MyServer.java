/**
 * 项目名称：quickstart-web 
 * 文件名：MyServer.java
 * 版本信息：
 * 日期：2016年12月29日
 * Copyright asiainfo Corporation 2016
 * 版权所有 *
 */
package com.quickstart.test.socket.object;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * MyServer 
 *  
 * @author：yangzl@asiainfo.com
 * @2016年12月29日 下午3:29:02 
 * @version 1.0
 */
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServer {

    private final static Logger logger = Logger.getLogger(MyServer.class.getName());

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10001);

        while (true) {
            Socket socket = server.accept();
            invoke(socket);
        }
    }

    private static void invoke(final Socket socket) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                ObjectInputStream is = null;
                ObjectOutputStream os = null;
                try {
                    is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    os = new ObjectOutputStream(socket.getOutputStream());

                    Object obj = is.readObject();
                    User user = (User) obj;
                    System.out.println("user: " + user.getName() + "/" + user.getPassword());

                    user.setName(user.getName() + "_new");
                    user.setPassword(user.getPassword() + "_new");

                    os.writeObject(user);
                    os.flush();
                } catch (IOException ex) {
                    logger.log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    logger.log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        is.close();
                    } catch (Exception ex) {
                    }
                    try {
                        os.close();
                    } catch (Exception ex) {
                    }
                    try {
                        socket.close();
                    } catch (Exception ex) {
                    }
                }
            }
        }).start();
    }
}
