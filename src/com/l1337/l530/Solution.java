package com.l1337.l530;


import java.util.Iterator;
import java.util.TreeSet;

import com.l1337.common.TreeNode;

public class Solution {

//    https://leetcode.com/problems/minimum-absolute-difference-in-bst/discuss/166537/Java-Pre-Order-Traversal-method
    //THIS is a BST...
    public int getMinimumDifference(TreeNode root) {
        TreeSet<Integer> set = new TreeSet<>();

        int ret = Integer.MAX_VALUE;
        while (root != null) {
            if (root.left == null) {
                if (set.contains(root.val))
                    return 0;
                set.add(root.val);
                root = root.right;
            } else {
                TreeNode right;
                for (right = root.left; right.right != null && right.right != root; right = right.right);
                if (right.right == null) {
                    right.right = root;
                    root = root.left;
                } else {
                    right.right = null;
                    if (set.contains(root.val))
                        return 0;
                    set.add(root.val);
                    root = root.right;
                }
            }
        }

        Iterator<Integer> iter = set.iterator();
        int pre = iter.next();
        while (iter.hasNext()) {
            int cur = iter.next();
            ret = Math.min(ret, cur - pre);
            pre = cur;
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
