package com.l1337.l238;


public class Solution {

    public int[] productExceptSelf(int[] nums) {
        int right [] = new int [nums.length];
        right[right.length - 1] = 1;
        for (int i = right.length - 2; i >= 0; --i)
            right[i] = right[i+1] * nums[i+1];

        int left = 1;
        for (int i = 0; i < nums.length; ++i)
        {
            right[i] *= left;
            left *= nums[i];
        }
        return right;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
