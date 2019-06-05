/**
 * 项目名称：quickstart-proxy 
 * 文件名：ProxyUtils.java
 * 版本信息：
 * 日期：2018年8月11日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.dynamic.sample2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;
import sun.misc.ProxyGenerator;

/**
 * ProxyUtils
 * 
 * @author：youngzil@163.com
 * @2018年8月11日 下午11:59:21
 * @since 1.0
 */
public class ProxyUtils {

    /*
     * 将根据类信息 动态生成的二进制字节码保存到硬盘中，
     * 默认的是clazz目录下
         * params :clazz 需要生成动态代理类的类
         * proxyName : 为动态生成的代理类的名称
         */
    public static void generateClassFile(Class clazz, String proxyName) {
        // 根据类信息和提供的代理类名称，生成字节码
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
        String paths = clazz.getResource(".").getPath();
        System.out.println(paths);
        
        FileOutputStream out = null;
        try {
            // 保留到硬盘中
            out = new FileOutputStream(paths + proxyName + ".class");
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
