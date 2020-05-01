/**
 * 项目名称：quickstart-javase 
 * 文件名：ObjectsTest.java
 * 版本信息：
 * 日期：2018年3月28日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Objects;

/**
 * ObjectsTest
 * 
 * @author：youngzil@163.com
 * @2018年3月28日 下午9:19:14
 * @since 1.0
 */
public class ObjectsTest {

    public static void main(String[] args) {

        Objects.requireNonNull(null, () -> "不能为空");
        
        
      //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
        

    }

}
