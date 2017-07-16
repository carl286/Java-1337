package com.l1337.l665;


public class Solution {

//    https://leetcode.com/problems/non-decreasing-array/discuss/174938/Python-Fast-Solution-(57-ms)
    public boolean checkPossibility(int[] nums) {
        if (nums.length <= 2)
            return true;

        int i = 1;
        while (i < nums.length && nums[i] >= nums[i-1])
            ++i;

        if (i >= nums.length - 1)
            return true;

        --i;

        int j = nums.length - 2;
        while (j >= i && nums[j] <= nums[j+1])
            --j;
        ++j;

        if (j != i + 1)
            return false;

        if (i == 0)
            return true;
        else if (j == nums.length - 1)
            return true;
        else
            return nums[i-1] <= nums[j] || nums[i] <= nums[j+1];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.checkPossibility(new int [] {4,2,1}));
    }
}
