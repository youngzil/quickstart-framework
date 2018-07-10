/**
 * 项目名称：quickstart-javase 
 * 文件名：EnvironmentInfo.java
 * 版本信息：
 * 日期：2017年6月30日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * EnvironmentInfo
 * 
 * @author：yangzl@asiainfo.com
 * @2017年6月30日 下午4:55:00
 * @version 1.0
 */
public class EnvironmentInfo {

    public static void main(String[] args) {
        
        String defaultValue = "<NA>";

        String hostName = defaultValue;
        try {
            hostName = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
        }
        System.out.println("host.name = " + hostName);
        System.out.println("java.version = " + System.getProperty("java.version", defaultValue));
        System.out.println("java.vendor = " + System.getProperty("java.vendor", defaultValue));
        System.out.println("java.home = " + System.getProperty("java.home", defaultValue));
        System.out.println("java.class.path = " + System.getProperty("java.class.path", defaultValue));
        System.out.println("java.library.path = " + System.getProperty("java.library.path", defaultValue));
        System.out.println("java.io.tmpdir = " + System.getProperty("java.io.tmpdir", defaultValue));
        System.out.println("java.compiler = " + System.getProperty("java.compiler", defaultValue));
        System.out.println("os.name = " + System.getProperty("os.name", defaultValue));
        System.out.println("os.arch = " + System.getProperty("os.arch", defaultValue));
        System.out.println("os.version = " + System.getProperty("os.version", defaultValue));
        System.out.println("user.name = " + System.getProperty("user.name", defaultValue));
        System.out.println("user.dir = " + System.getProperty("user.dir", defaultValue));

        // Get memory information.
        Runtime runtime = Runtime.getRuntime();
        int mb = 1024 * 1024;
        System.out.println("os.memory.free = " + Long.toString(runtime.freeMemory() / mb) + "MB");
        System.out.println("os.memory.max = " + Long.toString(runtime.maxMemory() / mb) + "MB");
        System.out.println("os.memory.total = " + Long.toString(runtime.totalMemory() / mb) + "MB");
    }

}
