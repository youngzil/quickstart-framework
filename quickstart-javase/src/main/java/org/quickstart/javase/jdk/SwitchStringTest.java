package org.quickstart.javase.jdk;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/11 23:16
 */
public class SwitchStringTest {

  public static void main(String[] args) {
    method(null);
  }

  public static void method(String param) {
    switch (param) {
      // 肯定不是进入这里
      case "sth":
        System.out.println("it's sth");
        break;
      // 也不是进入这里
      case "null":
        System.out.println("it's null");
        break;
      // 也不是进入这里
      default:
        System.out.println("default");
    }
  }

}
