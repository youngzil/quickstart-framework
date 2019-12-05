package org.quickstart.example.algorithm.sort;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/4 17:12
 */
public class ShellSort {

  // 希尔排序算法实现
  public static void main(String[] args) {

    int[] arr = new int[]{2, 4, 6, 8, 5, 9, 7, 3, 1, 0};
    shellSort(arr);
    System.out.println(arr);

  }

  public static void shellSort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    int d = arr.length / 2;
    while (d >= 1) {
      shellInsert(arr, d);
      d /= 2;
    }
  }

  /**
   * 希尔排序的一趟插入
   *
   * @param arr 待排数组
   * @param d 增量
   */
  public static void shellInsert(int[] arr, int d) {
    for (int i = d; i < arr.length; i++) {
      int j = i - d;
      int temp = arr[i];    //记录要插入的数据
      while (j >= 0 && arr[j] > temp) {  //从后向前，找到比其小的数的位置
        arr[j + d] = arr[j];    //向后挪动
        j -= d;
      }

      if (j != i - d)    //存在比其小的数
      {
        arr[j + d] = temp;
      }

    }
  }

}
