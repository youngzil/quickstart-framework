package org.quickstart.guava.collections;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-17 10:52
 */
public class CollectionsTest {

  @Test
  public void testCollections() {

    // 能够推断范型的静态工厂方法：
    List<String> list = Lists.newArrayList();
    Map<String, String> map = Maps.newLinkedHashMap();

    // 用工厂方法模式，我们可以方便地在初始化时就指定起始元素。
    Set<String> copySet = Sets.newHashSet("hehe");
    List<String> theseElements = Lists.newArrayList("alpha", "beta", "gamma");

    // 通过为工厂方法命名（Effective Java第一条），我们可以提高集合初始化大小的可读性
    List<String> exactly100 = Lists.newArrayListWithCapacity(100);
    List<String> approx100 = Lists.newArrayListWithExpectedSize(100);
    Set<String> approx100Set = Sets.newHashSetWithExpectedSize(100);

    // Guava引入的新集合类型没有暴露原始构造器，也没有在工具类中提供初始化方法。而是直接在集合类中提供了静态工厂方法，例如：
    Multiset<String> multiset = HashMultiset.create();

  }


  @Test
  public  void testIterables(){
    Iterable<Integer> concatenated = Iterables.concat(//
        Ints.asList(1, 2, 3),//
        Ints.asList(4, 5, 6)); // concatenated包括元素 1, 2, 3, 4, 5, 6

    Set<Integer> myLinkedHashSet = Sets.newLinkedHashSet(concatenated);
    Integer lastAdded = Iterables.getLast(myLinkedHashSet);

    Set<String> thisSetIsDefinitelyASingleton = Sets.newHashSet("hehe");
    // Set<String> thisSetIsDefinitelyASingleton = Sets.newHashSet("hehe","haha");
    String theElement = Iterables.getOnlyElement(thisSetIsDefinitelyASingleton);
    //如果set不是单元素集，就会出错了！
  }

}
