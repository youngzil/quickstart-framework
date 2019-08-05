package org.quickstart.javase.jdk.juc.forkjoin;

import java.util.concurrent.ForkJoinPool;

import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-05 09:58
 */
public class ForkJoinCalculatorTest {

  @Test
  public void testCompute() {
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    Long sum = forkJoinPool.invoke(new ForkJoinCalculator(1, 1000000000L));
    System.out.println(sum);
  }

}
