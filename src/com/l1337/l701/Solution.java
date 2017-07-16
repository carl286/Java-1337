package com.l1337.l701;


import com.l1337.common.TreeNode;

public class Solution {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val < val)
            root.right = insertIntoBST(root.right, val);
        else
            root.left =  insertIntoBST(root.left, val);

        return root;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
