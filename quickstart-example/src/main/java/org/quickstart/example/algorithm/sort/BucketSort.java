package org.quickstart.example.algorithm.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/4 17:14
 */
public class BucketSort {

  // 桶排序算法实现
  public static void main(String[] args) {

    int[] arr = new int[]{2, 4, 6, 8, 5, 9, 7, 3, 1, 0};
    bucketSort(arr);
    System.out.println(arr);

  }

  public static void bucketSort(int[] arr) {
    if(arr == null && arr.length == 0)
      return ;

    int bucketNums = 10; //这里默认为10，规定待排数[0,100)
    List<List<Integer>> buckets = new ArrayList<List<Integer>>(); //桶的索引

    for(int i=0; i<10; i++) {
      buckets.add(new LinkedList<Integer>()); //用链表比较合适
    }

    //划分桶
    for(int i=0; i<arr.length; i++) {
      buckets.get(f(arr[i])).add(arr[i]);
    }

    //对每个桶进行排序
    for(int i=0; i<buckets.size(); i++) {
      if(!buckets.get(i).isEmpty()) {
        Collections.sort(buckets.get(i)); //对每个桶进行快排
      }
    }

    //还原排好序的数组
    int k = 0;
    for(List<Integer> bucket : buckets) {
      for(int ele : bucket) {
        arr[k++] = ele;
      }
    }
  }

  /**
   * 映射函数
   * @param x
   * @return
   */
  public static int f(int x) {
    return x / 10;
  }

}
