package com.l1337.l129;




public class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // https://leetcode.com/submissions/detail/62866686/
    private int sum = 0;
    public void sumNumbers(TreeNode root, int acc) {
        int cur = 10*acc + root.val;
        if (root.left == null)
        {
            if (root.right == null)
            {
                sum += cur;
            }
            else
            {
                sumNumbers(root.right, cur);
            }
        }
        else
        {
            if (root.right == null)
            {
                sumNumbers(root.left, cur);
            }
            else
            {
                sumNumbers(root.left, cur);
                sumNumbers(root.right, cur);
            }
        }

    }

    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        sumNumbers(root, 0);

        return sum;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n1 = new TreeNode(1, n2, n3);
        System.out.println(s.sumNumbers(n1));
    }
}
