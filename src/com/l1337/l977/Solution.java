package com.l1337.l977;


import java.util.Arrays;

public class Solution {

    public int[] sortedSquares(int[] nums) {
        int [] ret = new int [nums.length];
//        for(int i = 0; i < nums.length; ++i)
//            ret[i] = nums[i] * nums[i];
//        Arrays.sort(ret);
        int left = 0, right = nums.length - 1, index = ret.length;
        while (left <= right)
        {
            if (Math.abs(nums[left]) > Math.abs(nums[right]))
            {
                ret[--index] = nums[left] * nums[left];
                ++left;
            }
            else
            {
                ret[--index] = nums[right] * nums[right];
                --right;
            }

        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
