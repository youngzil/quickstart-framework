/**
 * 项目名称：quickstart-javase
 * 文件名：MathTest.java
 * 版本信息：
 * 日期：2018年8月28日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

/**
 * MathTest
 *
 * @author：youngzil@163.com
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

        // Math.addExact()//加法
        // Math.subtractExact()//减法
        // Math.multiplyExact()//乘法
        // Math.floorDiv()//除法

        // Math.negateExact()//取反
        Math.abs(dd);// 数据绝对值的函数

        // Math.incrementExact()//加1
        // Math.decrementExact()//减1

        // Java中取余(%)和取模(Math.floorMod)的区别
        // 区别是：
        // 取余运算在计算商值向0方向舍弃小数位
        // 取模运算在计算商值向负无穷方向舍弃小数位
        // https://blog.csdn.net/qq_37499840/article/details/89047554

        // Math.floorMod()

    }

}
