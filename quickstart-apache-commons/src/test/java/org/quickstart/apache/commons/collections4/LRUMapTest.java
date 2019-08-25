package org.quickstart.apache.commons.collections4;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections4.map.LRUMap;
import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/8/25 15:30
 */
public class LRUMapTest {

  @Test
  public void testLRUMap() {

    Map map = new LRUMap<String, Integer>(4);
    map.put("1", 1);
    map.put("2", 2);
    map.put("3", 3);
    map.get("1");
    map.put("4", 4);
    map.put("5", 5);

    Iterator it = map.keySet().iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }

  }

}
