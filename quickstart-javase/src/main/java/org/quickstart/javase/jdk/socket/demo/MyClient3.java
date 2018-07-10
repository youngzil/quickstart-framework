/**
 * 项目名称：quickstart-javase 
 * 文件名：MyClient3.java
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

/**
 * MyClient3
 * 
 * @author：yangzl@asiainfo.com
 * @2017年8月10日 下午11:36:42
 * @since 1.0
 */
public class MyClient3 {
    private final static Logger logger = Logger.getLogger(MyClient3.class.getName());

    public static void main(String[] args) throws Exception {
        // 首先运行Server类，然后运行Client类，就可以分别在Server端和Client端控制台看到接收到的User对象实例了。
        for (int i = 0; i < 100; i++) {
            Socket socket = null;
            ObjectOutputStream os = null;
            ObjectInputStream is = null;

            try {
                socket = new Socket("localhost", 10000);

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
