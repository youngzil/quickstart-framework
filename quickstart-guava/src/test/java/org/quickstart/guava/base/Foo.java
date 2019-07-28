package org.quickstart.guava.base;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-14 11:59
 */
public class Foo {

  private String aString;
  private static String anInt;
  private static String anEnum;

  public int compareTo(Foo that) {
    return ComparisonChain.start()//
        .compare(this.aString, that.aString)//
        .compare(this.anInt, that.anInt)//
        .compare(this.anEnum, that.anEnum, Ordering.natural().nullsLast())//
        .result();
  }

}
