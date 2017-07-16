package com.l1337.l473;


public class Solution {

//    http://www.cnblogs.com/grandyang/p/6238425.html
//    https://leetcode.com/problems/matchsticks-to-square/discuss/95729/Java-DFS-Solution-with-Explanation
    public boolean makesquare(int[] nums) {
        if (nums.length <= 3)
            return false;

        int total = 0;
        for (int num : nums) {
            if (num <= 0)
                return false;
            total += num;
        }

        if (total % 4 != 0)
            return false;


        int dimention_length = total / 4;
        for (int num : nums) {
            if (num > dimention_length)
                return false;
        }

        return true;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
