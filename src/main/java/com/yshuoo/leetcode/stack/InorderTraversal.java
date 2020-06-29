package com.yshuoo.leetcode.stack;

import java.util.*;

/**
 * @Author yangshuo
 * @Date 2020/6/23 20:52
 *
 * 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 输入: [1,null,2,3]
 * 1
 *   \
 *    2
 *  /
 *  3
 *
 *  输出: [1,3,2]
 */
public class InorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Set<TreeNode> set = new HashSet<TreeNode>();
        if (root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            while(root.left != null && !set.contains(root)){
                root = root.left;
                stack.push(root);
            }
            root = stack.pop();
            list.add(root.val);
            set.add(root);
            if (root.right != null){
                root = root.right;
                stack.push(root);
            }
        }
        return list;
    }
}


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }