package org.quickstart.example.algorithm.sort;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/4 16:50
 */
public class InsertSort {

  // 简单插入排序算法实现
  public static void main(String[] args) {

    int[] arr = new int[]{2, 4, 6, 8, 5, 9, 7, 3, 1, 0};
    insertSort(arr);
    System.out.println(arr);

  }

  public static void insertSort(int[] arr) {
    if(arr == null || arr.length == 0)
      return ;

    for(int i=1; i<arr.length; i++) { //假设第一个数位置时正确的；要往后移，必须要假设第一个。

      int j = i;
      int target = arr[i]; //待插入的

      //后移
      while(j > 0 && target < arr[j-1]) {
        arr[j] = arr[j-1];
        j --;
      }

      //插入
      arr[j] = target;
    }

  }

}
