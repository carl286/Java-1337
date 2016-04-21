package com.l1337.l338;

//https://leetcode.com/problems/counting-bits/
//338. Counting Bits

public class Solution {

    public int[] countBits(int num) {
        int[] ret = new int [num+1];
        for (int i = 1; i <= num; ++i) {
            if ((i & 0x01) != 0)
                ret[i] = 1 + ret[i>>1];
            else
                ret[i] = ret[i>>1];
        }
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        for (int i : s.countBits(5))
            System.out.println(i);
    }
}
