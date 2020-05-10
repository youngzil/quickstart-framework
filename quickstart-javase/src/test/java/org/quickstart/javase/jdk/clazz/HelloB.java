package org.quickstart.javase.jdk.clazz;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/5/6 10:11
 */
public class HelloB extends HelloA {

  public HelloB() {
    System.out.println("HelloB");
  }

  {
    System.out.println("I'm B class");
  }

  static {
    System.out.println("static B");
  }

  public static void main(String[] args) {
    new HelloB();
  }

}

