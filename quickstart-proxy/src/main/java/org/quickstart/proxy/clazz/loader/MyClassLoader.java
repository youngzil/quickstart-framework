/**
 * 项目名称：quickstart-proxy 
 * 文件名：MyClassLoader.java
 * 版本信息：
 * 日期：2018年8月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.clazz.loader;

/**
 * MyClassLoader 自定义一个类加载器，用于将字节码转换为class对象
 * 
 * @author：youngzil@163.com
 * @2018年8月11日 下午10:50:55
 * @since 1.0
 */
public class MyClassLoader extends ClassLoader {

    public Class<?> defineMyClass(byte[] b, int off, int len) {
        return super.defineClass(b, off, len);
    }

}
