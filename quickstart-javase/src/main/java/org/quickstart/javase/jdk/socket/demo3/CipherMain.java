/**
 * 项目名称：quickstart-javase 
 * 文件名：SocketReq.java
 * 版本信息：
 * 日期：2017年8月11日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.socket.demo3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * CipherMain
 * 
 * @author：youngzil@163.com
 * @2017年8月11日 上午9:46:16
 * @since 1.0
 */
public class CipherMain {
    public static void main(String[] args) {

        CipherMain cm = new CipherMain();
        // cm.test_post();
        cm.test_get();
    }

    void test_post() {

        SocketReq socketreq = new SocketReq();
        try {
            byte[] getret = socketreq.get("https://192.168.111.30/Mycloud/index.html", "1", "utf-8");
            String getstrRead = new String(getret, "utf-8");
            System.out.println(getstrRead);
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void test_get() {

        SocketReq socketreq = new SocketReq();
        try {
            byte[] ret = socketreq.post("https://192.168.111.30/Mycloud/index.html", "1", "utf-8");

            String strRead = new String(ret, "utf-8");

            System.out.println(strRead);

        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
