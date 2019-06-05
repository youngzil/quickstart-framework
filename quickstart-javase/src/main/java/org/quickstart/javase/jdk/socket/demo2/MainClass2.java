/**
 * 项目名称：quickstart-javase 文件名：MainClass2.java 版本信息： 日期：2017年8月11日 Copyright yangzl Corporation
 * 2017 版权所有 *
 */
package org.quickstart.javase.jdk.socket.demo2;

import java.io.IOException;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 * MainClass2
 * 
 * @author：youngzil@163.com
 * @2017年8月11日 上午9:39:53
 * @since 1.0
 */
public class MainClass2 {
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);

        try {
            System.out.println("Locating server socket factory for SSL...");
            SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

            System.out.println("Creating a server socket on port " + port);
            SSLServerSocket serverSocket = (SSLServerSocket) factory.createServerSocket(port);

            String[] suites = serverSocket.getSupportedCipherSuites();
            System.out.println("Support cipher suites are:");
            for (int i = 0; i < suites.length; i++) {
                System.out.println(suites[i]);
            }
            serverSocket.setEnabledCipherSuites(suites);

            System.out.println("Support protocols are:");
            String[] protocols = serverSocket.getSupportedProtocols();
            for (int i = 0; i < protocols.length; i++) {
                System.out.println(protocols[i]);
            }

            System.out.println("Waiting for client...");
            SSLSocket socket = (SSLSocket) serverSocket.accept();

            System.out.println("Starting handshake...");
            socket.startHandshake();

            System.out.println("Just connected to " + socket.getRemoteSocketAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
