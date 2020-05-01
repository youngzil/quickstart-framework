/**
 * 项目名称：quickstart-javase 文件名：MyServer5.java 版本信息： 日期：2017年8月10日 Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.socket.demo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;

/**
 * MyServer5
 * 
 * @author：youngzil@163.com
 * @2017年8月10日 下午11:51:16
 * @since 1.0
 */
public class MyServer6 {

    private final static Logger logger = Logger.getLogger(MyServer6.class.getName());

    public static void main(String[] args) {
        try {
            ServerSocketFactory factory = ServerSocketFactory.getDefault();
            // ServerSocket server = factory.createServerSocket();
            ServerSocket server = factory.createServerSocket(10000);

            while (true) {
                Socket socket = server.accept();
                invoke(socket);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
