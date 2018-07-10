/**
 * 项目名称：quickstart-javase 文件名：MyClient2.java 版本信息： 日期：2017年8月10日 Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.socket.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * MyClient2
 * 
 * @author：yangzl@asiainfo.com
 * @2017年8月10日 下午11:31:26
 * @since 1.0
 */
public class MyClient2 {

    public static void main(String[] args) throws Exception {

        // 测试，首先运行MyServer类，然后运行两个MyClient类，然后分别在每个MyClient的提示符下输入字符串，就可以看到Server可以分别接收处理每个Client的请求了。
        // Client也和Server端类似，同样使用ObjectOutputStream和ObjectInputStream来处理，
        Socket socket = new Socket("localhost", 10000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String msg = reader.readLine();
            out.println(msg);
            out.flush();
            if (msg.equals("bye")) {
                break;
            }
            System.out.println(in.readLine());
        }
        socket.close();
    }

}
