/**
 * 项目名称：quickstart-javase 
 * 文件名：MyClient5.java
 * 版本信息：
 * 日期：2017年8月10日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.socket.demo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.SocketFactory;

/**
 * MyClient5
 * 
 * @author：youngzil@163.com
 * @2017年8月10日 下午11:55:19
 * @since 1.0
 */
public class MyClient6 {

    private final static Logger logger = Logger.getLogger(MyClient6.class.getName());

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            Socket socket = null;
            ObjectOutputStream os = null;
            ObjectInputStream is = null;

            try {
                SocketFactory factory = SocketFactory.getDefault();

                // socket = factory.createSocket();
                socket = factory.createSocket("localhost", 10000);

                os = new ObjectOutputStream(socket.getOutputStream());
                User user = new User("user_" + i, "password_" + i);
                os.writeObject(user);
                os.flush();

                is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                Object obj = is.readObject();
                if (obj != null) {
                    user = (User) obj;
                    System.out.println("user: " + user.getName() + "/" + user.getPassword());
                }
            } catch (IOException ex) {
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
    }
}
