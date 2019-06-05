/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：TreeTest.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.composite;

/**
 * TreeTest
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:05:42
 * @since 1.0
 */
public class TreeTest {

    public static void main(String[] args) {
        Tree tree = new Tree("A");
        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeC = new TreeNode("C");

        nodeB.add(nodeC);
        tree.root.add(nodeB);
        System.out.println("build the tree finished!");
    }

}
