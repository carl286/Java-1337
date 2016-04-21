package com.l1337.l283;

//283. Move Zeroes
//https://leetcode.com/problems/move-zeroes/
public class Solution {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;

        int i,l;
        for (i = 0, l = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                nums[l++] = nums[i];
            }
        }
        while (l < nums.length) {
            nums[l++] = 0;
        }
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int [] nums = {0, 1, 0, 3, 12};
        s.moveZeroes(nums);
        for (int i : nums)
            System.out.println(i);
    }
}
