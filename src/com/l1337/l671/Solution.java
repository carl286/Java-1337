package com.l1337.l671;


import java.util.TreeSet;

import com.l1337.common.TreeNode;

public class Solution {

//    https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/discuss/107233/Java-4-lines
    private int findSecondMinimumValueHelper(TreeNode root, TreeSet<Integer> treeSet) {
        int ret;
        if (root.left == null)
            ret = root.val;
        else
            ret = Math.min(findSecondMinimumValueHelper(root.left, treeSet),
                    findSecondMinimumValueHelper(root.right, treeSet));

        treeSet.add(ret);
        return ret;
    }
    public int findSecondMinimumValue(TreeNode root) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        findSecondMinimumValueHelper(root, treeSet);

        if (treeSet.size() > 1) {
            int first = treeSet.first();
            return treeSet.higher(first);
        } else {
            return -1;
        }
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
