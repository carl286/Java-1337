package com.l1337.l33;


import java.util.Stack;

public class Solution {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[left] <= nums[mid]) {
                if (nums[mid] < target || target < nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }

            }

        }

        return -1;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int []nums = {4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(s.search(nums, target));
    }
}
