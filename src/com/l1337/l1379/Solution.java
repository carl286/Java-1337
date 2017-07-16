package com.l1337.l1379;


import com.l1337.common.TreeNode;

public class Solution {

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null)
            return null;
        if (original == target)
            return cloned;
        TreeNode left = null, right = null;
        left = getTargetCopy(original.left, cloned.left, target);
        if (left != null)
            return left;
        return getTargetCopy(original.right, cloned.right, target);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
