package org.quickstart.kotlin;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/2 12:02
 */
public class JavaCallKotlin {

  public static void main(String[] args) {
    //  Class
    KotlinBean kotlinBean = new KotlinBean("Java call KotlinBean");
    System.out.printf(kotlinBean.getName());    //  Peter
    System.out.println(kotlinBean.calc(2, 3));  //  5

    KotlinBean.hello(kotlinBean);               //  Hello, this is Peter
    KotlinBean.Companion.echo("GoodBye", kotlinBean);   //  GoodBye, this is Peter

    //  Object
    KotlinUtils.foo();
    KotlinUtils.INSTANCE.bar();
  }
}
