package org.quickstart.guava.collections;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-14 23:14
 */
public class RangeMapTest {

  public static void main(String[] args) {

    // RangeMap描述了”不相交的、非空的区间”到特定值的映射。和RangeSet不同，RangeMap不会合并相邻的映射，即便相邻的区间映射到相同的值。

    RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
    rangeMap.put(Range.closed(1, 10), "foo"); // {[1,10] => "foo"}
    rangeMap.put(Range.open(3, 6), "bar"); // {[1,3] => "foo", (3,6) => "bar", [6,10] => "foo"}
    rangeMap.put(Range.open(10, 20), "foo"); // {[1,3] => "foo", (3,6) => "bar", [6,10] => "foo", (10,20) => "foo"}
    rangeMap.remove(Range.closed(5, 11)); // {[1,3] => "foo", (3,5) => "bar", (11,20) => "foo"}

  }

}
