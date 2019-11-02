package org.quickstart.kotlin;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/2 11:14
 */
// @Setter
// @Getter
// @AllArgsConstructor
// @NoArgsConstructor
// scala 使用lombok 不能识别，groovy可以使用
public class JavaBean {

  private String name;

  public JavaBean(String name) {
    this.name = name;
  }

  public int calc(int x, int y) {
    return x + y;
  }

  public static void hello(JavaBean bean) {
    System.out.println("Hello, this is " + bean.name);
  }

  public boolean is(String name) {
    return this.name.equals(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
