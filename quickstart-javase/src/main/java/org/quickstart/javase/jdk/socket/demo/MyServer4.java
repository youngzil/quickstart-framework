/**
 * 项目名称：quickstart-javase 文件名：MyServer4.java 版本信息： 日期：2017年8月10日 Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.socket.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * MyServer4
 * 
 * @author：yangzl@asiainfo.com
 * @2017年8月10日 下午11:39:20
 * @since 1.0
 */
public class MyServer4 {
    // 在有些情况下比如网络环境不好或者对象比较大的情况下需要把数据对象进行压缩然后在传输，此时就需要压缩这些对象流，此时就可以GZIPInputStream和GZIPOutputStream来处理一下socket的InputStream和OutputStream。
    // 在Server端使用，socket的InputStream首先被包装成GZIPInputStream，然后又被包装成ObjectInputStream，而socket的OutputStream首先被包装成GZIPOutputStream，然后又被包装成ObjectOutputStream，
    private final static Logger logger = Logger.getLogger(MyServer4.class.getName());

    // 首先运行Server类，然后运行Client类，就可以分别在Server端和Client端控制台看到接收到的User对象实例了。
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10000);

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
