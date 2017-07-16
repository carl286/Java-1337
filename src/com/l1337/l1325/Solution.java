package com.l1337.l1325;


import com.l1337.common.TreeNode;

public class Solution {

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null)
            return null;
        TreeNode left = null;
        TreeNode right = null;
        if (root.left != null)
            left = removeLeafNodes(root.left, target);
        if (root.right != null)
            right = removeLeafNodes(root.right, target);

        if (left == null && right == null && root.val == target)
            return null;

        root.left = left;
        root.right = right;
        return root;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
