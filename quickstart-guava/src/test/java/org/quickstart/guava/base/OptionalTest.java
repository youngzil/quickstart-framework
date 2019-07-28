/**
 * 项目名称：quickstart-guava 文件名：OptionalTest.java 版本信息： 日期：2018年1月3日 Copyright yangzl Corporation 2018 版权所有 *
 */
package org.quickstart.guava.base;

import com.google.common.base.Optional;

/**
 * OptionalTest
 * 
 * @author：youngzil@163.com
 * @2018年1月3日 上午9:21:27
 * @since 1.0
 */
public class OptionalTest {

  public static void main(String[] args) {

    // 创建Optional实例（以下都是静态方法）：
    // Optional.of(T) 创建指定引用的Optional实例，若引用为null则快速失败
    // Optional.absent() 创建引用缺失的Optional实例
    // Optional.fromNullable(T) 创建指定引用的Optional实例，若引用为null则表示缺失

    Optional<Integer> possible = Optional.of(5);

    Optional possible2 = Optional.absent();
    System.out.println(possible2.isPresent());// returns false

    possible.isPresent(); // returns true
    possible.absent();

    int value = possible.get(); // returns 5
    System.out.println(value);

    System.out.println(possible2.or(0));
    System.out.println(possible.or(0));

    System.out.println(possible2.asSet());
    System.out.println(possible.asSet());

    Optional.of(null).or("defaultValue");// 用一个默认值来替换可能的null

    // 用Optional实例查询引用（以下都是非静态方法）：
    // boolean isPresent() 如果Optional包含非null的引用（引用存在），返回true
    // T get() 返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
    // T or(T) 返回Optional所包含的引用，若引用缺失，返回指定的值
    // T orNull() 返回Optional所包含的引用，若引用缺失，返回null
    // Set<T> asSet() 返回Optional所包含引用的单例不可变集，如果引用存在，返回一个只有单一元素的集合，如果引用缺失，返回一个空集合。

  }

}
