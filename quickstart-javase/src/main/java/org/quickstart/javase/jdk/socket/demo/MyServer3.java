/**
 * 项目名称：quickstart-javase 文件名：MyServer3.java 版本信息： 日期：2017年8月10日 Copyright asiainfo Corporation 2017
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

/**
 * MyServer3
 * 
 * @author：youngzil@163.com
 * @2017年8月10日 下午11:35:35
 * @since 1.0
 */
public class MyServer3 {
    private final static Logger logger = Logger.getLogger(MyServer3.class.getName());

    // 首先运行Server类，然后运行Client类，就可以分别在Server端和Client端控制台看到接收到的User对象实例了。
    // 首先需要一个普通的对象类，由于需要序列化这个对象以便在网络上传输，所以实现java.io.Serializable接口就是必不可少的了，如下：
    // 对于Server端的代码，代码中分别使用了ObjectInputStream和ObjectOutputStream来接收和发送socket中的InputStream和OutputStream，然后转换成Java对象，
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10000);

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
