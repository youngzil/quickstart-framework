package org.quickstart.guava.collections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-14 12:23
 */
public class MultiSetTest {

  @Test
  public void testMultiSet() {

    String[] words = new String[2];

    // 统计一个词在文档中出现了多少次，传统的做法是这样的：

    Map<String, Integer> counts = new HashMap<String, Integer>();
    for (String word : words) {
      Integer count = counts.get(word);
      if (count == null) {
        counts.put(word, 1);
      } else {
        counts.put(word, count + 1);
      }
    }

    // 使用Multiset接口
    Multiset<String> multiset = HashMultiset.create();
    Arrays.asList(words).forEach(word -> multiset.add(word));

    System.out.println(multiset.size());// 返回集合元素的总个数（包括重复的元素）

    Set<String> uniqueSet = multiset.elementSet();// 获取Multiset中不重复元素的集合，类型为Set<E>
    Arrays.asList(uniqueSet).forEach(word -> System.out.println("word count = " + multiset.count(word)));// 给定元素在Multiset中的计数

    // Multiset：”集合[set]概念的延伸，它的元素可以重复出现…与集合[set]相同而与元组[tuple]相反的是，Multiset元素的顺序是无关紧要的：Multiset {a, a, b}和{a, b, a}是相等的”。
    // 元素可以重复，并且元素没有顺序区别，没有元素顺序限制的ArrayList<E>
    // Multiset不是Map

  }

}
