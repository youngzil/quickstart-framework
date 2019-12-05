package org.quickstart.example.algorithm.sort;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/4 16:41
 */
public class BubbleSort {

  // 冒泡排序算法实现
  public static void main(String[] args) {

    int[] arr = new int[]{2, 4, 6, 8, 5, 9, 7, 3, 1, 0};
    bubbleSort(arr);
    System.out.println(arr);

  }

  public static void bubbleSort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = arr.length - 1; j > i; j--) {
        if (arr[j] < arr[j - 1]) {
          swap(arr, j - 1, j);
        }
      }
    }
  }

  public static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

}