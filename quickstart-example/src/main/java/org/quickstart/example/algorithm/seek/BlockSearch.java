package org.quickstart.example.algorithm.seek;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/5 22:33
 */
public class BlockSearch {

  /**
   * 分块查找
   *
   * @param index
   *            索引表，其中放的是各块的最大值
   * @param st
   *            顺序表，
   * @param key
   *            要查找的值
   * @param m
   *            顺序表中各块的长度相等，为m
   * @return
   */
  public static int blockSearch(int[] index, int[] st, int key, int m) {
    // 在序列st数组中，用分块查找方法查找关键字为key的记录
    // 1.在index[ ] 中折半查找，确定要查找的key属于哪个块中
    int i = BinarySearch.binarySearch(index, key);
    if (i >= 0) {
      int j = i > 0 ? i * m : i;
      int len = (i + 1) * m;
      // 在确定的块中用顺序查找方法查找key
      for (int k = j; k < len; k++) {
        if (key == st[k]) {
          System.out.println("查询成功");
          return k;
        }
      }
    }
    System.out.println("查找失败");
    return -1;
  }

}
