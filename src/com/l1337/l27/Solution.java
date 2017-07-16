package com.l1337.l27;


public class Solution {

    public int removeElement(int[] nums, int val) {
        int ret = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != val) {
                nums[ret++] = nums[i];
            }
        }
        return ret;

    }

    public static void main(String [] args) {
        Solution s = new Solution();

        System.out.println("Hello World");
    }
}
