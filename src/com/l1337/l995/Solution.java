package com.l1337.l995;


public class Solution {

    public int minKBitFlips(int[] A, int K) {
        int cur = 0, res = 0, n = A.length;
        for (int i = 0; i < n; ++i) {
            if (i >= K && A[i - K] > 1) {
                cur--;
                A[i - K] -= 2;
            }
            if (cur % 2 == A[i]) {
                if (i + K > n) return -1;
                A[i] += 2;
                cur++;
                res++;
            }
        }
        return res;
    }

//    https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/discuss/239284/C%2B%2B-greedy-stack-and-O(1)-memory
//    https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/discuss/238609/JavaC%2B%2BPython-One-Pass-and-O(1)-Space
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
