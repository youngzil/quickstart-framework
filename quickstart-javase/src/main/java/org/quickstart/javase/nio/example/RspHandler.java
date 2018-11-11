/**
 * 项目名称：quickstart-javase 
 * 文件名：RspHandler.java
 * 版本信息：
 * 日期：2017年9月20日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.nio.example;

/**
 * RspHandler
 * 
 * @author：youngzil@163.com
 * @2017年9月20日 下午10:37:54
 * @since 1.0
 */
public class RspHandler {
    private byte[] rsp = null;

    public synchronized boolean handleResponse(byte[] rsp) {
        this.rsp = rsp;
        this.notify();
        return true;
    }

    public synchronized void waitForResponse() {
        while (this.rsp == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }

        System.out.println(new String(this.rsp));
    }
}
