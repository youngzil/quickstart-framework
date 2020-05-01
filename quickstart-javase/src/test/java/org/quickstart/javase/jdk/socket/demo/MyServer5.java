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
public class MyServer5 {
    // 对于一些有安全要求的应用就需要加密传输的数据，此时就需要用到SSLSocket了。
    // SSL
    // Server类，这里需要用到ServerSocketFactory类来创建SSLServerSocket类实例，然后在通过SSLServerSocket来获取SSLSocket实例，
    // 这里考虑到面向对象中的面向接口编程的理念，所以代码中并没有出现SSLServerSocket和SSLSocket，而是用了他们的父类ServerSocket和Socket。
    // 在获取到ServerSocket和Socket实例以后，剩下的代码就和不使用加密方式一样了。

    /**
     * 代码写完了，下面就需要产生keystore文件了，运行下面的命令
     * 
     * keytool -genkey -alias mysocket -keyalg RSA -keystore mysocket.jks 在提示输入项中，密码项自己给定，其它都不改直接回车，这里我使用的密码是“mysocket”。
     * 
     * 运行Server
     * 
     * java -Djavax.net.ssl.keyStore=mysocket.jks -Djavax.net.ssl.keyStorePassword=mysocket com.googlecode.garbagecan.test.socket.ssl.MyServer
     * 
     * 运行Client
     * 
     * java -Djavax.net.ssl.trustStore=mysocket.jks -Djavax.net.ssl.trustStorePassword=mysocket com.googlecode.garbagecan.test.socket.ssl.MyClient
     */
    private final static Logger logger = Logger.getLogger(MyServer5.class.getName());

    public static void main(String[] args) {
        try {
            ServerSocketFactory factory = SSLServerSocketFactory.getDefault();
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
