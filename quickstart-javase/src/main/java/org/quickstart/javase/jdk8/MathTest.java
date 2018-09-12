/**
 * 项目名称：quickstart-javase 
 * 文件名：MathTest.java
 * 版本信息：
 * 日期：2018年8月28日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

/**
 * MathTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年8月28日 下午11:16:36
 * @since 1.0
 */
public class MathTest {

    public static void main(String[] args) {
        // 如果结果超出+-2^31，就会抛出ArithmeticException异常
        int safeC = Math.multiplyExact(23, 45);
        System.out.println(safeC);
        
        int dd = Math.floorDiv(30, 5);
        System.out.println(dd);
        
    }

}
