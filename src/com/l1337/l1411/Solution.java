package com.l1337.l1411;


public class Solution {

    public int numOfWays(int n) {
        long d0 = 6, d1 = 6;
        int mod = 1000000007;
        while (--n > 0)
        {
            long nextd0 = 0, nextd1 = 0;
            // we can improve the calculation below essentially if we want...
            nextd0 = (3 * d0 + 2 * d1) % mod;
            nextd1 = (2 * d1 + 2 * d0) % mod;

            d0 = nextd0;
            d1 = nextd1;
        }

        return (int)((d0 + d1) % mod);
    }

//    https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/discuss/575485/C%2B%2BPython-O(logN)-Time
    public static void main(String [] args) {
        Solution s = new Solution();

        System.out.println(s.numOfWays(5000));
    }
}
