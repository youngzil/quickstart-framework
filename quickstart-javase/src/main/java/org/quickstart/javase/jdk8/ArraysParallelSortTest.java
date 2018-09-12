/**
 * 项目名称：quickstart-javase 
 * 文件名：ArraysParallelSortTest.java
 * 版本信息：
 * 日期：2018年8月28日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

import java.util.Arrays;

/**
 * ArraysParallelSortTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年8月28日 下午10:58:12
 * @since 1.0
 */
public class ArraysParallelSortTest {

    public static void main(String[] args) {

        int[] myArray = {1, 3, 5, 2, 4};// 初始化为给定值
        int[] myArray2 = new int[] {1, 3, 5, 2, 4};
        // int[] myArray = new int[5] {1, 3, 5, 2, 4};

        Arrays.sort(myArray);
        // 而是这么做：
        Arrays.parallelSort(myArray);
    }

}
