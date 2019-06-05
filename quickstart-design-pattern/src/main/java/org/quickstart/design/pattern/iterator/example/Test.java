/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.iterator.example;

/**
 * Test
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午9:14:38
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) {
        Collection collection = new MyCollection();
        Iterator it = collection.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
