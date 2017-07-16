package com.l1337.l504;


public class Solution {

//    https://leetcode.com/problems/base-7/discuss/151681/Java-recursive-and-non-recursive
    public String convertToBase7(int num) {
        StringBuilder sb = new StringBuilder();
        if (num == 0)
            return new String("0");

        long n = num;
        if (n < 0)
            n = -n;

        while (n > 0) {
            sb.append( n % 7);
            n /= 7;
        }

        if (num < 0) {
            sb.append('-');
        }

        sb = sb.reverse();

        return sb.toString();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
