package org.quickstart.javase.jdk8.concurrent;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/11 22:46
 */
public class ThreadLocalRandomTest {

  public static void main(String[] args) {
    ThreadLocalRandom.current().nextInt(2000);
  }

}
