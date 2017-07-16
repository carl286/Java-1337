package com.l1337.l81;

public class Solution {
    // https://leetcode.com/submissions/detail/62766296/
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target || nums[left] == target || nums[right] == target)
                return true;
            ++left;
            --right;
            while (left < right && nums[left] == nums[left - 1])
                ++left;
            while (left < right && nums[right] == nums[right + 1])
                --right;

        }

        return false;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int [] nums = new int[] {1,1,1,2,2,3};
        s.search(nums, 1);
    }
}
