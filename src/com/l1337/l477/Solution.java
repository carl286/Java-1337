package com.l1337.l477;


public class Solution {
    public int totalHammingDistance(int[] nums) {
        //32 * O(n)
        int ret = 0;
        int mask = 0x01;
        while (mask != 0) {
            int zeros = 0, ones = 0;
            for (int i : nums) {
                if ((i & mask) == 0)
                    ++zeros;
                else
                    ++ones;
            }
            ret += zeros * ones;
            mask <<= 1;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.totalHammingDistance(new int [] {4,14,2}));
    }
}
