package org.quickstart.guava.collections;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-14 23:07
 */
public class ClassToInstanceMapTest {

  public static void main(String[] args) {

    // ClassToInstanceMap是一种特殊的Map：它的键是类型，而值是符合键所指类型的对象。


    ClassToInstanceMap<Number> numberDefaults = MutableClassToInstanceMap.create();
    numberDefaults.putInstance(Integer.class, Integer.valueOf(0));


  }

}
