package com.l1337.l654;


import com.l1337.common.TreeNode;

public class Solution {

//    https://leetcode.com/problems/maximum-binary-tree/discuss/106156/Java-worst-case-O(N)-solution
//    https://leetcode.com/problems/maximum-binary-tree/discuss/106147/c-9-lines-on-log-n-map-plus-stack-with-binary-search
//    https://leetcode.com/problems/maximum-binary-tree/discuss/106146/C++-O(N)-solution
    public TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start >= end)
            return null;

        //figure out max
        int max_index = start;
        for (int i = start; i < end; ++i) {
            if (nums[i] > nums[max_index])
                max_index = i;
        }

        TreeNode root = new TreeNode(nums[max_index]);
        root.left = constructMaximumBinaryTree(nums, start, max_index);
        root.right = constructMaximumBinaryTree(nums, max_index+1, end);

        return root;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length);
    }








    public TreeNode constructMaximumBinaryTreeMarch10_21(int[] nums, int start, int end) {
        if (start >= end)
            return null;
        //find root
        int largestIndex = start;
        for(int i = start; i < end; ++i)
        {
            if (nums[i] > nums[largestIndex])
                largestIndex = i;
        }

        TreeNode root = new TreeNode(nums[largestIndex]);
        root.left = constructMaximumBinaryTreeMarch10_21(nums, start, largestIndex);
        root.right = constructMaximumBinaryTreeMarch10_21(nums, largestIndex+1, end);

        return root;
    }
    public TreeNode constructMaximumBinaryTreeMarch10_21(int[] nums) {
        return constructMaximumBinaryTreeMarch10_21(nums, 0, nums.length);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
