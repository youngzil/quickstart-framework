/**
 * 项目名称：quickstart-javase 
 * 文件名：SocketReq.java
 * 版本信息：
 * 日期：2017年8月11日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.socket.demo3;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * SocketReq
 * 
 * @author：yangzl@asiainfo.com
 * @2017年8月11日 上午9:43:35
 * @since 1.0
 */
public class SocketReq {
    private int port;
    private String host;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public SocketReq() {

    }

    public SocketReq(String host, int port) {
        this.host = host;
        this.port = port;

        try {
            socket = (SSLSocket) ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(this.host, this.port);
        } catch (UnknownHostException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // SSLSocket
        // try {
        // socket = (SSLSocket) factory.createSocket("https://192.168.111.30",
        // 443);
        // } catch (UnknownHostException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        // socket = new Socket();

    }

    public void sendGet(String path) throws IOException {

        // 自定义的管理器
        X509TrustManager xtm = new TrustAnyTrustManager();
        TrustManager mytm[] = {xtm};
        // 得到上下文
        SSLContext ctx;
        try {
            ctx = SSLContext.getInstance("SSL");

            // 初始化

            ctx.init(null, mytm, null);

            // 获得工厂
            SSLSocketFactory factory = ctx.getSocketFactory();

            // 从工厂获得Socket连接
            Socket socket = factory.createSocket(this.host, 443);

            // 剩下的就和普通的Socket操作一样了
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.write("GET " + path + " HTTP/1.0\n\n");
            out.flush();
            System.out.println("start   work!");
            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
                sb.append(line + "\n");
            }
            out.close();
            in.close();
            String outcome = sb.toString();
            System.out.println(outcome);

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void sendPost(String path) throws IOException {
        // String path = "/zhigang/postDemo.php";
        String data = URLEncoder.encode("name", "utf-8") + "=" + URLEncoder.encode("gloomyfish", "utf-8") + "&" + URLEncoder.encode("age", "utf-8") + "=" + URLEncoder.encode("32", "utf-8");
        // String data = "name=zhigang_jia";
        SocketAddress dest = new InetSocketAddress(this.host, this.port);
        socket.connect(dest);
        OutputStreamWriter streamWriter = new OutputStreamWriter(socket.getOutputStream(), "utf-8");
        bufferedWriter = new BufferedWriter(streamWriter);

        bufferedWriter.write("POST " + path + " HTTP/1.1\r\n");
        bufferedWriter.write("Host: " + this.host + "\r\n");
        bufferedWriter.write("Content-Length: " + data.length() + "\r\n");
        bufferedWriter.write("Content-Type: application/x-www-form-urlencoded\r\n");
        bufferedWriter.write("\r\n");
        bufferedWriter.write(data);
        bufferedWriter.flush();
        bufferedWriter.write("\r\n");
        bufferedWriter.flush();

        BufferedInputStream streamReader = new BufferedInputStream(socket.getInputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(streamReader, "utf-8"));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line.getBytes("utf-8"));
        }
        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
    }

    public static byte[] post(String url, String content, String charset) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        byte[] ret = null;

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] {new TrustAnyTrustManager()}, new java.security.SecureRandom());

            URL console = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-type", "multipart/form-data;boundary=*****");
            // 在与服务器连接之前，设置一些网络参数
            conn.setConnectTimeout(10000);

            conn.setDoOutput(true);
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());

            out.write(content.getBytes(charset));
            // 刷新、关闭
            out.flush();
            out.close();
            InputStream is = conn.getInputStream();
            try {
                if (is != null) {
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = is.read(buffer)) != -1) {
                        outStream.write(buffer, 0, len);
                    }
                    // is.close();
                    ret = outStream.toByteArray();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != is) {
                    is.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static byte[] get(String url, String content, String charset) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        byte[] ret = null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] {new TrustAnyTrustManager()}, new java.security.SecureRandom());

            URL console = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestMethod("GET");

            // conn.setRequestProperty("Content-type",
            // "multipart/form-data;boundary=*****");
            // 在与服务器连接之前，设置一些网络参数
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            // conn.setDoOutput(true);
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());

            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            conn.connect();

            InputStream is = conn.getInputStream();

            try {
                if (is != null) {
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = is.read(buffer)) != -1) {
                        outStream.write(buffer, 0, len);
                    }
                    ret = outStream.toByteArray();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != is) {
                    is.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 关闭输入流

        return ret;
    }

    private static class TrustAnyTrustManager implements X509TrustManager {
        TrustAnyTrustManager() {
            // 这里可以进行证书的初始化操作
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // System.out.println("检查客户端的可信任状态...");
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // System.out.println("检查服务器的可信任状态");
        }

        public X509Certificate[] getAcceptedIssuers() {
            // System.out.println("获取接受的发行商数组...");
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
