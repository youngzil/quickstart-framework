package org.quickstart.guava.collections;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-14 22:31
 */
public class MultimapTest {

  @Test
  public void testMultimap() {

    // 很少会直接使用Multimap接口，更多时候你会用ListMultimap或SetMultimap接口，它们分别把键映射到List或Set。
    // ”键-值集合映射”的映射：
    // Multimap不是Map

    ListMultimap<String, String> multimap = ArrayListMultimap.create();
    multimap.put("test", "value1");
    multimap.put("test", "value2");
    multimap.put("test", "value3");
    multimap.put("test", "value4");
    multimap.put("test", "value5");

    multimap.put("test1", "value6");
    multimap.put("test1", "value7");
    multimap.put("test1", "value8");
    multimap.put("test1", "value9");

    // Multimap.get(key)以集合形式返回键所对应的值视图，即使没有任何对应的值，也会返回空集合。ListMultimap.get(key)返回List，SetMultimap.get(key)返回Set。

    List<String> list = multimap.get("test");



  }
}
