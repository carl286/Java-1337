package com.l1337.l938;


import com.l1337.common.TreeNode;

public class Solution {

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null)
            return 0;
        int ret = 0;
        if (root.val < low)
        {
            ret += rangeSumBST(root.right, low, high);
        }
        else if (root.val > high)
        {
            ret += rangeSumBST(root.left, low, high);
        }
        else
        {
            ret += root.val;
            ret += rangeSumBST(root.right, low, high);
            ret += rangeSumBST(root.left, low, high);
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
