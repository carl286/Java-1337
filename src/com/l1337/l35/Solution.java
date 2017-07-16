package com.l1337.l35;


public class Solution {

    // nums[x-1] < target, nums[x] = target <= nums[x+1]
    private int fineLeft(int [] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 != right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target)
                left = mid;
            else
                right = mid;
        }

        return right;
    }

    public int searchInsert(int[] nums, int target) {
        return fineLeft(nums, target);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
