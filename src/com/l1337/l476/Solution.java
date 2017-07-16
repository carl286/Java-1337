package com.l1337.l476;


public class Solution {

    public int findComplement(int num) {
        int mask = ~0;
        int bits = 1;
        while ((num & mask) != 0) {
            mask &= ~bits;
            bits <<= 1;
        }

        return ~num & ~mask;
//        return ~num + Integer.highestOneBit(num) << 1
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findComplement(5));
    }
}
