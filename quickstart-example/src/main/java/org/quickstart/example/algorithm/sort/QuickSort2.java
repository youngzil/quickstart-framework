package org.quickstart.example.algorithm.sort;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/4 17:11
 */
public class QuickSort2 {

  // 优化版本：实现快速排序算法
  public static void main(String[] args) {

    int[] arr = new int[]{2, 4, 6, 8, 5, 9, 7, 3, 1, 0};
    sort(arr);
    System.out.println(arr);

  }

  public static void sort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    quickSort(arr, 0, arr.length - 1);
  }

  /**
   * 递归划分子序列
   */
  public static void quickSort(int[] arr, int left, int right) {
    if (left >= right) {
      return;
    }
    int pivotPos = partition(arr, left, right);
    quickSort(arr, left, pivotPos - 1);
    quickSort(arr, pivotPos + 1, right);
  }

  /**
   * 划分
   */
  public static int partition(int[] arr, int left, int right) {
    int pivotKey = arr[left];

    while (left < right) {
      while (left < right && arr[right] >= pivotKey) {
        right--;
      }
      arr[left] = arr[right]; //把小的移动到左边
      while (left < right && arr[left] <= pivotKey) {
        left++;
      }
      arr[right] = arr[left]; //把大的移动到右边
    }
    arr[left] = pivotKey; //最后把pivot赋值到中间
    return left;
  }


}
