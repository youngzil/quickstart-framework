package org.quickstart.kotlin

/**
 * @description TODO
 * @author youngzil@163.com
 * @createTime 2019/11/2 11:44
 */
object ScalaCallJava extends App {

  val javaBean = new JavaBean("Scala call JavaBean")
  println(javaBean.getName) //  JavaBean
  println(javaBean.calc(2, 3)) //  5

  JavaBean.hello(javaBean) //  Hello, this is JavaBean

}
