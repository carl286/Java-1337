package com.l1337.l34;


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

        if (right < nums.length) {
            return nums[right] == target ? right : -1;
        } else {
            return -1;
        }
    }

    // nums[x-1] <= nums[x] = target < nums[x+1]
    private int fineRight(int [] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 != right) {
            int mid = left + ((right - left) >> 1);
            if (target < nums[mid])
                right = mid;
            else
                left = mid;
        }
        if (left >= 0) {
            return nums[left] == target ? left : -1;
        } else {
            return -1;
        }
    }

    public int[] searchRange(int[] nums, int target) {
        int[] ret = {-1, -1};
        int left = fineLeft(nums, target);
        if (left != -1) {
            ret[0] = left;
            ret[1] = fineRight(nums, target);
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [] nums = {5,7,7,8,8,10};
        int target = 8;
        System.out.println(s.searchRange(nums, target));
    }
}
