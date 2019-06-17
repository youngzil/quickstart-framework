/**
 * 项目名称：quickstart-javase 
 * 文件名：Day.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.collection;

/**
 * Day
 * 
 * @author：youngzil@163.com
 * @2018年5月10日 下午7:59:50
 * @since 1.0
 */
public class Day {

    public Day(int i, int j, int k) {}

    public static void main(String[] args) {
        int[][] array = {{1, 2, 3, 4, 5}, {2, 3, 4, 5, 6}, {3, 4, 5, 6, 7}};
        System.out.println(array.length);
        System.out.println(array[0].length);
    }

    public boolean Find(int[][] array, int target) {
        int row = array.length - 1;
        int i = 0;
        while ((row >= 0) && (i < array[0].length)) {
            if (array[row][i] > target) {
                row--;
            } else if (array[row][i] < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

}
