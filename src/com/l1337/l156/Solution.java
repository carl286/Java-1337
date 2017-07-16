package com.l1337.l156;


import com.l1337.common.TreeNode;
import com.sun.source.tree.Tree;

public class Solution {
/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 */
// https://www.cnblogs.com/grandyang/p/5172838.html
//    https://www.lintcode.com/problem/binary-tree-upside-down/description

    public TreeNode UpsideDownBinaryTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode lastRoot = null, lastRight = null;
        while (root != null)
        {
            TreeNode left = root.left;
            TreeNode right = root.right;

            root.right = lastRoot;
            root.left = lastRight;

            lastRoot = root;
            lastRight = right;
            root = left;
        }

        return lastRoot;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;

        System.out.println(s.UpsideDownBinaryTree(n1));
    }
}
