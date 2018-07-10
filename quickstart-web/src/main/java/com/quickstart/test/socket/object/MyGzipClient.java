/**
 * 项目名称：quickstart-web 
 * 文件名：MyClient.java
 * 版本信息：
 * 日期：2016年12月29日
 * Copyright asiainfo Corporation 2016
 * 版权所有 *
 */
package com.quickstart.test.socket.object;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class MyGzipClient {

    private final static Logger logger = Logger.getLogger(MyClient.class.getName());

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Socket socket = null;
            GZIPOutputStream gzipos = null;
            ObjectOutputStream oos = null;
            GZIPInputStream gzipis = null;
            ObjectInputStream ois = null;

            try {
                socket = new Socket();
                SocketAddress socketAddress = new InetSocketAddress("localhost", 10001);
                socket.connect(socketAddress, 10 * 1000);
                socket.setSoTimeout(10 * 1000);

                gzipos = new GZIPOutputStream(socket.getOutputStream());
                oos = new ObjectOutputStream(gzipos);
                User user = new User("user_" + i, "password_" + i);
                oos.writeObject(user);
                oos.flush();
                gzipos.finish();

                gzipis = new GZIPInputStream(socket.getInputStream());
                ois = new ObjectInputStream(gzipis);
                Object obj = ois.readObject();
                if (obj != null) {
                    user = (User) obj;
                    System.out.println("user: " + user.getName() + "/" + user.getPassword());
                }
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ois.close();
                } catch (Exception ex) {
                }
                try {
                    oos.close();
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
