package com.l1337.l653;


import java.util.HashSet;
import java.util.Set;

import com.l1337.common.TreeNode;

public class Solution {

    private boolean helper(TreeNode root, int k, Set<Integer> met, Set<Integer> lookfor) {
        if (root == null)
            return false;

        if (lookfor.contains(root.val))
            return true;

        if (met.contains(k - root.val))
            return true;

        met.add(root.val);
        lookfor.add(k - root.val);

        return helper(root.left, k, met, lookfor) || helper(root.right, k, met, lookfor);
    }
    //if there any duplicate in bst???
//    https://leetcode.com/problems/two-sum-iv-input-is-a-bst/discuss/106059/JavaC++-Three-simple-methods-choose-one-you-like
    public boolean findTarget(TreeNode root, int k) {
        return helper(root, k, new HashSet<>(), new HashSet<>());
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
