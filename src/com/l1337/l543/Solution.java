package com.l1337.l543;


import com.l1337.common.TreeNode;

public class Solution {

    static class Result {
        int max_path_length;
        int max_left_path;
        int max_right_path;

        Result() {
            max_path_length = 0;
            max_left_path = 0;
            max_right_path = 0;
        }
    }

    private Result helper(TreeNode root) {
        Result ret = new Result();
        if (root == null)
            return ret;

        Result left = helper(root.left);
        Result right = helper(root.right);

        ret.max_left_path = root.left == null? 0 : 1 + Math.max(left.max_left_path, left.max_right_path);
        ret.max_right_path = root.right == null ? 0 : 1 + Math.max(right.max_left_path, right.max_right_path);
        ret.max_path_length = Math.max(ret.max_left_path + ret.max_right_path, Math.max(left.max_path_length, right.max_path_length));
        return ret;

    }
    public int diameterOfBinaryTree(TreeNode root) {

        return helper(root).max_path_length;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
