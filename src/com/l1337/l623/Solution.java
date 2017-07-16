package com.l1337.l623;


import com.l1337.common.TreeNode;

public class Solution {

    private TreeNode helper(TreeNode parent, TreeNode cur, int v, int d) {
        if (d == 0) {
            TreeNode ret = new TreeNode(v);
            if (cur != null) {
                if (parent.left == cur)
                    ret.left = cur;
                else
                    ret.right = cur;
            }
            return ret;
        } else if (cur == null) {
            return cur;
        } else {
            //d > 0, cur != null
            cur.left = helper(cur, cur.left, v, d - 1);
            cur.right = helper(cur, cur.right, v, d - 1);
            return cur;
        }
    }

//    https://leetcode.com/problems/add-one-row-to-tree/discuss/104555/C++-Java-10-line-Solution-no-helper
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        //assume root not null, d >= 1
        if (d == 1) {
            TreeNode ret = new TreeNode(v);
            ret.left = root;
            return ret;
        }

        --d;

        return helper(null, root, v, d);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
