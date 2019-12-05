package org.quickstart.example.algorithm.sort;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/4 17:09
 */
public class QuickSort {

  // 实现快速排序算法
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

  public static void quickSort(int[] arr, int left, int right) {
    if (left >= right) {
      return;
    }
    int pivotPos = partition(arr, left, right);
    quickSort(arr, left, pivotPos - 1);
    quickSort(arr, pivotPos + 1, right);
  }

  //一次划分
  public static int partition(int[] arr, int left, int right) {
    int pivotKey = arr[left];
    int pivotPointer = left;

    while (left < right) {
      while (left < right && arr[right] >= pivotKey) {
        right--;
      }
      while (left < right && arr[left] <= pivotKey) {
        left++;
      }
      swap(arr, left, right); //把大的交换到右边，把小的交换到左边。
    }
    swap(arr, pivotPointer, left); //最后把pivot交换到中间
    return left;
  }


  public static void swap(int[] arr, int left, int right) {
    int temp = arr[left];
    arr[left] = arr[right];
    arr[right] = temp;
  }

}
