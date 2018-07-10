/**
 * 项目名称：quickstart-web 
 * 文件名：MyServer.java
 * 版本信息：
 * 日期：2016年12月29日
 * Copyright asiainfo Corporation 2016
 * 版权所有 *
 */
package com.quickstart.test.socket.object;

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
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class MyGzipServer {

    private final static Logger logger = Logger.getLogger(MyServer.class.getName());

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10001);

        while (true) {
            Socket socket = server.accept();
            socket.setSoTimeout(10 * 1000);
            invoke(socket);
        }
    }

    private static void invoke(final Socket socket) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                GZIPInputStream gzipis = null;
                ObjectInputStream ois = null;
                GZIPOutputStream gzipos = null;
                ObjectOutputStream oos = null;

                try {
                    gzipis = new GZIPInputStream(socket.getInputStream());
                    ois = new ObjectInputStream(gzipis);
                    gzipos = new GZIPOutputStream(socket.getOutputStream());
                    oos = new ObjectOutputStream(gzipos);

                    Object obj = ois.readObject();
                    User user = (User) obj;
                    System.out.println("user: " + user.getName() + "/" + user.getPassword());

                    user.setName(user.getName() + "_new");
                    user.setPassword(user.getPassword() + "_new");

                    oos.writeObject(user);
                    oos.flush();
                    gzipos.finish();
                } catch (IOException ex) {
                    logger.log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
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
        }).start();
    }
}
