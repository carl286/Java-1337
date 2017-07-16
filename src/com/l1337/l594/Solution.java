package com.l1337.l594;


import java.util.Arrays;

public class Solution {

    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j = 0;
        int ret = 0;
        while (j < nums.length) {
            while (j < nums.length && (nums[i] == nums[j] || nums[i] + 1 == nums[j]))
                ++j;
            if (nums[i] != nums[j-1])
                ret = Math.max(ret, j - i);
            ++i;
            while (i < j && nums[i] == nums[i-1])
                ++i;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findLHS(new int [] {1,1,1}));
    }
}
