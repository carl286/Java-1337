package com.l1337.l342;

//https://leetcode.com/problems/power-of-four/
//342. Power of Four

//Follow up: Could you solve it without loops/recursion?

public class Solution {
    public boolean isPowerOfFour(int num) {
        if (num <= 0)
            return false;
        if (num == 1)
            return true;

//        return false;

        if ((num & (num - 1)) != 0)
            return false;
        int p = 1;
        for (int i = 0; i < 16; ++i) {
            if ((num & p) != 0)
                return true;
            p <<= 2;
        }

        return false;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.isPowerOfFour(1<<32));
    }
}
