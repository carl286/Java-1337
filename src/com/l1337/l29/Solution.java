package com.l1337.l29;


public class Solution {

    public int divide(int dividend, int divisor) {
        if (dividend == 0)
            return 0;
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;

        boolean isPositive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        long divd = dividend > 0 ? (long) dividend : 0 - (long)dividend;
        long divsor = divisor > 0 ? (long) divisor: 0 - (long) divisor;
        if (divsor == 1)
            return isPositive ? (int)divd : (int)-divd;
        long ret = 0;
        int shift = 1;
        while ((divsor << shift) <= divd) {
            // System.out.println(shift + "\t" + (divsor << shift));
            ++shift;
        }
        /*
        System.out.println(30 + "\t" + (divsor << 30) + "\t" + ((divsor << 30) < divd));
        System.out.println(30 + "\t" + (divsor << 30) + "\t" + ((divsor << 30) > divd));
        System.out.println(divd);
        */
        --shift;
        while (shift >= 0) {
            if ((divsor << shift) <= divd) {
                ret += 1 << shift;
                divd -= divsor << shift;
            }
            --shift;
        }

        return isPositive ? (int) ret : 0 - (int)ret;
    }

//    https://leetcode.com/problems/divide-two-integers/discuss/142849/C%2B%2BJavaPython-Should-Not-Use-%22long%22-Int

    public int divide2(int A, int B) {
        if (A == 1 << 31 && B == -1) return (1 << 31) - 1;
        int a = Math.abs(A), b = Math.abs(B), res = 0, x = 0;
        while (a - b >= 0) {
            for (x = 0; a - (b << x << 1) >= 0; x++);
            res += 1 << x;
            a -= b << x;
        }
        return (A > 0) == (B > 0) ? res : -res;
    }

    public static void main(String [] args) {
        Solution s = new Solution();

        System.out.println(s.divide2(-2147483648,2));
    }
}
