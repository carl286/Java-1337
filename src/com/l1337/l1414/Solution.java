package com.l1337.l1414;


public class Solution {

    public int findMinFibonacciNumbers(int k) {
        if (k < 2) return k;
        int a = 1, b = 1;
        while (b <= k) {
            b += a;
            a = b - a;
        }
        return 1 + findMinFibonacciNumbers(k - a);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
