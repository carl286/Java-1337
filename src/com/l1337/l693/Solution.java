package com.l1337.l693;


public class Solution {

    private final int A = 0xAAAAAAAA;
    private final int B = 0x55555555;
    public boolean hasAlternatingBits(int n) {
        return ((n ^= n/4) & n-1) == 0;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.hasAlternatingBits(4));
    }
}
