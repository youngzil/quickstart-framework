package org.quickstart.kotlin

import scala.beans.BeanProperty

/**
 * @description TODO
 * @author youngzil@163.com
 * @createTime 2019/11/2 11:55
 */

//Java 调用 Scala 时需要注意 class 和 object 的区别。此外在 Scala 对象中如果属性没有声明为 @BeanProperty 的话，调用时需要使用 对象.属性名() 来调用，声明后才可以使用 Java 风格的 对象.get属性名() 来调用。

class ScalaBean(@BeanProperty val name: String) {

  val age: Int = 10

  def calc(x: Int, y: Int) = x + y
}

object ScalaBean {

  def hello(bean: ScalaBean): Unit = println(s"Hello, this is ${bean.name}")
}

object ScalaUtils {
  def foo = println("Foo...")
}
