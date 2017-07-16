package com.l1337.l687;


import com.l1337.common.TreeNode;

public class Solution {
    static class Node {
        int maxLocal;
        int maxThroughRoot;
    }

    Node helper(TreeNode root) {
        //assum no null

        Node ret = new Node();
        ret.maxLocal = 1;
        ret.maxThroughRoot = 1;
        int maxLeftSubpath = 0, maxRightSubPath = 0;

        if (root.left != null) {
            Node left = helper(root.left);
            ret.maxLocal = Math.max(left.maxLocal, ret.maxLocal);
            if (root.left.val == root.val)
                maxLeftSubpath = left.maxThroughRoot;
        }

        if (root.right != null) {
            Node right = helper(root.right);
            ret.maxLocal = Math.max(right.maxLocal, ret.maxLocal);
            if (root.right.val == root.val)
                maxRightSubPath = right.maxThroughRoot;
        }

        ret.maxThroughRoot += Math.max(maxLeftSubpath, maxRightSubPath);
        ret.maxLocal = Math.max(ret.maxLocal, 1 + maxLeftSubpath + maxRightSubPath);

        return ret;
    }

    public int longestUnivaluePath(TreeNode root) {
        if (root == null)
            return 0;

        return helper(root).maxLocal - 1;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
