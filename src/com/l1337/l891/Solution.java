package com.l1337.l891;


import java.util.Arrays;

public class Solution {

//    https://leetcode.com/problems/sum-of-subsequence-widths/discuss/161267/JavaC%2B%2BPython-Sort-and-One-Pass
    public int sumSubseqWidths(int[] A) {
        int base = 1000000000 + 7;
        Arrays.sort(A);
        int ret = 0;
//        for (int i = 1; i < A.length; ++i)
//        {
//            ret = ((((((A[i] - A[i-1]) * (i+1))%base) * (i+2)) / 2) % base + ret) % base;
//        }

        int preSum = 0, suffixSum = 0;
        for(int k = 1; k < A.length; ++k)
        {
            suffixSum += A[A.length-k];
            preSum += A[k-1];

            ret = (ret + (((suffixSum - preSum) * (((int) Math.pow(2, k-1)) % base)) % base)) % base;
        }

        /*
        Arrays.sort(A);
        long c = 1, res = 0, mod = (long)1e9 + 7;
        for (int i = 0, n = A.length; i < n; ++i, c = c * 2 % mod)
            res = (res + A[i] * c - A[n - i - 1] * c) % mod;
        return (int)((res + mod) % mod);
         */
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.sumSubseqWidths(new int [] {1,2,3}));
    }
}
