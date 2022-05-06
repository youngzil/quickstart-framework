package org.quickstart.apache.commons.beanutils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 10:03
 */
public class BeanUtilsTest {

  @Test
	 public void test01() throws Exception {
    Student stu1 = new Student();
    Student stu2 = new Student();
    stu2.setNo("TEST001");
    stu2.setName("Jack Chen");

    //复制属性
    System.out.println(stu1);
    System.out.println("------------------------");
    BeanUtils.copyProperties(stu1, stu2);
    System.out.println(stu1);

 //设置属性的值
    BeanUtils.copyProperty(stu1, "major", "机械设计");
    System.out.println("------------------------");
    System.out.println(stu1);

 //将javaBean转化成Map
    Map<String, String> describe = BeanUtils.describe(stu1);
    System.out.println("------------------------");
    for (Map.Entry<String, String> entry : describe.entrySet()) {
      System.out.println("K:"+entry.getKey()+"--V:"+entry.getValue());
    }

 //将Map转化成javaBean
    Map<String , String> map = new HashMap<>();
    map.put("no", "TEST002");
    map.put("name", "Blueth Li");
    map.put("major", "Kong Fu");
    Student stu3 = new Student();
    BeanUtils.populate(stu3, map);
    System.out.println("------------------------");
    System.out.println(stu3);
  }

}
