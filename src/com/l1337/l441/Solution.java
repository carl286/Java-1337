package com.l1337.l441;


public class Solution {

    public int arrangeCoins(int n) {
        long k = 0;
        while (k*(k+1)/2 < (long)n + 1)
            ++k;

        return (int)k - 1;
    }

//    https://leetcode.com/problems/arranging-coins/discuss/92298/Java-O(1)-Excel-Math-Problem
//    https://leetcode.com/problems/arranging-coins/discuss/92274/JAVA-Clean-Code-with-Explanations-and-Running-Time-2-Solutions
    public static void main(String [] args) {
        Solution s = new Solution();
        for (int n = 0; n <= 11; ++n)
            System.out.println(s.arrangeCoins(n));
    }
}
