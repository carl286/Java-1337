package com.l1337.l213;


public class Solution {

    // That means the first house is the neighbor of the last one.
    // Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
//    https://leetcode.com/problems/pizza-with-3n-slices/discuss/554171/Java-DP-Solution-Clear-Explanation-Clean-code
    public int rob(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return 0;

        return -1;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
