package com.l1337.l334;

//https://leetcode.com/problems/increasing-triplet-subsequence/
//334. Increasing Triplet Subsequence
public class Solution {

    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3)
            return false;
        int length = nums.length;
        int i = 1;
        while (i < length && nums[i] <= nums[i-1])
            ++i;
        if (i < length) {
            int [] minTwo = new int[2];
            minTwo[0] = nums[i-1];
            minTwo[1] = nums[i];
            ++i;
            while (i < length) {
                while (i < length && nums[i] <= nums[i-1])
                    ++i;
                if (i < length) {
                    if (nums[i] > minTwo[1] || nums[i-1] > minTwo[0])
                        return true;
                    //switch
                    minTwo[0] = nums[i-1];
                    minTwo[1] = nums[i];
//                } else {
//                    return nums[i-1] > minTwo[1];
                }
                ++i;
            }
        }

        return false;
    }
    public static void main(String [] args) {
//        int nums[] = {9,8,1,5,3,0,100};
        int [] nums = {5,1,5,5,2,5,4};
        Solution s = new Solution();
        System.out.println(s.increasingTriplet(nums));
    }
}
