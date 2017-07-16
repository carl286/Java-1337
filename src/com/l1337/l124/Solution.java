package com.l1337.l124;


import com.l1337.common.TreeNode;

public class Solution {

    class InterResult {
        int maxLeft;
        int maxRight;
        int maxVal;
        InterResult(int val)
        {
            maxLeft = val;
            maxRight = val;
            maxVal = val ;
        }
    };

    public int maxPathSum(TreeNode root) {
        return maxPathSumHelper(root).maxVal;
    }

    // root is null
    public InterResult maxPathSumHelper(TreeNode root) {

        InterResult ret = new InterResult(root.val);

        InterResult left = new InterResult(0);
        if (root.left != null) {
            left = maxPathSumHelper(root.left);
            ret.maxLeft = Math.max(root.val, root.val + Math.max(left.maxLeft, left.maxRight));
            ret.maxVal = Math.max(ret.maxLeft, left.maxVal);
        }

        InterResult right = new InterResult(0);
        if (root.right != null) {
            right = maxPathSumHelper(root.right);
            ret.maxRight = Math.max(root.val, root.val + Math.max(right.maxLeft, right.maxRight));
            ret.maxVal = Math.max(ret.maxVal, right.maxVal);
        }

        ret.maxVal = Math.max(ret.maxVal, Math.max(0, Math.max(left.maxLeft, left.maxRight)) + root.val + Math.max(0, Math.max(right.maxLeft, right.maxRight)));
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();

        TreeNode n0 = new TreeNode(-10);
        TreeNode n1 = new TreeNode(9);
        TreeNode n2 = new TreeNode(20);
        n0.left = n1;
        n0.right = n2;
        TreeNode n3 = new TreeNode(15);
        TreeNode n4 = new TreeNode(7);
        n2.left = n3;
        n2.right = n4;

        // TreeNode n0 = new TreeNode(-3);

        System.out.println(s.maxPathSum(n0));
    }
}
