package org.quickstart.guava.collections;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-14 23:02
 */
public class TableTest {

  public static void main(String[] args) {
    // 使用多个键做索引的时候

    Table<String, String, Double> weightedGraph = HashBasedTable.create();
    weightedGraph.put("v1", "v2", 4D);
    weightedGraph.put("v1", "v3", 20D);
    weightedGraph.put("v2", "v3", 5D);

    weightedGraph.row("v1"); // returns a Map mapping v2 to 4, v3 to 20
    weightedGraph.column("v3"); // returns a Map mapping v1 to 20, v2 to 5

  }

}
