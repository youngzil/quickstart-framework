/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：TreeNode.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.composite;

import java.util.Enumeration;
import java.util.Vector;

/**
 * TreeNode
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:04:44
 * @since 1.0
 */
public class TreeNode {

    private String name;
    private TreeNode parent;
    private Vector<TreeNode> children = new Vector<TreeNode>();

    public TreeNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    // 添加孩子节点
    public void add(TreeNode node) {
        children.add(node);
    }

    // 删除孩子节点
    public void remove(TreeNode node) {
        children.remove(node);
    }

    // 取得孩子节点
    public Enumeration<TreeNode> getChildren() {
        return children.elements();
    }
}
