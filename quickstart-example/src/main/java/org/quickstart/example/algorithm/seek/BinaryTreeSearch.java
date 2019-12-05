package org.quickstart.example.algorithm.seek;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/5 22:35
 */
public class BinaryTreeSearch {

  //定义二叉树数据结构
  public class TreeNode {

    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
      this.value = value;
    }
  }

  public class TreeSearch {

    public TreeNode Solution(TreeNode root, int value) {
      if (root == null) {
        return null;
      }
      //定义当前节点
      TreeNode current = root;

      while (current != null) {
        if (current.value < value) {
          //如果当前节点的值比value小，则从其右子树中开始找
          current = current.right;
        } else if (current.value > value) {
          //如果当前节点的值比value大，则从其左子树中开始找
          current = current.left;
        } else if (current.value == value) {
          //找到则返回这个节点
          return current;
        }
      }

      return null;
    }
  }

}
