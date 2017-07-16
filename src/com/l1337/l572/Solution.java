package com.l1337.l572;


import com.l1337.common.TreeNode;

public class Solution {

    private boolean isEqualTree(TreeNode s, TreeNode t) {
        if (s == null && t == null)
            return true;
        if (s == null || t == null)
            return false;

        if (s.val != t.val)
            return false;

        return isEqualTree(s.left, t.left) && isEqualTree(s.right, t.right);
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (isEqualTree(s, t))
            return true;

        if (s != null)
            return isSubtree(s.left, t) || isSubtree(s.right, t);
        else
            return false;
    }

//    https://leetcode.com/problems/subtree-of-another-tree/discuss/102760/Easy-O(n)-java-solution-using-preorder-traversal
    public static void main(String [] args) {
        Solution s = new Solution();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n3.left = n4;
        n3.right = n5;


        n1.left = n2;

        TreeNode n11 = new TreeNode(1);
        System.out.println(s.isSubtree(n1, n11));
    }
}
