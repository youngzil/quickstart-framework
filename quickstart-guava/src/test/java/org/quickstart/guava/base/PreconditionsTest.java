package org.quickstart.guava.base;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static com.google.common.base.Preconditions.checkPositionIndexes;
import static com.google.common.base.Preconditions.checkState;

import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-14 11:20
 */
public class PreconditionsTest {

  @Test
  public void testBase() {

    // int i = -2, j = -3;
    int i = 2, j = 3;

    // 检查boolean是否为true，用来检查传递给方法的参数。
    checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
    checkArgument(i < j, "Expected i < j, but %s > %s", i, j);

    int value = checkNotNull(3);// 检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull。
    System.out.println("value=" + value);

    checkState(3 > 2);// 用来检查对象的某些状态。

    int index = 1;
    int size = 20;
    checkElementIndex(index, size);// 检查index作为索引值对某个列表、字符串或数组是否有效。index>=0 && index<size *
    checkPositionIndex(index, size);// 检查index作为索引值对某个列表、字符串或数组是否有效。index>=0 && index<size *

    int end = 10;
    checkPositionIndexes(index, end, size);// 检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效*

  }

}
