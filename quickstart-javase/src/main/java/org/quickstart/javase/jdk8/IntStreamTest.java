/**
 * 项目名称：quickstart-javase 
 * 文件名：IntStreamTest.java
 * 版本信息：
 * 日期：2018年9月12日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/**
 * IntStreamTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年9月12日 下午2:17:46
 * @since 1.0
 */
public class IntStreamTest {

    public static IntStream generateFibonacciSequence() {
        return IntStream.iterate(1, new IntUnaryOperator() {

            private int prev = 0;

            @Override
            public int applyAsInt(int operand) {
                int temp = operand + prev;
                prev = operand;
                return temp;
            }
        });
    }

    public static void main(String[] args) {
        // 生成前20项斐波那契数列
        // [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597,
        // 2584, 4181, 6765]
        System.out.println(Arrays.toString(IntStreamTest.generateFibonacciSequence().limit(20).toArray()));
    }

}
