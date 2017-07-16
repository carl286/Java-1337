package com.l1337.l1137;


public class Solution {

    public int tribonacci(int n) {
        int t0 = 0, t1= 1, t2 = 1;
        switch (n)
        {
            case 0:
                return t0;
            case 1:
                return t1;
            case 2:
                return t2;
            default:
                while (n-- >= 3)
                {
                    int last_t0 = t0;
                    t0 = t1;
                    t1 = t2;
                    t2 = t0 + t1 + last_t0;
                }

                return t2;
        }
    }

//    https://leetcode.com/problems/n-th-tribonacci-number/discuss/347604/C%2B%2BO(k3log(n))-Solution-(k3)-Matrix-Exponentiation

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
