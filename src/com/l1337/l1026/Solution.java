package com.l1337.l1026;


import com.l1337.common.TreeNode;

public class Solution {

//    https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/discuss/274610/JavaC%2B%2BPython-Top-Down
    private int helper(TreeNode root, int max_sofar, int min_sofar)
    {
        if (root == null)
            return 0;

        int diff = Math.max(Math.abs(max_sofar-root.val), Math.abs(min_sofar-root.val));
        max_sofar = Math.max(root.val, max_sofar);
        min_sofar = Math.min(root.val, min_sofar);
        int left = helper(root.left, max_sofar, min_sofar);
        int right = helper(root.right, max_sofar, min_sofar);

        return Math.max(diff, Math.max(left, right));
    }
    public int maxAncestorDiff(TreeNode root) {
        return helper(root, root.val, root.val);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
