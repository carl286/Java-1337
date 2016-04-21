package com.l1337.l326;

//https://leetcode.com/problems/power-of-three/
//326. Power of Three
public class Solution {

    boolean isPowerOfThreeHelperHelper(int n) {
        if (n == 1)
            return true;
        if (n % 3 != 0)
            return false;
        return isPowerOfThreeHelperHelper(n/3);
    }
    public boolean isPowerOfThreeHelper(int n) {
        if (n <= 0)
            return false;
        return isPowerOfThreeHelperHelper(n);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.isPowerOfThreeHelper(3));
    }

//    https://leetcode.com/discuss/78532/summary-all-solutions-new-method-included-at-15-30pm-jan-8th
//    1 public boolean isPowerOfThree(int n) {
//        2     return n>0 && Math.pow(3, (int)(Math.log(0x7fffffff)/Math.log(3)))%n==0;
//        3 }

//    https://leetcode.com/discuss/78495/my-one-line-java-solution
//    1 public boolean isPowerOfThree(int n) {
//        3 　　return (Math.log10(n) / Math.log10(3)) % 1 == 0;
//        5 }

//    public boolean isPowerOfThree(int n) {
//        if(n==0) return false;
//
//        return n == Math.pow(3, Math.round(Math.log(n)/Math.log(3)));
//    }
}
