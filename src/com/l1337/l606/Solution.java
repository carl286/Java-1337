package com.l1337.l606;


import com.l1337.common.TreeNode;

public class Solution {

//    https://leetcode.com/problems/construct-string-from-binary-tree/discuss/151806/Python3:-Iterative-Method-Using-stack
    public String tree2str(TreeNode t) {
        if (t == null)
            return "";

        String ret = Integer.toString(t.val);
        if (t.left != null) {
            if (t.right != null)
                ret += "(" + tree2str(t.left) + ")" + "(" + tree2str(t.right) + ")";
            else
                ret += "(" + tree2str(t.left) + ")";
        } else if (t.right != null) {
            ret += "()" + "(" + tree2str(t.right) + ")";
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
