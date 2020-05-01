package org.quickstart.javase.jdk.hashcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-11 15:29
 */
public class HashMapTest {

  @Test
  public void testMap() {
    Map<String, String> params = new HashMap<>();
    params.put("test", "test");
    params.put("abc", "test");
    params.put("bar", "test");
    params.put("foo", "test");


    String[] keys = params.keySet().toArray(new String[0]);

    System.out.println(keys);

    Arrays.sort(keys);
    System.out.println(keys);


  }

}
