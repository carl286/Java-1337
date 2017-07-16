package com.l1337.l563;


import com.l1337.common.TreeNode;

public class Solution {

    static class Node {
        int acc_sum;
        int acc_tilt;

        Node() {
            acc_sum = 0;
            acc_tilt = 0;
        }
    }

    Node helper(TreeNode root) {
        Node ret = new Node();

        if (root != null) {
            Node left = helper(root.left);
            Node right = helper(root.right);

            ret.acc_sum = left.acc_sum + right.acc_sum + root.val;
            ret.acc_tilt = Math.abs(left.acc_sum - right.acc_sum) + left.acc_tilt + right.acc_tilt;
        }

        return ret;
    }

    public int findTilt(TreeNode root) {

        return helper(root).acc_tilt;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
