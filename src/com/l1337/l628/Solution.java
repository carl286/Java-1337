package com.l1337.l628;


import java.util.Arrays;

public class Solution {

    public int maximumProduct(int[] nums) {
        //nums.length >= 3 and there is negative numbers, there can be 0....
        Arrays.sort(nums);

        //you can use dp if you want....
        return Math.max(nums[0] * nums[1] * nums[nums.length - 1], nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
