package com.l1337.l507;


public class Solution {

    public boolean checkPerfectNumber(int num) {
        if (num <= 1)
            return false;

        long acc = 1;
//        https://leetcode.com/problems/perfect-number/discuss/163198/C++-easy-to-understand-solution
        for (int i = 2, j = num >> 1; i <= j && acc <= num; ++i, j = num / i) {
            if (num % i == 0) {
                acc += i;
                if (j != i)
                    acc += j;
            }
        }

        return acc == num;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.checkPerfectNumber(28));
    }
}
