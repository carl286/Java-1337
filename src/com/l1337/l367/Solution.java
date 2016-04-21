package com.l1337.l367;


public class Solution {

//    http://www.cnblogs.com/grandyang/p/5619296.html
//    https://leetcode.com/problems/valid-perfect-square/submissions/
    public boolean isPerfectSquare(int num) {
        //assume num > 0
        /*
        int mid = num / 2;
        int i = 2;
        while (i <= mid && num != 1) {
            while (num % i == 0) {
                num /= i;
                if (num % i != 0)
                    return false;
                num /= i;
                mid = num / 2;
            }
            ++i;
        }

        return num == 1;
        */
        return false;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
//        for (int i = 1; i <= 16; ++i)
//            System.out.println(i + "\t" + s.isPerfectSquare(i));
        System.out.println(16 + "\t" + s.isPerfectSquare(2147483647));

    }
}
