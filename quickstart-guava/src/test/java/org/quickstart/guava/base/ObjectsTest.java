/**
 * 项目名称：quickstart-guava 文件名：OptionalTest.java 版本信息： 日期：2018年1月3日 Copyright yangzl Corporation 2018 版权所有 *
 */
package org.quickstart.guava.base;

import org.junit.Ignore;
import org.junit.Test;
import org.quickstart.guava.model.Bar;
import org.quickstart.guava.model.Color;

import com.google.common.base.Objects;

/**
 * OptionalTest
 * 
 * @author：youngzil@163.com
 * @2018年1月3日 上午9:21:27
 * @since 1.0
 */
public class ObjectsTest {

  public static void main(String[] args) {

    Objects.equal("a", "a"); // returns true
    Objects.equal(null, "a"); // returns false
    Objects.equal("a", null); // returns false
    Objects.equal(null, null); // returns true

  }

  @Test
  public void testHashcode() {

    Objects.hashCode(Bar.class, Color.class);

    Bar bar = new Bar();
    Objects.hashCode(bar.getCode(), bar.getName());

  }

  @Ignore
  @Test
  public void testToStrig() {

    // 老版本的方法

    // Returns "ClassName{x=1}"
    // Objects.toStringHelper(this).add("x", 1).toString();
    // Returns "MyObject{x=1}"
    // Objects.toStringHelper("MyObject").add("x", 1).toString();

  }



}
