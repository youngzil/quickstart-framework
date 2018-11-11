/**
 * 项目名称：quickstart-javase 文件名：MainClass3.java 版本信息： 日期：2017年8月11日 Copyright asiainfo Corporation
 * 2017 版权所有 *
 */
package org.quickstart.javase.jdk.socket.demo2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.Principal;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * MainClass3
 * 
 * @author：youngzil@163.com
 * @2017年8月11日 上午9:40:38
 * @since 1.0
 */
public class MainClass3 {
    public static void main(String[] args) throws Exception {

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        String hostName = "hostName";
        String fileName = "fileName";

        SSLSocket sslsock = (SSLSocket) factory.createSocket(hostName, 443);

        SSLSession session = sslsock.getSession();
        X509Certificate cert;
        try {
            cert = (X509Certificate) session.getPeerCertificates()[0];
        } catch (SSLPeerUnverifiedException e) {
            System.err.println(session.getPeerHost() + " did not present a valid certificate.");
            return;
        }

        System.out.println(session.getPeerHost() + " has presented a certificate belonging to:");
        Principal p = cert.getSubjectDN();
        System.out.println("\t[" + p.getName() + "]");
        System.out.println("The certificate bears the valid signature of:");
        System.out.println("\t[" + cert.getIssuerDN().getName() + "]");

        System.out.print("Do you trust this certificate (y/n)? ");
        System.out.flush();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        if (Character.toLowerCase(console.readLine().charAt(0)) != 'y')
            return;

        PrintWriter out = new PrintWriter(sslsock.getOutputStream());

        out.print("GET " + fileName + " HTTP/1.0\r\n\r\n");
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(sslsock.getInputStream()));
        String line;
        while ((line = in.readLine()) != null)
            System.out.println(line);

        sslsock.close();
    }
}
