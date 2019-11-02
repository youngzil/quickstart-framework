package org.quickstart.kotlin;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/2 11:23
 */
public class JavaCallGroovy {

  public static void main(String[] args) {
    GroovyBean groovyBean = new GroovyBean("Java call GroovyBean");
    System.out.println(groovyBean.getName());   //  GroovyBean
    System.out.println(groovyBean.calc(2, 3));  //  5
    GroovyBean.hello(groovyBean);               //  Hello, this is GroovyBean
  }
}
