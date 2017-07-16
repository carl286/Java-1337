package com.l1337.l538;


import com.l1337.common.TreeNode;

public class Solution {
    public TreeNode convertBST(TreeNode root) {
        TreeNode ret = root;
        int acc = 0;
        while (root != null) {
            if (root.right == null) {
                root.val += acc;
                acc = root.val;
                root = root.left;
            } else {
                TreeNode right = root.right;
                while (right.left != null && right.left != root) {
                    right = right.left;
                }

                if (right.left == null) {
                    right.left = root;
                    root = root.right;
                } else {
                    right.left = null;
                    root.val += acc;
                    acc = root.val;
                    root = root.left;
                }
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
