package org.quickstart.kotlin;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/2 11:56
 */
public class JavaCallScala {

  public static void main(String[] args) {
    ScalaBean scalaBean = new ScalaBean("Java call ScalaBean");
    // Scala 属性的默认调用方式
    System.out.println(scalaBean.name());       //  ScalaBean
    // 声明为 @BeanProperty 后提供的调用方式
    System.out.println(scalaBean.getName());    //  ScalaBean
    System.out.println(scalaBean.age());        //  10
    System.out.println(scalaBean.calc(2, 3));    //  5

    // 调用 Scala的单例对象，本质上调用的是下面一行
    ScalaBean.hello(scalaBean);                 //  Hello, this is ScalaBean
    ScalaBean$.MODULE$.hello(scalaBean);        //  Hello, this is ScalaBean

    ScalaUtils.foo();   //  Foo...
  }
}
