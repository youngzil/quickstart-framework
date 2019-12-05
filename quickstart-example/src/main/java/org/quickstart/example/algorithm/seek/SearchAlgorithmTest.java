package org.quickstart.example.algorithm.seek;

import java.util.Arrays;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/5 22:27
 */
public class SearchAlgorithmTest {


  /**
   * 顺序查找算法 从数据序列中第一个元素开始，从头到尾依次逐个查找。直到找到所要的数据或搜索完整个数据序列
   */
  public static int searchFun(int a[], int x) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] == x) {
        return i;
      }
    }
    return -1;
  }


  // /**
  //  * 折半查找，又称为二分法查找。要求数据序列呈线性结构，也就是经过排序的。
  //  * 从数据的1/2处匹配，如果与需要查询的值相等，直接返回，
  //  * 如果大于需要查询的关键字，则在从前半部分的1/2处查找，也就是整个数据的1/4处；
  //  * 如果小于需要查询的关键字，则从后半部分的1/2处查找，也就是整个数据的3/4出；
  //  * 以此类推，直到找到关键字或将查找范围缩小到只剩下一个元素也不等于关键字.
  //  */
  public static int binarySearch(int a[], int x) {
    int midNum, lowNum = 0, highNum = a.length - 1;
    while (lowNum <= highNum) {
      midNum = (lowNum + highNum) / 2;
      if (a[midNum] == x) {
        return midNum;
      } else if (a[midNum] > x) {
        highNum = midNum - 1;
      } else if (a[midNum] < x) {
        lowNum = midNum + 1;
      }
    }
    return -1;
  }

  // 链表结构中的查找算法

  /**
   * 定义数据结构中的元素
   */
  class DataElement {

    String key;
    String other_messages;

  }

  /**
   * 定义链表结构
   */
  class DataLinkedList {

    DataElement dataElement;
    DataLinkedList nextNode;
    //我们这里只做查找算法的示例，省去添加节点，删除节点等其他方法，只做查找

    /**
     * 链表结构中的查找算法 一般来说可以通过关键字查询，从表头依次查找
     */
    DataLinkedList findData(DataLinkedList head, String key) {
      DataLinkedList temp = head;//保存表头指针
      while (temp != null) {//节点有效，进行查找
        DataElement date = temp.dataElement;
        if (date.key.equals(key)) {//节点的关键字与传入的关键字相同
          return temp;//返回该节点指针
        }
        temp = temp.nextNode;//处理下一节点
      }
      return null;//未找到，返回空指针
    }
  }


  /**
   * 定义二叉树结构
   */
  static class TreeDate {

    String data;
    TreeDate leftData;
    TreeDate rightData;

    /**
     * 二叉树查找算法 遍历二叉树中的每一个结点，逐个比较数据。
     *
     * @param treeNode 树结点,首次调用传入树的根结点
     * @param data 要查找的结点
     * @return TreeDate查找结果
     */
    TreeDate findData(TreeDate treeNode, String data) {
      if (treeNode == null) {
        return null;
      } else {
        if (treeNode.data.equals(data)) {
          return treeNode;
        }
        if (findData(treeNode.leftData, data) != null) {//递归查找左结点
          return findData(treeNode.leftData, data);
        }
        if (findData(treeNode.rightData, data) != null) {//递归查找右结点
          return findData(treeNode.rightData, data);
        }
      }
      return null;
    }
  }


  /**
   * 定义图结构
   */
  static class Graph {

    static final int SIZE = 5;//图的最大顶点数
    static final int MAX_VALUE = 65535;//最大值
    public char[] vertex = new char[SIZE];//保存顶点信息（序号或字母）
    //        int graphType;//图的类型（0.无向图；1.有向图）
    int vertexNum;//顶点的数量
    //        int edgeNum;//边的数量
    int[][] edgeWeight = new int[SIZE][SIZE];//保存边的权值
    int[] isTraversal = new int[SIZE];//遍历标志

    /**
     * 深度遍历图 从第n个顶点开始遍历
     *
     * @param graph 图
     * @param n 第n个顶点
     * @param key 需要查找的顶点
     */
    static void deepTraOne(Graph graph, int n, char key) {
      graph.isTraversal[n] = 1;//标记该顶点已经处理过
      if (graph.vertex[n] == key) {//就是要找的结点,如果是进行遍历而不是查找，删除这个判断输出所有结点数据即可
        System.out.printf("->%c", graph.vertex[n]);//输出结点数据
        return;
      }
      //添加处理结点的操作
      for (int i = 0; i < graph.vertexNum; i++) {
        if (graph.edgeWeight[n][i] != MAX_VALUE && graph.isTraversal[n] == 0) {
          deepTraOne(graph, i, key);//递归遍历
        }
      }
    }

    /**
     * 深度优先遍历
     *
     * @param graph 图
     * @param key 需要查找的顶点
     */
    static void findVertex(Graph graph, char key) {
      //清除各顶点遍历标志
      //            for (int i = 0; i < graph.vertexNum; i++) {
      //                graph.isTraversal[i] = 0;
      //            }
      Arrays.parallelSetAll(graph.isTraversal, index -> 0);//java8的写法,作用同上面的for循环
      System.out.printf("深度优先遍历结点：");
      for (int i = 0; i < graph.vertexNum; i++) {
        if (graph.isTraversal[i] == 0) {//若该点未遍历
          deepTraOne(graph, i, key);//调用遍历函数
        }
      }
      System.out.printf("\n");
    }
  }


}
