package com.l1337.l461;


public class Solution {
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int count = 0;
        while (z != 0) {
            z = z & (z - 1);
            ++count;
        }

        return count;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.hammingDistance(1,4));
    }
}
