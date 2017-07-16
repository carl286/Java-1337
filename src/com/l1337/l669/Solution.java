package com.l1337.l669;


import com.l1337.common.TreeNode;

public class Solution {

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null)
            return null;

        TreeNode left = trimBST(root.left, L, R);
        TreeNode right = trimBST(root.right, L, R);

        TreeNode ret = new TreeNode(root.val);
        if (root.val < L || root.val > R) {
            if (left == null || right == null)
                ret = left == null ? right : left;
            else  {
                ret = left;
                while (left.right != null)
                    left = left.right;
                left.right = right;
            }
        } else {
            ret.left = left;
            ret.right = right;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
