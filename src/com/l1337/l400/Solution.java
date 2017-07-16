package com.l1337.l400;


public class Solution {
    public int findNthDigit(int n) {
        int ret = 0;
        long base = 1;
        int count = 1;

        while (count * 9 * base <= n) {
            n -= count * 9 * base;
            base *= 10;
            ++count;
        }
        ret = (int) (base - 1);

        while (n > 0) {

            //base > 1 + n;
            base /= 10;
            ret += n / base * base;
            n %= base;
            base /= 10;
        }
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
