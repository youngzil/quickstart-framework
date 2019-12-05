package org.quickstart.example.algorithm.sort;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/4 16:46
 */
public class SelectSort {

  // 简单选择排序算法的实现
  public static void main(String[] args) {

    int[] arr = new int[]{2, 4, 6, 8, 5, 9, 7, 3, 1, 0};
    selectSort(arr);
    System.out.println(arr);

  }

  public static void selectSort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    int minIndex = 0;
    for (int i = 0; i < arr.length - 1; i++) { //只需要比较n-1次
      minIndex = i;
      for (int j = i + 1; j < arr.length; j++) { //从i+1开始比较，因为minIndex默认为i了，i就没必要比了。
        if (arr[j] < arr[minIndex]) {
          minIndex = j;
        }
      }

      if (minIndex != i) { //如果minIndex不为i，说明找到了更小的值，交换之。
        swap(arr, i, minIndex);
      }
    }

  }

  public static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

}
