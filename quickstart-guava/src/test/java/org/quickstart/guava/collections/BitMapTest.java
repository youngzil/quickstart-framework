package org.quickstart.guava.collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-14 22:54
 */
public class BitMapTest {

  public static void main(String[] args) {

    // BiMap<K, V>是特殊的Map：
    // 可以用 inverse()反转BiMap<K, V>的键值映射
    // 保证值是唯一的，因此 values()返回Set而不是普通的Collection

    BiMap<String, Integer> bitmap = HashBiMap.create();

    bitmap.put("test1", 1);
    bitmap.put("test2", 2);
    bitmap.put("test3", 3);

    String str = bitmap.inverse().get(2);
    System.out.println(str);

  }

}
