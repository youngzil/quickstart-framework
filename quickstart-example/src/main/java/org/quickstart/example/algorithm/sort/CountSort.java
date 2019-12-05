package org.quickstart.example.algorithm.sort;

import java.util.Arrays;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/4 17:13
 */
public class CountSort {
  // 计数排序算法实现
  public static void main(String[] args) {

    int[] arr = new int[]{2, 4, 6, 8, 5, 9, 7, 3, 1, 0};
    countSort(arr);
    System.out.println(arr);

  }

  public static void countSort(int[] arr) {
    if(arr == null || arr.length == 0)
      return ;

    int max = max(arr);

    int[] count = new int[max+1];
    Arrays.fill(count, 0);

    for(int i=0; i<arr.length; i++) {
      count[arr[i]] ++;
    }

    int k = 0;
    for(int i=0; i<=max; i++) {
      for(int j=0; j<count[i]; j++) {
        arr[k++] = i;
      }
    }

  }

  public static int max(int[] arr) {
    int max = Integer.MIN_VALUE;
    for(int ele : arr) {
      if(ele > max)
        max = ele;
    }

    return max;
  }

}
