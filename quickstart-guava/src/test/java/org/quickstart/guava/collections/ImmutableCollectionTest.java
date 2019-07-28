/**
 * 项目名称：quickstart-guava 文件名：ImmutableCollectionTest.java 版本信息： 日期：2018年1月3日 Copyright yangzl Corporation 2018 版权所有 *
 */
package org.quickstart.guava.collections;

import java.util.Set;

import org.quickstart.guava.model.Bar;
import org.quickstart.guava.model.Color;

import com.google.common.collect.ImmutableSet;

/**
 * ImmutableCollectionTest
 * 
 * @author：youngzil@163.com
 * @2018年1月3日 上午9:38:23
 * @since 1.0
 */
public class ImmutableCollectionTest {
  public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of("red", "orange", "yellow", "green", "blue", "purple");

  class Foo {
    Set<Bar> bars;

    Foo(Set<Bar> bars) {
      this.bars = ImmutableSet.copyOf(bars); // defensive copy!
    }
  }

  public static final ImmutableSet<Color> WEBSAFE_COLORS = ImmutableSet.of(new Color(0, 191, 255));;
  public static final ImmutableSet<Color> GOOGLE_COLORS = ImmutableSet.<Color>builder()//
      .addAll(WEBSAFE_COLORS)//
      .add(new Color(0, 191, 255))//
      .build();

}
