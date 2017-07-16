package com.l1337.l523;


public class Solution {

    private boolean isMultiple(int n, int k) {
        if (k == 0)
            return n == 0;
        else
            return n % k == 0;
    }

//    https://leetcode.com/problems/continuous-subarray-sum/discuss/99499/Java-O(n)-time-O(k)-space
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2)
            return false;

        if (k == 1)
            return true;

        for (int i  = 1; i < nums.length; ++i) {
            nums[i] += nums[i - 1];
            if (isMultiple(nums[i], k))
                return true;
        }


        for (int i = 0; i < nums.length - 1; ++i) {
            for (int j = i + 2; j < nums.length; ++j) {
                if (isMultiple(nums[j] - nums[i], k))
                    return true;
            }
        }

        return false;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.checkSubarraySum(new int []{0,0}, 0));
    }
}
