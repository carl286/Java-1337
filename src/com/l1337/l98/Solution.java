package com.l1337.l98;


import com.l1337.common.TreeNode;

public class Solution {
    public boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null)
            return true;
        return root.val < max && root.val > min && isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
