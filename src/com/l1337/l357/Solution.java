package com.l1337.l357;


public class Solution {

    public int countNumbersWithUniqueDigits(int n) {
        /*
        if (n == 0)
            return 1;

        int ret;
        int a1 = 0, a2 = 9, b1 = 1;
        ret = a2 + b1;
        int i = 2;
        int last = Math.min(10,n);
        while (i <= last) {
            int b2 = (11-i)*(b1+a1);
            int a3 = (10-i)*a2;
            a1 = a2;
            a2 = a3;
            b1 = b2;

            ret += (a2 + b1);
            ++i;
        }

        return ret;
        */

        //Can you SB more???? This is sucn a naive question...
        if (n == 0)
            return 1;
        if (n == 1)
            return 10;
        int ret = 10;
        int i = 2;
        int last = Math.min(10,n);
        int p = 9;
        while (i <= last) {
            p *= (11-i);
            ret += p;
            ++i;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.countNumbersWithUniqueDigits(3));
    }
}
