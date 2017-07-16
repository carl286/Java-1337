package com.l1337.l404;


import com.l1337.common.TreeNode;

public class Solution {

    //cur should not be null
    private int sumHelper(TreeNode parent, TreeNode cur) {
        if (cur == null)
            return 0;
        int ret = sumHelper(cur, cur.left) + sumHelper(cur, cur.right);
        if (cur.left == null && cur.right == null && (parent != null && parent.left == cur))
            ret += cur.val;
        return ret;
    }
    public int sumOfLeftLeaves(TreeNode root) {
        return sumHelper(null, root);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
