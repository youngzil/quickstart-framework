/**
 * 项目名称：quickstart-javase 
 * 文件名：TestClassStaticBlock.java
 * 版本信息：
 * 日期：2017年10月30日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.classloader;

/**
 * TestClassStaticBlock
 * 
 * @author：youngzil@163.com
 * @2017年10月30日 上午11:25:41
 * @since 1.0
 */
public class TestClassStaticBlock {

    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> clazz = Class.forName("org.quickstart.javase.jdk.classloader.ClassStaticBlock");

        System.out.println(clazz);

    }

}
