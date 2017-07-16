package com.l1337.l50;


import java.util.*;

public class Solution {

    public double myPowHelper(double x, long n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return x;
        return myPowHelper(x*x, n / 2) * ((n % 2) == 1 ? x: 1);
    }

    public double myPow(double x, int n) {
        if (n == Integer.MIN_VALUE) return 0;
        return n > 0 ? myPowHelper(x, n) : 1 / myPowHelper(x, 0 - (long)n);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
